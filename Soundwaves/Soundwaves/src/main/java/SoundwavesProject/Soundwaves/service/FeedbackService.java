package SoundwavesProject.Soundwaves.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoundwavesProject.Soundwaves.model.Feedback;
import SoundwavesProject.Soundwaves.repository.FeedbackRepository;

@Service
public class FeedbackService {
    
    @Autowired
    private FeedbackRepository feedbackRepository;

    public void saveFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    public List<Feedback> getFeedback() 
    {
        return feedbackRepository.findAll();
    }

}
