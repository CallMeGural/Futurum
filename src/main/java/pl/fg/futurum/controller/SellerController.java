package pl.fg.futurum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.fg.futurum.model.Seller;
import pl.fg.futurum.service.SellerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    @GetMapping("/sellers")
    public List<Seller> getAllSellers() {
        return sellerService.getAllSellers();
    }

    @GetMapping("/sellers/{id}")
    public Seller getSingleSeller(@PathVariable long id) {
        return sellerService.getSingleSeller(id);
    }

    @PostMapping("/sellers")
    public Seller createNewSeller(@RequestBody Seller seller) {
        return sellerService.createNewSeller(seller);
    }

    @PutMapping("/sellers/{id}")
    public Seller editSeller(@PathVariable long id, String username) {
        return sellerService.editSeller(id,username);
    }

    @DeleteMapping("/sellers/{id}")
    public void deleteSeller(@PathVariable long id) {
        sellerService.deleteSeller(id);
    }

}
