package pl.fg.futurum.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.fg.futurum.model.Campaign;
import pl.fg.futurum.model.User;
import pl.fg.futurum.repository.CampaignRepository;
import pl.fg.futurum.repository.SellerRepository;
import pl.fg.futurum.service.SellerService;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    @GetMapping("/sellers/list")
    public String getAllSellers(Model model) {
        model.addAttribute("sellers",sellerService.getAllSellers());
        return "sellers_list";
    }

    @GetMapping("/register")
    public String sellerForm(Model model) {
        model.addAttribute("seller",new User());
        return "seller_registration";
    }

    @PostMapping("/register")
    public String submitRegistration(@Valid @ModelAttribute("seller") User seller,
                                     Errors errors) {
        if(errors.hasErrors()) return "seller_registration";
        sellerService.createNewSeller(seller);
        return "redirect:/sellers/list";
    }

    @GetMapping("/sellers/{id}")
    public String getSingleSeller(Model model, @PathVariable long id, @AuthenticationPrincipal User user) {
        if(user.getId()!=id) return "redirect:/sellers/list";
        User seller = sellerService.getSingleSeller(id);
        model.addAttribute("seller",seller);
        return "seller_edit";
    }

    @PutMapping("/sellers/{id}")
    public String updateSeller(@PathVariable long id,
                               @Valid @ModelAttribute("seller") User seller,
                               Errors errors) {
        if(errors.hasErrors()) return "seller_edit";
        sellerService.editSeller(seller,id);
        return "redirect:/sellers/list";
    }


    @DeleteMapping("/sellers/{id}")
    public String deleteSeller(@PathVariable long id,@AuthenticationPrincipal User seller) {
        if(seller.getId()==id) {
            sellerService.deleteSeller(id);
            return "redirect:/logout";
        }
        return "redirect:/sellers/list";
    }

}
