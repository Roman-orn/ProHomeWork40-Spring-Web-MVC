package springwebmvc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import springwebmvc.model.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public record OrderResponseDto(
        Long id,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime creationDateTime,
        ArrayList<Product> products,
        BigDecimal totalCost) {
}
