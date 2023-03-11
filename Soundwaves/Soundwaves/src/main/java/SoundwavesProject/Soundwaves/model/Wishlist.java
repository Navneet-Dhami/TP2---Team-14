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
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public Wishlist(){

    }

    public Wishlist(Product product) {
        this.product = product;
    }

    
}
