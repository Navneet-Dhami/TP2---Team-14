package SoundwavesProject.Soundwaves.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoundwavesProject.Soundwaves.model.Wishlist;
import SoundwavesProject.Soundwaves.repository.WishlistRepository;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;
    
    public void addToWishlist(Wishlist wishlistItem) {
        wishlistRepository.save(wishlistItem);
    }

    public List<Wishlist> getWishlist() {
        return wishlistRepository.findAll();
    }

    public void rmvWish(long id)
    {
     wishlistRepository.deleteById(id);
    }

    public List<Wishlist> getWishlist(int userId) {
        return wishlistRepository.findByUserId(userId);
    }
    


}
