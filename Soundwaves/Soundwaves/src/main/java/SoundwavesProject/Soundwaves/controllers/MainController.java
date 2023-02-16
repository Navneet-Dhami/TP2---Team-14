package SoundwavesProject.Soundwaves.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import SoundwavesProject.Soundwaves.UserDetails;
import SoundwavesProject.Soundwaves.UserRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
    
    @Autowired
    private UserRepository customerRepo;
    
    @GetMapping("/")
    public String home() { 
        return "index";
    }

    @GetMapping("/index")
    public String index() { 
        return "index";
    }

    @GetMapping("/contact")
    public String contact() { 
        return "contact";
    }

    @GetMapping("/products")
    public String products() { 
        return "products";
    }

    @GetMapping("/user")
    public String user() { 
        return "user";
    }

    @GetMapping("/userLogin")
    public String userLogin() { 
        return "userLogin";
    }

    @GetMapping("/userRegistration")
    public String userRegistration() { 
        return "userRegistration";
    }

    @PostMapping("/registerUser")
    public String register(@ModelAttribute UserDetails c, HttpSession session) {      
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPass = encoder.encode(c.getPassword());
        c.setPassword(encodedPass);
    
        customerRepo.save(c);
        return "redirect:/userLogin";
    }
}
