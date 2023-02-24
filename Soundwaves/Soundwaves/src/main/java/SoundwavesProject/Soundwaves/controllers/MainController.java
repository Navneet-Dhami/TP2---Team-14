package SoundwavesProject.Soundwaves.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import SoundwavesProject.Soundwaves.model.User;
import SoundwavesProject.Soundwaves.repository.UserRepository;
import SoundwavesProject.Soundwaves.service.ProductService;
import SoundwavesProject.Soundwaves.service.categoriesService;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private categoriesService categoriesService;

    @Autowired
    ProductService productService;
    
    @GetMapping({"/", "/index"})
    public String home(Model model) { 
        model.addAttribute("categories", categoriesService.getAllCategory());
        return "index";
    }

    @GetMapping({"/products/category/{id}"})
    public String viewByCategoryHome(Model model, @PathVariable int id) { 
        model.addAttribute("categories", categoriesService.getAllCategory());
        model.addAttribute("products", productService.getProductByCatId(id));
        return "products";
    }

    @GetMapping("/contact")
    public String contact() { 
        return "contact";
    }

    @GetMapping("/aboutus")
    public String aboutus() { 
        return "aboutus";
    }

    @GetMapping("/products")
    public String products(Model model) { 
        model.addAttribute("categories", categoriesService.getAllCategory());
        model.addAttribute("products", productService.getProduct());
        return "products";
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
