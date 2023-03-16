package SoundwavesProject.Soundwaves;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import SoundwavesProject.Soundwaves.model.User;

@SpringBootTest
public class SoundWavesUserTest {


    @Test
	void testUserDetails() { 
		User user = new User();
		user.setFirstname("User");
		user.setSurname("Soundwaves");
        user.setEmail("user@soundwaves.com");
        user.setPassword("123456");
        user.setUsername("soundwavesUser");

		assertEquals("User", user.getFirstname());
		assertEquals("Soundwaves", user.getSurname());
        assertEquals("user@soundwaves.com", user.getEmail());
        assertEquals("123456", user.getPassword());
        assertEquals("soundwavesUser", user.getUsername());
	}

    @Test
    void testUserRole() { 
        User user = new User();
        user.setRole("USER");

        User admin = new User();
        admin.setRole("ADMIN");

        assertEquals("USER", user.getRole());
        assertEquals("ADMIN", admin.getRole());
    }
}
