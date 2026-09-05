package springwebmvc.model;

import java.math.BigDecimal;

public class Product {

    public String name;
    public int quantity;
    public BigDecimal price;

    public Product() {

    }

    public Product(String name, int quantity, BigDecimal price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}
