package SoundwavesProject.Soundwaves.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import SoundwavesProject.Soundwaves.model.UserDetails;
import SoundwavesProject.Soundwaves.repository.UserRepository;
import SoundwavesProject.Soundwaves.service.categoriesService;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
    
    @Autowired
    private UserRepository customerRepo;

    @Autowired
    private categoriesService categoriesService;
    
    @GetMapping("/")
    public String home() { 
        return "index";
    }

    @GetMapping("/index")
    public String getCategories(Model model){
        model.addAttribute("categories", categoriesService.getAllCategory());
        return "index.html";
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
