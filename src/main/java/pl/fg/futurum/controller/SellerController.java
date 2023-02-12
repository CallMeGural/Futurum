package pl.fg.futurum.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.fg.futurum.model.User;
import pl.fg.futurum.service.SellerService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    @GetMapping("/sellers/list")
    public /*List<User>*/String getAllSellers(Model model) {
        model.addAttribute("sellers",sellerService.getAllSellers());
        return "sellers_list";
        //return sellerService.getAllSellers();
    }

    @GetMapping("/sellers")
    public String sellerForm(Model model) {
        model.addAttribute("seller",new User());
        return "seller_registration";
    }

    @PostMapping("/sellers")
    public String submitRegistration(@Valid User user, Errors error) {
        if(error.hasErrors()) return "seller_registration";
        sellerService.createNewSeller(user);
        return "redirect:/sellers/list";
    }

    @GetMapping("/sellers/{id}")
    public String getSingleSeller(Model model,@PathVariable long id) {
        User seller = sellerService.getSingleSeller(id);
        model.addAttribute("seller",seller);
        return "seller_edit";
    }

    @PutMapping("/sellers/{id}")
    public String updateSeller(@PathVariable long id, @ModelAttribute("seller") User seller) {
        sellerService.editSeller(seller,id);
        return "redirect:/sellers/list";
    }

//    @PutMapping("/sellers/{id}")
//    public User editSeller(@PathVariable long id, String username) {
//        return sellerService.editSeller(username,id);
//    }

    @DeleteMapping("/sellers/{id}")
    public void deleteSeller(@PathVariable long id) {
        sellerService.deleteSeller(id);
    }

}
