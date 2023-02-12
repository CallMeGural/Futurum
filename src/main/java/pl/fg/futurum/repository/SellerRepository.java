package pl.fg.futurum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.fg.futurum.model.User;

@Repository
public interface SellerRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    @Modifying
    @Query("UPDATE User s set s.funds=s.funds-?1 where s.id=?2")
    int updateSellerFundValue(double bid,long id);
}
