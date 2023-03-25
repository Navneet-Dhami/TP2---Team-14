package SoundwavesProject.Soundwaves.controllers;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
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
import SoundwavesProject.Soundwaves.service.FeedbackService;
import SoundwavesProject.Soundwaves.service.OrderService;
import SoundwavesProject.Soundwaves.service.ProductService;
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

   

    @GetMapping("/admin/adminHome")
    public String adminHome(Model model) {
    double totalSales24 = orderService.getTotalSalesLast24Hours();
    double totalSales = orderService.getTotalSales();
    model.addAttribute("ordersLast24HoursTotal", totalSales24);
    model.addAttribute("ordersAllTime", totalSales);
    return "admin/adminHome";
    }


    @GetMapping("/admin/feedback")
    public String feedback(Model model){
        model.addAttribute("feedback", feedbackService.getFeedback());
        return "/admin/adminFeedback";

    }

    @GetMapping("/admin/products")
    public String products(Model model){
        model.addAttribute("products", productService.getProduct());
        return "admin/adminproducts";
    }

    @GetMapping("/admin/order")
    public String orders(Model model){
        model.addAttribute("orders", orderService.getOrders());
        return "admin/adminOrder";
    }

    @GetMapping("/admin/adminUserView")
    public String adminuserView(Model model){
        model.addAttribute("users", userService.getUser());
        return "admin/adminUserView";
    }

    @GetMapping("/admin/adminReport")
    public String adminReport(Model model) { 
        Date date = new Date();
        model.addAttribute("date", date);
        model.addAttribute("products", productService.getProduct());
        model.addAttribute("orders", orderService.getOrders());
        return "admin/adminReport";
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


