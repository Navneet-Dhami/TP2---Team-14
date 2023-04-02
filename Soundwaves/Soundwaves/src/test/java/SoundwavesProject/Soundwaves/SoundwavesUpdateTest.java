package SoundwavesProject.Soundwaves;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import SoundwavesProject.Soundwaves.model.Updates;

@SpringBootTest
public class SoundwavesUpdateTest {

    @Test
    void updatesTest()
    {
        LocalDateTime createdAt = null;
        Updates updates = new Updates();
        updates.setMessage("Updated!");
        updates.setUsername("Micheal");
        updates.setCreatedAt(createdAt);
        assertEquals("Updated!", updates.getMessage());
        assertEquals("Micheal", updates.getUsername());
        assertEquals(createdAt, updates.getCreatedAt());
    }
    
}
