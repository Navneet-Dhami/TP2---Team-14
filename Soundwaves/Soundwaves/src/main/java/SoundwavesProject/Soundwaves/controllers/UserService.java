package SoundwavesProject.Soundwaves.controllers;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    public UserDetails findUserByEmail(String email);    
}
