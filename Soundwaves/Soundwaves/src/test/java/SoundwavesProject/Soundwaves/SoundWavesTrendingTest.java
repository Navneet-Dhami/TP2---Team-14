package SoundwavesProject.Soundwaves;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import SoundwavesProject.Soundwaves.model.TrendingModel;

@SpringBootTest
public class SoundWavesTrendingTest {

    @Test
    void reviewTest() {
        TrendingModel model = new TrendingModel("order");
        assertEquals("order", model.getFilter());
        model.setFilter("rating");
        assertEquals("rating", model.getFilter());

    }
}