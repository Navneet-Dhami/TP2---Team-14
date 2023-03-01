package SoundwavesProject.Soundwaves.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import SoundwavesProject.Soundwaves.model.Product;

@Repository
public interface SearchRepository extends JpaRepository<Product, Integer> {
    
    @Query(value = "SELECT * FROM product WHERE name LIKE %:keyword%", nativeQuery = true)

    List<Product> search(String keyword);
}
