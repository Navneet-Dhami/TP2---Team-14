package SoundwavesProject.Soundwaves.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SoundWavesUserDetails implements UserDetails {

    private User u;
    
    public SoundWavesUserDetails(User u) {
        this.u = u; 
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = u.getRole();
        List<SimpleGrantedAuthority> auth = new ArrayList<>(); 

        auth.add(new SimpleGrantedAuthority(role));
        return auth;
    }

    @Override
    public String getPassword() {
        return u.getPassword();
    }

    @Override
    public String getUsername() {
        return u.getUsername();
    }

    public int getUserId() {
        return u.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }   
}
