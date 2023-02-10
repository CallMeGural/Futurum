package pl.fg.futurum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.fg.futurum.model.Campaign;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign,Long> {
}
