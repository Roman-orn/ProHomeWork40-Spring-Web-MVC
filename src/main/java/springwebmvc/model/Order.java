package springwebmvc.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {

    public Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime creationDateTime;
    public ArrayList<Product> products;
    public BigDecimal totalCost;

    public Order(ArrayList<Product> products) {
        this.creationDateTime = LocalDateTime.now();
        this.products = products;
        this.totalCost = products.stream()
                .map(product -> product.price.multiply(BigDecimal.valueOf(product.quantity)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
