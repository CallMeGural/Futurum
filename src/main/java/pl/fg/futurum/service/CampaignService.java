package pl.fg.futurum.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import pl.fg.futurum.model.Campaign;
import pl.fg.futurum.model.User;
import pl.fg.futurum.repository.CampaignRepository;
import pl.fg.futurum.repository.SellerRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final SellerRepository sellerRepository;

    public List<Campaign> getSellerCampaigns(User seller) {
        return campaignRepository.findAllBySeller(seller);
    }

    public Campaign getSingleCampaign(long id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Campaign does not exist"));
    }

    @Transactional
    public void addNewCampaign(Campaign campaign,User seller) {
        campaign.setSeller(seller);
        sellerRepository.campaignDonation(seller.getFunds()-campaign.getFund(),seller.getId());
        campaignRepository.save(campaign);
    }


    @Transactional
    public void editCampaign(long id, Campaign campaign) {
        Campaign edited = campaignRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Campaign does not exist"));
        edited.setName(campaign.getName());
        edited.setKeywords(campaign.getKeywords());
        edited.setTown(campaign.getTown());
        edited.setRadius(campaign.getRadius());

        sellerRepository.campaignDonation(
                edited.getSeller().getFunds()-campaign.getFund()+edited.getFund(),
                edited.getSeller().getId());
        edited.setFund(campaign.getFund());

        if(edited.getFund()>edited.getBid()) edited.setStatus(true);
    }

    @Transactional
    public void deleteCampaign(long id) {
        Campaign campaign = findSingleCampaignForClient(id);
        User seller = campaign.getSeller();
        sellerRepository.campaignDonation(seller.getFunds()+campaign.getFund(),id);
        campaignRepository.deleteById(id);
    }

    public List<Campaign> findAllCampaignsForClient() {
        return campaignRepository.findAllByStatusTrue();
    }

    public Campaign findSingleCampaignForClient(long id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Campaign does not exist"));
    }

    @Transactional
    public void closeCampaign(long id) {
        Campaign campaign = findSingleCampaignForClient(id);
        if(campaign.getFund()-campaign.getBid()<=0) campaignRepository.closeCampaign(id);

    }

    @Transactional
    public void payForCampaign(long id) {
        Campaign campaign = findSingleCampaignForClient(id);
        double afterPayment =campaign.getFund()-campaign.getBid();
        campaignRepository.payForCampaign(id,afterPayment);
    }
}
