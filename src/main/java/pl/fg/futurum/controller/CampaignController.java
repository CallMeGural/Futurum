package pl.fg.futurum.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.fg.futurum.model.Campaign;
import pl.fg.futurum.model.Town;
import pl.fg.futurum.model.User;
import pl.fg.futurum.service.CampaignService;

@Controller
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;

    @GetMapping("/campaigns/list")
    public /*List<Campaign>*/ String getAllCampaigns(Model model,@AuthenticationPrincipal User seller) {
        model.addAttribute("campaigns",campaignService.getSellerCampaigns(seller));
        return "campaigns_list";
        //return campaignService.getAllCampaigns();
    }

    @GetMapping("/campaigns")
    public String campaignForm(Model model) {
        model.addAttribute("towns",Town.values());
        model.addAttribute("campaign",new Campaign());
        return "campaign_form";
    }

    @PostMapping("/campaigns")
    public /*Campaign*/String addNewCampaign(/*@RequestBody*/ /*@ModelAttribute("campaign")*/@Valid Campaign campaign, Errors errors, @AuthenticationPrincipal User seller) {
        if(errors.hasErrors()) return "campaignform";
        campaign.setSeller(seller);
        campaignService.addNewCampaign(campaign);
        return "redirect:/campaigns/list";
    }

    @GetMapping("/campaigns/{id}")
    public String getSingleCampaign(Model model,@PathVariable long id) {
        Campaign campaign=campaignService.getSingleCampaign(id);
        model.addAttribute("towns",Town.values());
        model.addAttribute("campaign",campaign);
        return "campaign_edit";
    }

    @PutMapping("/campaigns/{id}")
    public String updateCampaign(@PathVariable long id, @ModelAttribute("campaign") Campaign campaign) {
        campaignService.editCampaign(id, campaign);
        return "redirect:/campaigns/list";
    }

    @DeleteMapping("campaigns/{id}")
    public String deleteCampaign(@PathVariable long id) {
        campaignService.deleteCampaign(id);
        return "redirect:/campaigns/list";
    }

}
