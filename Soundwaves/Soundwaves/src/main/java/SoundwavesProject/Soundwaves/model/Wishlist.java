package SoundwavesProject.Soundwaves.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="wishlist")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public Wishlist() {
    }

    public Wishlist(User user, Product product) {
        this.user = user;
        this.product = product;
    }

  
}


    

