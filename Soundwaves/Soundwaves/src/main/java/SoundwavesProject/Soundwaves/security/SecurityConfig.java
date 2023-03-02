package SoundwavesProject.Soundwaves.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import SoundwavesProject.Soundwaves.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    
    @Bean
    public UserDetailsService userDetailsService() { 
        return new UserService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder();
    } 

    @Bean 
    public DaoAuthenticationProvider authenticationProvider() {  
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception { 
        http
            .authorizeHttpRequests()
            .requestMatchers( "/media/**").permitAll() 
            .requestMatchers( "/css/styles.css", "/js/script.js").permitAll()  
            .requestMatchers("/", "/index", "/contact", "/products", "/products/category/{id}","/aboutus").permitAll()
            .requestMatchers( "/register", "/registerUser").permitAll()    
            .requestMatchers( "/admin/adminHome").hasAnyAuthority("ADMIN")
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/login")
            .loginProcessingUrl("/loginUser")
            .usernameParameter("loginEmail")
            .passwordParameter("loginPassword")
            .defaultSuccessUrl("/")
            .failureUrl("/login")
            .permitAll()
            .and() 
            .logout()
            .logoutSuccessUrl("/")
            .permitAll();
        return http.build();
    }
}
