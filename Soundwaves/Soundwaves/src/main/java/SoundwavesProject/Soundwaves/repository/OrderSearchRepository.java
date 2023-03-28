package SoundwavesProject.Soundwaves.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import SoundwavesProject.Soundwaves.model.Order;

@Repository
public interface OrderSearchRepository extends JpaRepository<Order, Integer> {
    
    @Query(value = "SELECT * FROM orders WHERE product_name LIKE %:keyword%", nativeQuery = true)

    List<Order> search(String keyword);
}
