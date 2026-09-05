package springwebmvc.service;

import org.springframework.stereotype.Service;
import springwebmvc.dto.OrderRequestDto;
import springwebmvc.dto.OrderResponseDto;
import springwebmvc.exception.BadRequestException;
import springwebmvc.exception.DataNotFoundException;
import springwebmvc.exception.ErrorCode;
import springwebmvc.model.Order;
import springwebmvc.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.Collection;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderResponseDto create(OrderRequestDto requestDto) {
        if (requestDto.products() == null) {
            throw new BadRequestException(ErrorCode.INPUT_PARAMETERS_VALIDATION_FAILED, "Parameter [products] must not be null!");
        }

        if (requestDto.products().isEmpty()) {
            throw new BadRequestException(ErrorCode.INPUT_PARAMETERS_VALIDATION_FAILED, "Parameter [products] must not be empty!");
        }

        Order order = orderRepository.create(requestDto.products());
        return new OrderResponseDto(
                order.id,
                order.creationDateTime,
                order.products,
                order.totalCost
        );
    }

    public OrderResponseDto findById(Long id){
        Order order = orderRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException(ErrorCode.ORDER_NOT_FOUND, "Order with id %d not found.".formatted(id)));
        return new OrderResponseDto(
                order.id,
                order.creationDateTime,
                order.products,
                order.totalCost
        );
    }

    public OrderResponseDto update(Long id, OrderRequestDto requestDto){
        if (requestDto.products() == null) {
            throw new BadRequestException(ErrorCode.INPUT_PARAMETERS_VALIDATION_FAILED, "Parameter [products] must not be null!");
        }

        if (requestDto.products().isEmpty()) {
            throw new BadRequestException(ErrorCode.INPUT_PARAMETERS_VALIDATION_FAILED, "Parameter [products] must not be empty!");
        }

        Order order = orderRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException(ErrorCode.ORDER_NOT_FOUND, "Order with id %d not found.".formatted(id)));

        order.products = requestDto.products();
        order.totalCost = order.products.stream()
                .map(product -> product.price.multiply(BigDecimal.valueOf(product.quantity)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        orderRepository.update(order);

        return new OrderResponseDto(
                order.id,
                order.creationDateTime,
                order.products,
                order.totalCost
        );
    }

    public void delete(Long id){
        orderRepository.delete(id);
    }

    public Collection<Order> getAll() {
        return orderRepository.getAll();
    }
}
