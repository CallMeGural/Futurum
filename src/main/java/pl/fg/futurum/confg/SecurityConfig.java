package pl.fg.futurum.confg;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests(
                authorization -> authorization
                        .requestMatchers("/campaigns/**","/sellers/**")
                        .hasAuthority("USER")

                        .requestMatchers("/","/register","/clients/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
        )
                .formLogin().permitAll()
                .and()
                .logout().logoutSuccessUrl("/");
        return http.build();
    }
}
