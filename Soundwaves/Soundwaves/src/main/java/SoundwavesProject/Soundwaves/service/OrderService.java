package SoundwavesProject.Soundwaves.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoundwavesProject.Soundwaves.model.Order;
import SoundwavesProject.Soundwaves.repository.OrderRepository;

@Service
public class OrderService implements OrderServiceInterface {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public void createOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByUserId(long userId) {
        return orderRepository.findByUserId(userId);
    }
    
}