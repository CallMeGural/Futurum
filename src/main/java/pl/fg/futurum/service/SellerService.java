package pl.fg.futurum.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.fg.futurum.model.User;
import pl.fg.futurum.repository.SellerRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;

    public List<User> getAllSellers() {
        return sellerRepository.findAll();
    }

    public User getSingleSeller(long id) {
        return sellerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User does not exist"));
    }

    public User createNewSeller(User user) {
        return sellerRepository.save(user);
    }

    @Transactional
    public User editSeller(long id, String username) {
        User edited = sellerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User does not exist"));
        edited.setUsername(username);
        return edited;
    }

    public void deleteSeller(long id) {
        sellerRepository.deleteById(id);
    }
}
