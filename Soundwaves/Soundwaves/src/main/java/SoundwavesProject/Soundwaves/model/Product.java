package SoundwavesProject.Soundwaves.model;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Product{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name="product_id")
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;
    private double price;
    private int stock;
    private String description;
    private int quantity;
    private String img;
    private int minimum;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Category getCategory() {
        return this.category;
    }

    public double getPrice() {
        return this.price;
    }

    public int getStock() {
        return this.stock;
    }

    public String getDesc() {
        return this.description;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String getImg() {
        return this.img;
    }

    public Product() {
    }

    public Product(Long id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;

    }
}