package SoundwavesProject.Soundwaves.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoundwavesProject.Soundwaves.model.Order;
import SoundwavesProject.Soundwaves.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<Order> getOrder() {

        return orderRepository.findAll();
    }
    public void addOrder(Order order){

        orderRepository.save(order);
    }
    public void rmvOrder(int id){

        orderRepository.deleteById(id);
    }
    public Optional<Order> getOrderByProductId(int productId){
        
        return orderRepository.findById(productId);
    } 

    public List<Order> getOrderByUserId(int userId){

        return orderRepository.findAllOrdersByUserId(userId);
    }    
}