package pl.fg.futurum.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.fg.futurum.repository.SellerRepository;
import pl.fg.futurum.model.Seller;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    public Seller getSingleSeller(long id) {
        return sellerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Seller does not exist"));
    }

    public Seller createNewSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Transactional
    public Seller editSeller(long id, String username) {
        Seller edited = sellerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Seller does not exist"));
        edited.setUsername(username);
        return edited;
    }

    public void deleteSeller(long id) {
        sellerRepository.deleteById(id);
    }
}
