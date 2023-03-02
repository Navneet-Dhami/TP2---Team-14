package SoundwavesProject.Soundwaves.global;

import java.util.ArrayList;
import java.util.List;

import SoundwavesProject.Soundwaves.model.Product;
// import SoundwavesProject.Soundwaves.model.Order;

public class AllData {

    public static List<Product> cart;
    //public static List<Order> cartOrder;

    static{
        cart = new ArrayList<Product>();
        // cartOrder = new ArrayList<Order>();
    }
    
}
