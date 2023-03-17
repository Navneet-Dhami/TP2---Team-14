package SoundwavesProject.Soundwaves.service;

import java.util.List;


import SoundwavesProject.Soundwaves.model.Order;

public interface OrderServiceInterface {
    void createOrder(Order order);
   
    List<Order> getOrdersByUserId(long userId);
    List<Order> getOrders();
}
