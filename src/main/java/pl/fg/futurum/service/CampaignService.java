package pl.fg.futurum.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import pl.fg.futurum.model.Campaign;
import pl.fg.futurum.model.User;
import pl.fg.futurum.repository.CampaignRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;

    public List<Campaign> getSellerCampaigns(User seller) {
        return campaignRepository.findAllBySeller(seller);
    }

    public Campaign getSingleCampaign(long id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Campaign does not exist"));
    }

    public Campaign addNewCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }


    @Transactional
    public void editCampaign(long id, Campaign campaign) {
        Campaign edited = campaignRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Campaign does not exist"));
        edited.setName(campaign.getName());
        edited.setKeywords(campaign.getKeywords());
        edited.setBid(campaign.getBid());
        edited.setTown(campaign.getTown());
        edited.setRadius(campaign.getRadius());
    }

    public void deleteCampaign(long id) {
        campaignRepository.deleteById(id);
    }
}
