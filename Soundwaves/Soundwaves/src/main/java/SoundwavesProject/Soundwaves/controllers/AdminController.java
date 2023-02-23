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

    @GetMapping("/admin/adminHome") 
    public String adminHome() { 
        return "admin/adminHome";
    }

    @GetMapping("/admin/products")
    public String products(Model model){
        model.addAttribute("products", productService.getProduct());
        return "admin/adminproducts";
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

    
}


