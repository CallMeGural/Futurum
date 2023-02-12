package pl.fg.futurum.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.fg.futurum.model.User;
import pl.fg.futurum.repository.SellerRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllSellers() {
        return sellerRepository.findAll();
    }

    public User getSingleSeller(long id) {
        User seller =  sellerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User does not exist"));
        return seller;
    }

    public User createNewSeller(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return sellerRepository.save(user);
    }

    @Transactional
    public void editSeller(User seller, long id) {
        User edited = sellerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User does not exist"));
        //if(!seller.getUsername().isEmpty())
            edited.setUsername(seller.getUsername());
        //if(!seller.getFirstname().isEmpty())
             edited.setFirstname(seller.getFirstname());
        //if(seller.getFunds()>=200000)
            edited.setFunds(seller.getFunds());
    }

    public void deleteSeller(long id) {
        sellerRepository.deleteById(id);
    }
}
