package pl.fg.futurum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.fg.futurum.model.Campaign;
import pl.fg.futurum.model.Town;
import pl.fg.futurum.model.User;
import pl.fg.futurum.repository.SellerRepository;
import pl.fg.futurum.service.CampaignService;

@Controller
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;

    @GetMapping("/campaigns/list")
    public /*List<Campaign>*/ String getAllCampaigns(Model model) {
        model.addAttribute("campaigns",campaignService.getAllCampaigns());
        return "campaignlist";
        //return campaignService.getAllCampaigns();
    }

    @GetMapping("/campaigns/{id}")
    public Campaign getSingleCampaign(@PathVariable long id) {
        return campaignService.getSingleCampaign(id);
    }

    @GetMapping("/campaigns")
    public String campaignForm(Model model) {
        Town[] towns = Town.values();
        model.addAttribute("towns",towns);
        model.addAttribute("campaign",new Campaign());
        return "campaignform";
    }

    @PostMapping("/campaigns")
    public /*Campaign*/String addNewCampaign(/*@RequestBody*/ @ModelAttribute("campaign") Campaign campaign,@AuthenticationPrincipal User seller) {
        System.out.println("jestem w addNewCampaign");
        campaign.setUser(seller);
        campaignService.addNewCampaign(campaign);
        return "redirect:/campaigns/list";
    }

    @PutMapping("/campaings/{id}")
    public Campaign editCampaign(@PathVariable long id, Campaign campaign) {
        return campaignService.editCampaign(id, campaign);
    }

}
