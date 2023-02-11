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

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class Config {

    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner commandLineRunner(CampaignRepository campaignRepository, SellerRepository sellerRepository) {
        return args -> {
            User s1 = new User("s1", passwordEncoder.encode("pw"),"Seller1",1000000);
            User s2 = new User("s2", passwordEncoder.encode("pw"),"Seller2",1000001);
            User s3 = new User("s3", passwordEncoder.encode("pw"),"Seller3",1000213);
            sellerRepository.saveAll(List.of(s1,s2,s3));
            Campaign c1 = new Campaign("Campaign1",100000,true, Town.BYDGOSZCZ,100,s1);
            Campaign c2 = new Campaign("Campaign2",200000,true, Town.KRAKOW,100,s1);
            Campaign c3 = new Campaign("Campaign3",150000,false, Town.GDANSK,200,s2);
            Campaign c4 = new Campaign("Campaign4",50000,true, Town.LUBLIN,10,s2);
            Campaign c5 = new Campaign("Campaign5",50000,true, Town.LODZ,100,s3);
            Campaign c6 = new Campaign("Campaign6",100000,true, Town.WARSAW,100,s3);
            campaignRepository.saveAll(List.of(c1,c2,c3,c4,c5,c6));
        };
    }
}
