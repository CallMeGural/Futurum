package pl.fg.futurum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.fg.futurum.model.User;
import pl.fg.futurum.service.SellerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    @GetMapping("/sellers")
    public List<User> getAllSellers() {
        return sellerService.getAllSellers();
    }

    @GetMapping("/sellers/{id}")
    public User getSingleSeller(@PathVariable long id) {
        return sellerService.getSingleSeller(id);
    }

    @PostMapping("/sellers")
    public User createNewSeller(@RequestBody User user) {
        return sellerService.createNewSeller(user);
    }

    @PutMapping("/sellers/{id}")
    public User editSeller(@PathVariable long id, String username) {
        return sellerService.editSeller(id,username);
    }

    @DeleteMapping("/sellers/{id}")
    public void deleteSeller(@PathVariable long id) {
        sellerService.deleteSeller(id);
    }

}
