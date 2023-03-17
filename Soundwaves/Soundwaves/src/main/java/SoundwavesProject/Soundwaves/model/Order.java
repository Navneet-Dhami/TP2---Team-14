package SoundwavesProject.Soundwaves.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    @Column(name = "product_name")
    private String productName;
    
    @Column(name = "quantity")
    private int quantity;
    
    @Column(name = "total_amount")
    private double totalAmount;
    
    @Column(name = "date_ordered")
    private LocalDate dateOrdered;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;
    

    public enum OrderStatus {
        PENDING,
        SHIPPED,
        DELIVERED,
        CANCELLED
    }
    


}
