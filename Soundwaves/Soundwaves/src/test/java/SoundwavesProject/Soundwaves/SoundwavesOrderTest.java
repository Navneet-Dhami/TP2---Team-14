package SoundwavesProject.Soundwaves;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import SoundwavesProject.Soundwaves.model.User;
import SoundwavesProject.Soundwaves.model.Order.OrderStatus;
import SoundwavesProject.Soundwaves.model.Order;

import SoundwavesProject.Soundwaves.model.Product;


@SpringBootTest
public class SoundwavesOrderTest {

    @Test
    void orderTest(){
    Product product = new Product();
    Order order = new Order();
    User user = new User();
    order.setUser(user);
    order.setProduct(product);
    order.setProductName("Headphones");
    order.setQuantity(0);
    order.setOrderStatus(OrderStatus.PENDING);
    order.setTotalAmount(50);
    assertEquals(user, order.getUser());
    assertEquals(product, order.getProduct());
    assertEquals("Headphones", order.getProductName());
    assertEquals(0, order.getQuantity());
    assertEquals(OrderStatus.PENDING, order.getOrderStatus());
    assertEquals(50, order.getTotalAmount());
    

   


    }
    
}
