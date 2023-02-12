package pl.fg.futurum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.fg.futurum.model.User;

@Repository
public interface SellerRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    @Modifying
    @Query("Update User s set s.funds=?1 where s.id=?2")
    void campaignDonation(double funds,long id);
}
