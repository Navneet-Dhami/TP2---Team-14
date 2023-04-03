package SoundwavesProject.Soundwaves.model;

import java.time.LocalDate;

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
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private String review;
    private Long rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    private LocalDate date;

    public User getUser(){
        return this.user;
    }

    public String getReview(){
        return this.review;
    }

    public Long getRating(){
        return this.rating;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setRating(long rating){
        this.rating = rating;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public Review() {
    }

    public Review(Long id, User user, String review, long rating, Product product) {
        this.id = id;
        this.user = user;
        this.review = review;
        this.rating = rating;
        this.product = product;
    }
}
