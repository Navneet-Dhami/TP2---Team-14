package SoundwavesProject.Soundwaves.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

// import SoundwavesProject.Soundwaves.dto.OrderDTO;
import SoundwavesProject.Soundwaves.global.AllData;
import SoundwavesProject.Soundwaves.model.Product;
// import SoundwavesProject.Soundwaves.model.Order;
import SoundwavesProject.Soundwaves.model.User;
import SoundwavesProject.Soundwaves.repository.UserRepository;
import SoundwavesProject.Soundwaves.service.ProductService;
//import SoundwavesProject.Soundwaves.service.OrderService;
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
    // @Autowired
    // private OrderService orderService;



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
        //model.addAttribute("cartOrderNo", AllData.cartOrder.size());
        return "index";
    }

    @GetMapping({"/products/category/{id}"})
    public String viewByCategoryHome(Model model, @PathVariable int id) { 
        model.addAttribute("categories", categoriesService.getAllCategory());
        model.addAttribute("products", productService.getProductByCatId(id));
        model.addAttribute("cartNo", AllData.cart.size());
        //model.addAttribute("cartOrderNo", AllData.cartOrder.size());
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
        //model.addAttribute("cartOrderNo", AllData.cartOrder.size());
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

    @GetMapping("/addCart/{id}") public String addCart(@PathVariable int id) //addCart = checkout page
    {
      AllData.cart.add(productService.getProductById(id).get());
      return "redirect:/products";
    }

    @GetMapping("/cart") public String getCart (Model model) //orderTable = cart table
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

   // Orders Table & Functionality

    // @GetMapping("/addCheckout/{id}") public String checkout(@PathVariable int id)
    // {
    //     AllData.cartOrder.add(orderService.getOrderByProductId(id).get());

    //   return "redirect:/products";
    // }

    // @GetMapping("/checkout") public String getCartOrder(Model model){

    //     model.addAttribute("cartOrderNo", AllData.cartOrder.size());
    //     double amount = AllData.cartOrder.stream().mapToDouble(Order::getTotalPrice).sum();
    //     model.addAttribute("amount", amount);
    //     model.addAttribute("cartOrder", AllData.cartOrder);
    //     return "cartOrder";
    // }

    // @GetMapping("/cartOrder/rmvOrdertItems/{index}") public String rmvOrderItems(@PathVariable int index)
    // {
    //     AllData.cartOrder.remove(index);
    //     return "redirect:/cart";
    // }
    
    // @GetMapping("/checkout")
    // public String checkout(Model model) { 
    //     double orderAmount = AllData.cartOrder.stream().mapToDouble(Order::getTotalPrice).sum();
    //     model.addAttribute("amount", orderAmount);
    //     return "checkout";

    // }
}

