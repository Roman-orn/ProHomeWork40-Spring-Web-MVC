package springwebmvc.repository;

import org.springframework.stereotype.Repository;
import springwebmvc.model.Order;
import springwebmvc.model.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class OrderRepository {

    private final ConcurrentHashMap<Long, Order> orders = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong(0);

    public Order create(ArrayList<Product> products) {
        Order order = new Order(products);
        order.id = counter.incrementAndGet();
        orders.put(order.id, order);
        return order;
    }

    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(orders.get(id));
    }

    public void update(Order order) {
        orders.replace(order.id, order);
    }

    public void delete(Long id) {
        orders.remove(id);
    }

    public Collection<Order> getAll() {
        return orders.values();
    }
}
