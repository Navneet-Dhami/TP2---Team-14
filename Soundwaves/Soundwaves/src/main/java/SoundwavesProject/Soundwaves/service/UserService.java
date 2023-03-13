package SoundwavesProject.Soundwaves.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import SoundwavesProject.Soundwaves.model.SoundWavesUserDetails;
import SoundwavesProject.Soundwaves.model.User;
import SoundwavesProject.Soundwaves.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByUsername(username);

        if (u == null) { 
            throw new UsernameNotFoundException(username);
        }
        return new SoundWavesUserDetails(u);
    }   

    public List<User> getUser() { 
        return userRepository.findAll();
    }

    public void removeUser(int id) { 
        userRepository.deleteById(id);
    }

    public Optional<User> getUserById(int id)
    {
        return userRepository.findById(id);
    } 
}