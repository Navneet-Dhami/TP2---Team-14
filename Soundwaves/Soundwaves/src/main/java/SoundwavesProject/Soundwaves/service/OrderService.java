package SoundwavesProject.Soundwaves.service;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoundwavesProject.Soundwaves.model.Order;
import SoundwavesProject.Soundwaves.model.Order.OrderStatus;
import SoundwavesProject.Soundwaves.repository.OrderRepository;
import SoundwavesProject.Soundwaves.repository.OrderSearchRepository;

@Service
public class OrderService implements OrderServiceInterface {

    @Autowired
    OrderRepository orderRepository;

    @Autowired UpdatesService updatesService;

    @Autowired
    OrderSearchRepository orderSearchRepository;

    @Override
    public void createOrder(Order order) {
        orderRepository.save(order);
    }

  

    @Override
    public List<Order> getOrdersByUserId(long userId) {
        return orderRepository.findByUserId(userId);
    }
    

    @Override 
    public List<Order> getOrders() { 
        return orderRepository.findAll();
    }

    public void rmvOrder(long id)
    {
     orderRepository.deleteById(id);
    }

    public void updateOrderStatus(long id, OrderStatus orderStatus) {
        Order order = orderRepository.findById(id).get();
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);

        if (orderStatus == OrderStatus.DELIVERED) {
            updatesService.sendDeliveryUpdate(order);
        }

    }



    public double getTotalSalesLast24Hours() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        List<Order> orders = orderRepository.findByDateOrderedAfterAndOrderStatusNot(yesterday, Order.OrderStatus.CANCELLED);
        double totalSales = orders.stream().mapToDouble(Order::getTotalAmount).sum();
        return totalSales;
    }

    public double getTotalSales() {
        List<Order> orders = orderRepository.findByOrderStatusNot(Order.OrderStatus.CANCELLED);
        double totalSales = orders.stream().mapToDouble(Order::getTotalAmount).sum();
        return totalSales;
    }
    
    public List<Order> searchKeyword(String keyword)
    {
        return orderSearchRepository.search(keyword);
    }

    
    
}