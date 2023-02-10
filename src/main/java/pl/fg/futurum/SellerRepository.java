package pl.fg.futurum;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.fg.futurum.model.Seller;

public interface SellerRepository extends JpaRepository<Seller,Long> {
}
