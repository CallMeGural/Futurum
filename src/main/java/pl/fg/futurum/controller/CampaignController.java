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
    public String getAllCampaigns(Model model,
                                  @AuthenticationPrincipal User seller) {
        model.addAttribute("campaigns",
                campaignService.getSellerCampaigns(seller));
        return "campaigns_list";
    }

    @GetMapping("/campaigns")
    public String campaignForm(Model model) {
        model.addAttribute("towns",Town.values());
        model.addAttribute("campaign",new Campaign());
        return "campaign_form";
    }

    @PostMapping("/campaigns")
    public String addNewCampaign(@Valid Campaign campaign,
                                 Errors errors,
                                 @AuthenticationPrincipal User seller) {
        if(errors.hasErrors()) return "campaign_form";
        campaignService.addNewCampaign(campaign,seller);
        return "redirect:/campaigns/list";
    }

    @GetMapping("/campaigns/{id}")
    public String getSingleCampaign(Model model,@PathVariable long id) {
        model.addAttribute("towns",Town.values());
        model.addAttribute("campaign",
                campaignService.getSingleCampaign(id));
        return "campaign_edit";
    }

    @PutMapping("/campaigns/{id}")
    public String updateCampaign(@PathVariable long id,
                                 @Valid @ModelAttribute("campaign") Campaign campaign,
                                 Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("towns",Town.values());
            return "campaign_edit";
        }
        campaignService.editCampaign(id, campaign);
        return "redirect:/campaigns/list";
    }

    @DeleteMapping("campaigns/{id}")
    public String deleteCampaign(@PathVariable long id) {
        campaignService.deleteCampaign(id);
        return "redirect:/campaigns/list";
    }

}
