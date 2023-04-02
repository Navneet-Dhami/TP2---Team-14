package SoundwavesProject.Soundwaves.controllers;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



import SoundwavesProject.Soundwaves.global.AllData;
import SoundwavesProject.Soundwaves.model.Feedback;
import SoundwavesProject.Soundwaves.model.Order;
import SoundwavesProject.Soundwaves.model.Product;
import SoundwavesProject.Soundwaves.model.Review;
import SoundwavesProject.Soundwaves.model.SoundWavesUserDetails;
import SoundwavesProject.Soundwaves.model.TrendingModel;

import SoundwavesProject.Soundwaves.model.User;
import SoundwavesProject.Soundwaves.model.Wishlist;
import SoundwavesProject.Soundwaves.model.Order.OrderStatus;
import SoundwavesProject.Soundwaves.repository.ProductRepository;
import SoundwavesProject.Soundwaves.repository.ReviewRepository;
import SoundwavesProject.Soundwaves.repository.UserRepository;
import SoundwavesProject.Soundwaves.service.FeedbackService;
import SoundwavesProject.Soundwaves.service.OrderService;
import SoundwavesProject.Soundwaves.service.ProductService;
import SoundwavesProject.Soundwaves.service.ReviewService;
import SoundwavesProject.Soundwaves.service.UpdatesService;
import SoundwavesProject.Soundwaves.service.TrendingProductsService;
import SoundwavesProject.Soundwaves.service.UserService;
import SoundwavesProject.Soundwaves.service.WishlistService;

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

    @Autowired
    FeedbackService feedbackService;

    @Autowired
    UserService userService;

    @Autowired
    WishlistService wishlistService;

    @Autowired
    OrderService orderService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ReviewService reviewService;

    @Autowired
    UpdatesService updatesService;
  
    @Autowired
    TrendingProductsService trendingProductsService;


    @GetMapping("/search")
    public String search(@Param("keyword") String keyword, Model model) {
        System.out.println("keyword: " + keyword);

        List<Product> searchResult = productService.searchKeyword(keyword);
        model.addAttribute("keyword", keyword);
        model.addAttribute("searchResult", searchResult);
        model.addAttribute("pageTitle", "Search Results For " + keyword);
        return "search_results";

    }

    @GetMapping("/productView/{id}")
    public String productView(@PathVariable Long id, Model model) {

        Product product = productService.getProductById(id).get();
        List<Review> review = reviewService.getReview(id);
        model.addAttribute("product", product);
        model.addAttribute("review", review);
        model.addAttribute("addReview", new Review());
        return "product_view";

    }

    @PostMapping("/productView/{id}/review")
    public String addReview(@PathVariable Long id, Model model, @ModelAttribute("addReview") Review review) {

        //Code from Orders
        SoundWavesUserDetails userDetails = (SoundWavesUserDetails)
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId = userDetails.getUserId();
        Optional<User> user = userService.getUserById(userId);
        //Sets the new review
        Review reviewNew = new Review();
        reviewNew.setUser(user.get());
        reviewNew.setRating(review.getRating());
        System.out.print(review.getReview());
        reviewNew.setReview(review.getReview());
        reviewNew.setProduct(productService.getProductById(id).get());
        reviewNew.setDate(LocalDate.now());
        reviewService.createReview(reviewNew);

        return "redirect:/productView/{id}";

    }

    @GetMapping({"/", "/index"})
    public String home(Model model, @ModelAttribute("changeFilter") TrendingModel filter) { 
        
        List<Product> trendingProducts;
        if (filter.getFilter() != null && filter.getFilter().equals("orders")) {
            trendingProducts = trendingProductsService.getTrendingProductsByOrders();
        } else {
            trendingProducts = trendingProductsService.getTrendingProducts();
        }
        model.addAttribute("trendingProducts", trendingProducts);
    

        model.addAttribute("categories", categoriesService.getAllCategory());
        model.addAttribute("cartNo", AllData.cart.size());
        //model.addAttribute("cartOrderNo", AllData.cartOrder.size());
        return "index";
    }

    @PostMapping({ "/", "/index" })
    public String homeFilter(Model model, @ModelAttribute("changeFilter") TrendingModel filter) {

        List<Product> trendingProducts;
        if (filter.getFilter().equals("orders")) {
            trendingProducts = trendingProductsService.getTrendingProductsByOrders();
        } else {
            trendingProducts = trendingProductsService.getTrendingProducts();
        }
        model.addAttribute("trendingProducts", trendingProducts);

        model.addAttribute("categories", categoriesService.getAllCategory());
        model.addAttribute("cartNo", AllData.cart.size());
        // model.addAttribute("cartOrderNo", AllData.cartOrder.size());
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

    @GetMapping("/userPage")
    public String userPage(Model model) { 
        SoundWavesUserDetails userDetails = (SoundWavesUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = userDetails.getUsername();
        model.addAttribute("users", userService.loadUserByUsername(userName));
        return "userPage";
    }

    @GetMapping("/userOrders")
    public String userOrders(Model model) { 
        SoundWavesUserDetails userDetails = (SoundWavesUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId = userDetails.getUserId();
        String userName = userDetails.getUsername();
        model.addAttribute("orders", orderService.getOrdersByUserId(userId));
        model.addAttribute("users", userService.loadUserByUsername(userName));
        return "userOrders";
    }

    @PostMapping("/contact")
    public String submitFeedback(@RequestParam String name, @RequestParam String email, @RequestParam String subject, @RequestParam String message) {
        Feedback feedback = new Feedback();
        feedback.setName(name);
        feedback.setEmail(email);
        feedback.setSubject(subject);
        feedback.setMessage(message);
    
        feedbackService.saveFeedback(feedback);
    
        updatesService.sendFeedbackUpdate(feedback);
    
        return "redirect:/contact";
    }
    

    @GetMapping("/addCart/{id}")
    public String addCart(@PathVariable int id) {
        Product product = productService.getProductById(id).get();
        boolean duplicateProduct = false;
        for (Product p : AllData.cart) {
            if (p.getId() == product.getId()) {
                duplicateProduct = true;
                break;
            }
        }
        if (duplicateProduct) {
            
        } else {
            product.setQuantity(1); 
            AllData.cart.add(product);
        }
        return "redirect:/products";
    }
    
    
    @GetMapping("/cart")
    public String getCart(Model model) {
        model.addAttribute("cartNo", AllData.cart.size());
        double amount = AllData.cart.stream()
            .mapToDouble(item -> item.getPrice() * item.getQuantity())
            .sum();
        model.addAttribute("amount", amount);
        model.addAttribute("cart", AllData.cart);
        return "Cart";
    }
    
    @GetMapping("/cart/rmvItems/{index}")
    public String rmvItems(@PathVariable int index) {
        AllData.cart.remove(index);
        return "redirect:/cart";
    }
    
    @PostMapping("/cart/updateQuantity")
    public String updateQuantity(@RequestParam("quantity") List<Integer> quantities) {
        for (int i = 0; i < AllData.cart.size(); i++) {
            AllData.cart.get(i).setQuantity(quantities.get(i));
        }
        return "redirect:/cart";
    }

    @GetMapping("/wishlist")
    public String getWishlist(Model model, Authentication authentication) {
        int userId = ((SoundWavesUserDetails) authentication.getPrincipal()).getUserId();
        model.addAttribute("wishlists", wishlistService.getWishlist(userId));
        return "wishlist";
    }
    


    @GetMapping("/addToWishlist/{id}")
    public String addToWishlist(@PathVariable int id, Authentication authentication) {

    int userId = ((SoundWavesUserDetails) authentication.getPrincipal()).getUserId();

    Optional<Product> productId = productService.getProductById(id);
    Product product = productId.get();
    User user = userService.getUserById(userId).get();
    Wishlist wishlistItem = new Wishlist(user, product);
    wishlistService.addToWishlist(wishlistItem);

    return "redirect:/products";
}


@GetMapping("/wishlist/remove/{id}") public String removeWish(@PathVariable long id)
{
  wishlistService.rmvWish(id);
  return "redirect:/wishlist";
}

    
    @GetMapping("/checkout")
    public String checkout(Model model) {
 
    SoundWavesUserDetails userDetails = (SoundWavesUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    int userId = userDetails.getUserId();
    Optional<User> user = userService.getUserById(userId);

   
    List<Product> itemsInCart = AllData.cart;


    double totalAmount = itemsInCart .stream()
            .mapToDouble(item -> item.getPrice() * item.getQuantity())
            .sum();


    Order order = new Order();
    order.setUser(user.get());
    order.setOrderStatus(OrderStatus.PENDING);
    order.setDateOrdered(LocalDate.now());
    order.setTotalAmount(totalAmount);


    for (Product item : itemsInCart ) {
        int quantity = item.getQuantity();
        int updatedStock = item.getStock() - quantity;
       
        item.setStock(updatedStock);
        productRepository.save(item);

        Order orderDetail = new Order();
        orderDetail.setUser(user.get());
        orderDetail.setOrderStatus(OrderStatus.PENDING);
        orderDetail.setDateOrdered(LocalDate.now());
        orderDetail.setTotalAmount(item.getPrice() * quantity);
        orderDetail.setQuantity(quantity);
        orderDetail.setProductName(item.getName());
        orderDetail.setProduct(item);
        orderService.createOrder(orderDetail);

        updatesService.sendOrderUpdate(orderDetail);
     }


    AllData.cart.clear();

    
    return "redirect:/userOrders";
    }





}

