package springwebmvc.dto;

import springwebmvc.model.Product;

import java.util.ArrayList;

public record OrderRequestDto(
        ArrayList<Product> products) {
}
