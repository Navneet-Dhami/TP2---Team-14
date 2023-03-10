package SoundwavesProject.Soundwaves.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private Long id;
    private String name;

    private int categoryId;
    private double price;
    private int stock;
    private String description;
    private int quantity;
    private String img;

}

