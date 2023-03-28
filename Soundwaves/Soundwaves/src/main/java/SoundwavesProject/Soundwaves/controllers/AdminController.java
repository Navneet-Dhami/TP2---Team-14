package SoundwavesProject.Soundwaves.controllers;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import SoundwavesProject.Soundwaves.dto.ProductDTO;
import SoundwavesProject.Soundwaves.model.Date;
import SoundwavesProject.Soundwaves.model.Order;
import SoundwavesProject.Soundwaves.model.Product;
import SoundwavesProject.Soundwaves.model.Updates;
import SoundwavesProject.Soundwaves.repository.OrderRepository;
import SoundwavesProject.Soundwaves.service.FeedbackService;
import SoundwavesProject.Soundwaves.service.OrderService;
import SoundwavesProject.Soundwaves.service.ProductService;
import SoundwavesProject.Soundwaves.service.UpdatesService;
import SoundwavesProject.Soundwaves.service.UserService;
import SoundwavesProject.Soundwaves.service.categoriesService;



@Controller
public class AdminController {
 
    public static String findDir = System.getProperty("user.dir") + "/Soundwaves/Soundwaves/src/main/resources/static/media";
   
    @Autowired
    categoriesService categoriesService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    FeedbackService feedbackService;

    @Autowired 
    OrderService orderService;

    @Autowired
    UpdatesService updatesService;

    @Autowired
    OrderRepository orderRepository;
  

    @GetMapping("/admin/adminHome")
    public String adminHome(Model model) {
    double totalSales24 = orderService.getTotalSalesLast24Hours();
    double totalSales = orderService.getTotalSales();
    List<Updates> updates = updatesService.getRecentUpdates();
    List<Order> orders = orderRepository.findAll();
    int totalOrders = orders.size();
    model.addAttribute("totalOrders", totalOrders);
    model.addAttribute("updates", updates);
    model.addAttribute("ordersLast24HoursTotal", totalSales24);
    model.addAttribute("ordersAllTime", totalSales);
    return "admin/adminHome";
    }


    @GetMapping("/admin/feedback")
    public String feedback(Model model){
        model.addAttribute("feedback", feedbackService.getFeedback());
        List<Updates> updates = updatesService.getRecentUpdates();
        model.addAttribute("updates", updates);
        return "/admin/adminFeedback";

    }

    @GetMapping("/admin/products")
    public String products(Model model){
        model.addAttribute("products", productService.getProduct());
        List<Updates> updates = updatesService.getRecentUpdates();
        model.addAttribute("updates", updates);
        return "admin/adminproducts";
    }

    @GetMapping("/admin/order")
    public String orders(Model model){
        model.addAttribute("orders", orderService.getOrders());
        List<Updates> updates = updatesService.getRecentUpdates();
        model.addAttribute("updates", updates);
        return "admin/adminOrder";
    }

    @GetMapping("/admin/adminUserView")
    public String adminuserView(Model model){
        model.addAttribute("users", userService.getUser());
        List<Updates> updates = updatesService.getRecentUpdates();
        model.addAttribute("updates", updates);
        return "admin/adminUserView";
    }

    @GetMapping("/admin/adminReport")
    public String adminReport(Model model) { 
        Date date = new Date();
        List<Updates> updates = updatesService.getRecentUpdates();
        model.addAttribute("updates", updates);
        model.addAttribute("date", date);
        model.addAttribute("products", productService.getProduct());
        model.addAttribute("orders", orderService.getOrders());
        return "admin/adminReport";
    }

    @GetMapping("/admin/adminPendingOrders")
    public String adminPendingOrders(Model model) { 
        model.addAttribute("orders", orderService.getOrders());
        return "admin/adminPendingOrders";
    }

    @GetMapping("/admin/adminShippedOrders")
    public String adminShippedOrders(Model model) { 
        model.addAttribute("orders", orderService.getOrders());
        return "admin/adminShippedOrders";
    }

    @GetMapping("/admin/adminDeliveredOrders")
    public String adminDeliveredOrders(Model model) { 
        model.addAttribute("orders", orderService.getOrders());
        return "admin/adminDeliveredOrders";
    }

    @GetMapping("/admin/adminCancelledOrders")
    public String adminCancelledOrders(Model model) { 
        model.addAttribute("orders", orderService.getOrders());
        return "admin/adminCancelledOrders";
    }

    @GetMapping("/searchOrder")
    public String searchOrder(@Param("keyword") String keyword, Model model) {
        System.out.println("keyword: " + keyword);

        List<Order> searchResult = orderService.searchKeyword(keyword);
        model.addAttribute("keyword", keyword);
        model.addAttribute("searchResult", searchResult);
        model.addAttribute("pageTitle", "Search Results For " + keyword);
        return "admin/adminOrderSearch";
    }

    @GetMapping("/searchProduct")
    public String searchProduct(@Param("keyword") String keyword, Model model) {
        System.out.println("keyword: " + keyword);
        List<Product> searchResult = productService.searchKeyword(keyword);
        model.addAttribute("keyword", keyword);
        model.addAttribute("searchResult", searchResult);
        model.addAttribute("pageTitle", "Search Results For " + keyword);
        return "admin/adminProductSearch";
    }


    @GetMapping("/admin/user/remove/{id}") 
    public String removeProduct(@PathVariable int id) { 
        userService.removeUser(id);
        return "redirect:/admin/adminUserView";
    }


    @GetMapping("/admin/addproduct")
    public String addProduct(Model model){
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoriesService.getAllCategory());
        return "admin/adminaddproduct";
    }
    
    @PostMapping("/admin/addproduct")
    public String addProductPost(@ModelAttribute("productDTO") ProductDTO productDTO, 
    @RequestParam("media") MultipartFile file, 
    @RequestParam("imgName") String imgName) throws IOException {

       Product product = new Product();
       product.setId(productDTO.getId());
       product.setName(productDTO.getName());
       product.setCategory(categoriesService.getCatId(productDTO.getCategoryId()).get());
       product.setPrice(productDTO.getPrice());
       product.setStock(productDTO.getStock());
       product.setDescription(productDTO.getDescription());


       //Unique identifiter for image = uuidNme
       //Directory is linked to findDir, in which the Path 'filePathName' takes in the value of the image name (the uuid) and the directory (findDir) to write operation

       String uuidNme;

       if(!file.isEmpty())
       {
        uuidNme = file.getOriginalFilename();
        Path filePathName = Paths.get(findDir, uuidNme);
        Files.write(filePathName, file.getBytes());
       } else {

        uuidNme = imgName;

       }

       product.setImg(uuidNme);
       productService.addProduct(product);



        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/remove/{id}") public String removeProduct(@PathVariable long id)
    {
      productService.rmvProduct(id);
      return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/modify/{id}") public String modifyProduct(@PathVariable long id, Model model)
    {
      Product product = productService.getProductById(id).get();
      ProductDTO productDTO = new ProductDTO();

      productDTO.setId(product.getId());
      productDTO.setName(product.getName());
      productDTO.setCategoryId(product.getCategory().getId());
      productDTO.setPrice(product.getPrice());
      productDTO.setStock(product.getStock());
      productDTO.setDescription(product.getDescription());
      productDTO.setImg(product.getImg());

      model.addAttribute("categories", categoriesService.getAllCategory());
      model.addAttribute("productDTO", productDTO);
      

      return "admin/adminaddproduct";
    }

    @PostMapping("/admin/order/updateStatus/{id}")
    public String updateOrderStatus(@PathVariable long id, @RequestParam("orderStatus") Order.OrderStatus orderStatus) {
    orderService.updateOrderStatus(id, orderStatus);
    return "redirect:/admin/order";
    }

    @GetMapping("/admin/order/remove/{id}") public String removeOrder(@PathVariable long id)
    {
      orderService.rmvOrder(id);
      return "redirect:/admin/order";
    }

    

    
}


