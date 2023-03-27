package SoundwavesProject.Soundwaves.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Updates {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String username;
    private String message;
 
    @CreationTimestamp
    private LocalDateTime createdAt;
    

    public Updates() {
       
    }
 
    public Updates(String message, LocalDateTime createdAt, String userName) {
        this.message = message;
        this.createdAt = createdAt;
        this.username = userName;
    }
 
}
