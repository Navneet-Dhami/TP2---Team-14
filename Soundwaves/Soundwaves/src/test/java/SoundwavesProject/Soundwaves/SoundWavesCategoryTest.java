package SoundwavesProject.Soundwaves;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import SoundwavesProject.Soundwaves.model.Category;

@SpringBootTest
public class SoundWavesCategoryTest {
    
    @Test
    void categoryTest(){
        Category category = new Category();
        category.setCategoryName("Headphones");
        category.setImageUrl("headphones.jpg");
        assertEquals("Headphones", category.getCategoryName());
        assertEquals("headphones.jpg", category.getImageUrl());
        
        
        

    }
}