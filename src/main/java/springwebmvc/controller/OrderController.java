package springwebmvc.controller;

import org.springframework.web.bind.annotation.*;
import springwebmvc.dto.OrderRequestDto;
import springwebmvc.dto.OrderResponseDto;
import springwebmvc.model.Order;
import springwebmvc.service.OrderService;

import java.util.Collection;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto requestDto) {
        return orderService.create(requestDto);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public OrderResponseDto getOrder(@PathVariable("id") Long id) {
        return orderService.findById(id);
    }

    @PutMapping("/{id}")
    public OrderResponseDto updateOrder(@PathVariable("id") Long id, @RequestBody OrderRequestDto requestDto) {
        return orderService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") Long id) {
        orderService.delete(id);
    }

    @GetMapping
    public Collection<Order> getAllOrders() {
        return orderService.getAll();
    }
}
