package SoundwavesProject.Soundwaves.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoundwavesProject.Soundwaves.model.Review;
import SoundwavesProject.Soundwaves.repository.ReviewRepository;

@Service
public class ReviewService {
    
    @Autowired
    ReviewRepository reviewRepository;

    public void createReview(Review review) {
       reviewRepository.save(review);
    }

    public List<Review> getReview(long id) {
         return reviewRepository.searchReviewByProduct_Id(id);
    }
}
