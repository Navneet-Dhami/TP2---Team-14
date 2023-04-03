package SoundwavesProject.Soundwaves;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import SoundwavesProject.Soundwaves.model.Review;
import SoundwavesProject.Soundwaves.model.User;

@SpringBootTest
public class SoundWavesReviewTest {

    @Test
    void reviewTest() {
        Review review = new Review();
        User user = new User();
        LocalDate date = LocalDate.now();
        review.setUser(user);
        review.setReview("review");
        review.setRating(5);
        review.setDate(date);
        assertEquals(user, review.getUser());
        assertEquals("review", review.getReview());
        assertEquals(5, review.getRating());
        assertEquals(date, review.getDate());
    }
}
