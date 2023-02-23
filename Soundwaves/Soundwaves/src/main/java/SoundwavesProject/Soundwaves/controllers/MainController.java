package SoundwavesProject.Soundwaves.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import SoundwavesProject.Soundwaves.model.User;
import SoundwavesProject.Soundwaves.repository.UserRepository;
import SoundwavesProject.Soundwaves.service.categoriesService;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
    
    @Autowired
    private UserRepository userRepository;

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

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    @GetMapping("/login")
    public String login() { 
        return "login";
    }

    @GetMapping("/register")
    public String register() { 
        return "register";
    }

    @PostMapping("/registerUser")
    public String register(@ModelAttribute User u, HttpSession session) {      
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPass = encoder.encode(u.getPassword());
        u.setPassword(encodedPass);
        u.setRole("USER");
        userRepository.save(u);
        return "redirect:/login";
    }
}
