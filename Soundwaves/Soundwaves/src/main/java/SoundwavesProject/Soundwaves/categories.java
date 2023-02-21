package SoundwavesProject.Soundwaves;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="categories")
public class categoryDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="Name")
    private String categoryName;

    public void setId(int id) { 
        this.id = id;
    }

    public int getId() { 
        return this.id;
    }

    public String setName(String categoryName) { 
        return this.categoryName = categoryName;
    }

    public String getName() { 
        return this.categoryName;
    }

    

} 