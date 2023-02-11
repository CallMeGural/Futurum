package pl.fg.futurum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.fg.futurum.model.User;

@Repository
public interface SellerRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
