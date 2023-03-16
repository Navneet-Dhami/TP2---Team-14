package SoundwavesProject.Soundwaves;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import SoundwavesProject.Soundwaves.model.Product;


@SpringBootTest
public class SoundWavesProductTest {

    
    @Test
    void productTest(){
     Product product = new Product();
     product.setName("Headphones V2");
     product.setDescription("Version 2 headphones. New and improved.");
     product.setPrice(52);
     product.setStock(8);
     product.setImg("something.jpg");
     assertEquals("Headphones V2", product.getName());
     assertEquals("Version 2 headphones. New and improved.", product.getDescription());
     assertEquals(52, product.getPrice());
     assertEquals(8, product.getStock());
     assertEquals("something.jpg", product.getImg());


    }
}
