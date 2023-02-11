package pl.fg.futurum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.fg.futurum.model.User;
import pl.fg.futurum.repository.SellerRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final SellerRepository sellerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = sellerRepository.findByUsername(username);
        System.out.println(user.getAuthorities());
        if(user!=null) return user;
        throw new UsernameNotFoundException("User "+username+" is not found!");
    }
}
