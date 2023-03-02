package SoundwavesProject.Soundwaves.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoundwavesProject.Soundwaves.model.Product;
import SoundwavesProject.Soundwaves.repository.ProductRepository;
import SoundwavesProject.Soundwaves.repository.SearchRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SearchRepository searchRepository;

    public List<Product> searchKeyword(String keyword)
    {
        return searchRepository.search(keyword);
    }

    public List<Product> getProduct() 
    {
        return productRepository.findAll();
    }
    public void addProduct(Product product)
    {
     productRepository.save(product);
    }
    public void rmvProduct(long id)
    {
     productRepository.deleteById(id);
    }
    public Optional<Product> getProductById(long id)
    {
        return productRepository.findById(id);
    } 

     public List<Product> getProductByCatId(int id)
    {
         return productRepository.searchProdByCategory_Id(id);
    }
    
}