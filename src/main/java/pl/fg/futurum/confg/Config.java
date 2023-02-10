package pl.fg.futurum.confg;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.fg.futurum.model.Campaign;
import pl.fg.futurum.repository.CampaignRepository;
import pl.fg.futurum.repository.SellerRepository;

import java.util.List;

@Configuration
public class Config {

    @Bean
    CommandLineRunner commandLineRunner(CampaignRepository campaignRepository, SellerRepository sellerRepository) {
        return args -> {
            Campaign c1 = new Campaign();
            Campaign c2 = new Campaign();
            Campaign c3 = new Campaign();
            Campaign c4 = new Campaign();
            Campaign c5 = new Campaign();
            Campaign c6 = new Campaign();
            campaignRepository.saveAll(List.of(c1,c2,c3,c4,c5,c6));
        };
    }
}
