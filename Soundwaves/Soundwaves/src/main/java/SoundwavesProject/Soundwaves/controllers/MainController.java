package SoundwavesProject.Soundwaves.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import SoundwavesProject.Soundwaves.global.AllData;
import SoundwavesProject.Soundwaves.model.Product;
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



    @GetMapping("/search")
    public String search(@Param("keyword") String keyword, Model model) {
        System.out.println("keyword: " + keyword);

        List<Product> searchResult = productService.searchKeyword(keyword);
        model.addAttribute("keyword", keyword);
        model.addAttribute("searchResult", searchResult);
        model.addAttribute("pageTitle", "Search Results For " + keyword);
        return "search_results";


    }


    
    @GetMapping({"/", "/index"})
    public String home(Model model) { 
        model.addAttribute("categories", categoriesService.getAllCategory());
        model.addAttribute("cartNo", AllData.cart.size());
        return "index";
    }

    @GetMapping({"/products/category/{id}"})
    public String viewByCategoryHome(Model model, @PathVariable int id) { 
        model.addAttribute("categories", categoriesService.getAllCategory());
        model.addAttribute("products", productService.getProductByCatId(id));
        model.addAttribute("cartNo", AllData.cart.size());
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
        model.addAttribute("cartNo", AllData.cart.size());
        return "products";
    }

    @GetMapping("/login")
    public String login() { 
        AllData.cart.clear();
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

    @GetMapping("/addCart/{id}") public String addCart(@PathVariable int id)
    {
      AllData.cart.add(productService.getProductById(id).get());
      return "redirect:/products";
    }

    @GetMapping("/cart") public String getCart (Model model)
    {
        model.addAttribute("cartNo", AllData.cart.size());
        double amount = AllData.cart.stream().mapToDouble(Product::getPrice).sum();
        model.addAttribute("amount", amount);
        model.addAttribute("cart", AllData.cart);
        return "cart";
    }

    @GetMapping("/cart/rmvItems/{index}") public String rmvItems(@PathVariable int index)
    {
        AllData.cart.remove(index);
        return "redirect:/cart";
    }


    
}
