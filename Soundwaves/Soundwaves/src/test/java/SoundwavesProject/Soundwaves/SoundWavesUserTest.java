package SoundwavesProject.Soundwaves;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import SoundwavesProject.Soundwaves.model.User;

@SpringBootTest
public class SoundWavesUserTest {

    @Test
	void testUser() { 
		User u = new User();
		u.setFirstname("User");
		u.setSurname("Soundwaves");
		assertEquals("User", u.getFirstname());
		assertEquals("Soundwaves", u.getSurname());
	}
}
