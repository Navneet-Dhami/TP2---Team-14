package SoundwavesProject.Soundwaves.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SoundwavesProject.Soundwaves.model.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    
    List<Wishlist> findByUserId(int userId);
   
}
