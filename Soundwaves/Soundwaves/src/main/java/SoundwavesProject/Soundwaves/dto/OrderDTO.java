package SoundwavesProject.Soundwaves.dto;
import java.sql.Date;
import lombok.Data;


@Data
public class OrderDTO {
    private Long id;
    private int productId;
    private String name;
    private Date createdDate;
    private double totalPrice;
    private String img;
    private int quantity;
    private int userId;
    private int tracking;

    
}