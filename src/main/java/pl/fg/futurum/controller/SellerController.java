package pl.fg.futurum.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.fg.futurum.model.User;
import pl.fg.futurum.service.SellerService;


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
    public String getSingleSeller(Model model,@PathVariable long id) {
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
    public void deleteSeller(@PathVariable long id) {
        sellerService.deleteSeller(id);
    }

}
