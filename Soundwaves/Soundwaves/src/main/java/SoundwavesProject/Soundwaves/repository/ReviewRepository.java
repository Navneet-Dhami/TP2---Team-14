package SoundwavesProject.Soundwaves.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SoundwavesProject.Soundwaves.model.Product;
import SoundwavesProject.Soundwaves.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{

   List<Review> searchReviewByProduct_Id(long id);

   List<Review> findByProductAndDateAfter(Product product, LocalDate startDate);

}

