package SoundwavesProject.Soundwaves.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoundwavesProject.Soundwaves.model.Feedback;
import SoundwavesProject.Soundwaves.model.Order;
import SoundwavesProject.Soundwaves.model.Updates;
import SoundwavesProject.Soundwaves.repository.UpdatesRepository;

@Service
public class UpdatesService {

    @Autowired
    private UpdatesRepository updatesRepository;

    public void sendOrderUpdate(Order order) {
        String message = String.format("%s created a new order", order.getUser().getUsername());
        Updates update = new Updates(message, LocalDateTime.now(), order.getUser().getUsername());
        updatesRepository.save(update);
    }

    public void sendDeliveryUpdate(Order order) {
        String message = String.format("%s's order has been delivered", order.getUser().getUsername());
        Updates update = new Updates(message, LocalDateTime.now(), order.getUser().getUsername());
        updatesRepository.save(update);
    }

    public void sendFeedbackUpdate(Feedback feedback) {
        String message = String.format("%s submitted a new feedback", feedback.getName());
        Updates update = new Updates(message, LocalDateTime.now(), feedback.getName());
        updatesRepository.save(update);
    }

    public List<Updates> getRecentUpdates() {
        return updatesRepository.findTop10ByOrderByIdDesc();
    }
}

