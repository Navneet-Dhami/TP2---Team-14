package SoundwavesProject.Soundwaves.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)   
    @Column(name="category_id")
    private int id;

    private String categoryName;

    private String imageUrl;

    public Category(int id,  String categoryName, String imageUrl) {
      this.id = id;
      this.categoryName = categoryName;
      this.imageUrl = imageUrl;
    }

    public Category() {}

    Category speakers = new Category(1, "Speakers", "" );
    Category posters = new Category(2, "Posters", "" );
    Category headphones = new Category(3, "Headphones", "" );
    Category cds = new Category(4, "Cds", "" );
    Category vinyls = new Category(5, "Vinyls", "" );
   
} 