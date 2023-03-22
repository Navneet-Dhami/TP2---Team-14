package SoundwavesProject.Soundwaves.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SoundwavesProject.Soundwaves.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{

   List<Review> searchReviewByProduct_Id(long id);

}

