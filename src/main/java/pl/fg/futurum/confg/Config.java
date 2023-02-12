package pl.fg.futurum.confg;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import pl.fg.futurum.model.Campaign;
import pl.fg.futurum.model.User;
import pl.fg.futurum.model.Town;
import pl.fg.futurum.repository.CampaignRepository;
import pl.fg.futurum.repository.SellerRepository;
import pl.fg.futurum.service.CampaignService;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class Config {

    private final PasswordEncoder passwordEncoder;
    private final CampaignService service;

    @Bean
    CommandLineRunner commandLineRunner(CampaignRepository campaignRepository, SellerRepository sellerRepository) {
        return args -> {
            User s1 = new User("s1", passwordEncoder.encode("pw"),"Seller1",5000);
            User s2 = new User("s2", passwordEncoder.encode("pw"),"Seller2",15000);
            User s3 = new User("s3", passwordEncoder.encode("pw"),"Seller3",12312);
            sellerRepository.saveAll(List.of(s1,s2,s3));
            Campaign c1 = new Campaign("Campaign1",100,1000, Town.BYDGOSZCZ,100);
            Campaign c2 = new Campaign("Campaign2",200,1000, Town.KRAKOW,100);
            Campaign c3 = new Campaign("Campaign3",150,1000, Town.GDANSK,200);

            campaignRepository.saveAll(List.of(c1,c2,c3));
            service.addNewCampaign(c1,s1);
            service.addNewCampaign(c2,s2);
            service.addNewCampaign(c3,s3);

        };
    }
}
