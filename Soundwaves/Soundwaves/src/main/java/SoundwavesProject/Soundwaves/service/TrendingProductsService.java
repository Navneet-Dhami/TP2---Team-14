package SoundwavesProject.Soundwaves.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SoundwavesProject.Soundwaves.model.Order;
import SoundwavesProject.Soundwaves.model.Product;
import SoundwavesProject.Soundwaves.model.Review;
import SoundwavesProject.Soundwaves.repository.OrderRepository;
import SoundwavesProject.Soundwaves.repository.ProductRepository;
import SoundwavesProject.Soundwaves.repository.ReviewRepository;

@Service
public class TrendingProductsService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<Product> getTrendingProducts() {
        LocalDate now = LocalDate.now();
        LocalDate oneMonthAgo = now.minus(Period.ofMonths(1));
        List<Product> products = productRepository.findAll();
        return products.stream()
                .sorted(Comparator.comparingDouble(p -> -1 * getAverageRatingForProduct(p, oneMonthAgo)))
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Product> getTrendingProductsByOrders() {
        LocalDate now = LocalDate.now();
        LocalDate oneMonthAgo = now.minus(Period.ofMonths(1));
        List<Order> orders = orderRepository.findByDateOrderedAfter(oneMonthAgo);
        Map<Product, Integer> productQuantities = orders.stream()
                .collect(Collectors.groupingBy(Order::getProduct,
                        Collectors.summingInt(Order::getQuantity)));
        return productQuantities.entrySet().stream()
                .sorted(Map.Entry.<Product, Integer>comparingByValue().reversed())
                .limit(10)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private double getAverageRatingForProduct(Product product, LocalDate startDate) {
        List<Review> reviews = reviewRepository.findByProductAndDateAfter(product, startDate);
        if (reviews.isEmpty()) {
            return 0.0;
        }
        
        double sum = 0.0;
        for (Review review : reviews) {
            sum += review.getRating();
        }

        return sum / reviews.size();
    }

}