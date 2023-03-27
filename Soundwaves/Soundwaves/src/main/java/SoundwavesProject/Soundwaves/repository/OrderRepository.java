package SoundwavesProject.Soundwaves.repository;

import java.time.LocalDate;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import SoundwavesProject.Soundwaves.model.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(long userId);
    List<Order> findByOrderStatusNot(Order.OrderStatus orderStatus);
    List<Order> findByDateOrderedAfterAndOrderStatusNot(LocalDate date, Order.OrderStatus orderStatus);
    List<Order> findByDateOrderedAfter(LocalDate oneMonthAgo);


   
}

