package SoundwavesProject.Soundwaves.model;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Order {
    
    //product id & quantity & user id created date, tracking(should say in the tracking column dispatched or not yet), totalprice, img
    //link to product id and user id (FK to product & user tables)

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "productOrder_id")
    private int productId;
    private String name;
    private Date createdDate;
    private double totalPrice;
    private String img;
    private int quantity;
    @Column(name = "user_id")
    private int userId;
    private int tracking;

   //@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productOrder_id",referencedColumnName = "product_id",insertable = false,updatable = false)
    private Product product;

    //@ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;

    
}
