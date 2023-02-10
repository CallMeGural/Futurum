package pl.fg.futurum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.fg.futurum.model.Campaign;
import pl.fg.futurum.service.CampaignService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;

    @GetMapping("/campaigns")
    public List<Campaign> getAllCampaigns() {
        return campaignService.getAllCampaigns();
    }

    @GetMapping("/campaigns/{id}")
    public Campaign getSingleCampaign(@PathVariable long id) {
        return campaignService.getSingleCampaign(id);
    }

    @PostMapping("/campaigns")
    public Campaign addNewCampaign(@RequestBody Campaign campaign) {
        return campaignService.addNewCampaign(campaign);
    }

    @PutMapping("/campaings/{id}")
    public Campaign editCampaign(@PathVariable long id, Campaign campaign) {
        return campaignService.editCampaign(id, campaign);
    }

}
