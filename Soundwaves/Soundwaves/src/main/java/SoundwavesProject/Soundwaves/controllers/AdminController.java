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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import SoundwavesProject.Soundwaves.dto.ProductDTO;
import SoundwavesProject.Soundwaves.model.Product;
import SoundwavesProject.Soundwaves.service.ProductService;
import SoundwavesProject.Soundwaves.service.categoriesService;



@Controller
public class AdminController {
 
    public static String findDir = System.getProperty("user.dir") + "/Soundwaves/Soundwaves/src/main/resources/static/media";
   
    @Autowired
    categoriesService categoriesService;

    @Autowired
    ProductService productService;

    @GetMapping("/admin")
    public String adminHome(){
        return "admin.html";
    }

    @GetMapping("/admin/products")
    public String products(Model model){
        model.addAttribute("products", productService.getProduct());
        return "adminproducts.html";
    }


    @GetMapping("/admin/products/add")
    public String addProduct(Model model){
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoriesService.getAllCategory());
        return "adminaddproduct.html";
    }
    
    @PostMapping("/admin/products/add")
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
       String uuidDir;

       if(!file.isEmpty())
       {
        uuidDir = file.getOriginalFilename();
        Path fileNameAndPath = Paths.get(findDir, uuidDir);
        Files.write(fileNameAndPath, file.getBytes());
       } else {

        uuidDir = imgName;

       }

       product.setImg(uuidDir);
       productService.addProduct(product);



        return "redirect:/admin/products";
    }

    
}


