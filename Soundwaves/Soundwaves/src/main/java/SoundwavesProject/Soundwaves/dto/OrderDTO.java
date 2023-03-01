package SoundwavesProject.Soundwaves.dto;
import java.sql.Date;
import lombok.Data;


@Data
public class OrderDTO {
    private Long id;
    private Long productId;
    private int userId;

    private Date createdDate;
    private double totalPrice;
    private String img;
    private int quantity;
    private int tracking;

    
}