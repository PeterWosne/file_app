package com.ermakow.file_app.converters;

import com.ermakow.file_app.dtos.ProductDto;
import com.ermakow.file_app.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice(), product.getYear(), product.getCc(), product.getMileage(), product.getFileInfos());
    }
}
