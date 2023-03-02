// package SoundwavesProject.Soundwaves.model;

// import jakarta.persistence.*;
// import lombok.Data;

// import java.util.Date;

// @Entity
// @Data
// public class Order {

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     @Column(name = "order_id")
//     private Long id;

//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "product_id", referencedColumnName = "product_id")
//     private Product product;

//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "user_id", referencedColumnName = "user_id")
//     private User user;

//     @Column(name = "created_date")
//     private Date createdDate;

//     @Column(name = "total_price")
//     private double totalPrice;

//     private String img;

//     private int quantity;

//     private int tracking;

  
// }
