package SoundwavesProject.Soundwaves;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import SoundwavesProject.Soundwaves.model.Product;
import SoundwavesProject.Soundwaves.model.User;
import SoundwavesProject.Soundwaves.model.Wishlist;

@SpringBootTest
public class SoundwavesWishlistTest {

    @Test
    void wishlistTest()
    {
        Wishlist wishlist = new Wishlist();
        Product product = new Product();
        User user = new User();
        wishlist.setProduct(product);
        wishlist.setUser(user);
        assertEquals(product, wishlist.getProduct());
        assertEquals(user, wishlist.getUser());
        
    }
    
}
