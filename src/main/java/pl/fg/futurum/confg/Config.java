package pl.fg.futurum.confg;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
            User s1 = new User("s1", passwordEncoder.encode("pw"),"Seller1",3000);
            User s2 = new User("s2", passwordEncoder.encode("pw"),"Seller2",15000);
            User s3 = new User("s3", passwordEncoder.encode("pw"),"Seller3",12312);
            sellerRepository.saveAll(List.of(s1,s2,s3));
            Campaign c1 = new Campaign("Campaign1",100,true, Town.BYDGOSZCZ,100);
            Campaign c2 = new Campaign("Campaign2",200,true, Town.KRAKOW,100);
            Campaign c3 = new Campaign("Campaign3",150,false, Town.GDANSK,200);
            Campaign c4 = new Campaign("Campaign4",500,true, Town.LUBLIN,10);
            Campaign c5 = new Campaign("Campaign5",500,true, Town.LODZ,100);
            Campaign c6 = new Campaign("Campaign6",1000,true, Town.WARSAW,100);
            campaignRepository.saveAll(List.of(c1,c2,c3,c4,c5,c6));
            service.addNewCampaign(c1,s1);
            service.addNewCampaign(c2,s1);
            service.addNewCampaign(c3,s2);
            service.addNewCampaign(c4,s2);
            service.addNewCampaign(c5,s3);
            service.addNewCampaign(c6,s3);

        };
    }
}
