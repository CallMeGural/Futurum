package pl.fg.futurum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.fg.futurum.model.Campaign;
import pl.fg.futurum.model.User;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign,Long> {
    List<Campaign> findAllBySeller(User seller);

    List<Campaign> findAllByStatusTrue();

    @Modifying
    @Query("UPDATE Campaign c set c.fund=?2 where c.id=?1")
    void payForCampaign(long id,double payment);


    @Modifying
    @Query("UPDATE Campaign c set c.status=false where c.id=?1")
    void closeCampaign(long id);
}
