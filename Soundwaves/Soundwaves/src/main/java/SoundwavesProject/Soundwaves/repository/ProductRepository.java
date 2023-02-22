package SoundwavesProject.Soundwaves.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import SoundwavesProject.Soundwaves.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

   List<Product> searchProdByCategory_Id(int id);
    
}
