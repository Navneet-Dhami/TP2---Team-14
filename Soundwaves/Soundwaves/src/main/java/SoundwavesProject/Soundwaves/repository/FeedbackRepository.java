package SoundwavesProject.Soundwaves.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SoundwavesProject.Soundwaves.model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long>{
    
}
