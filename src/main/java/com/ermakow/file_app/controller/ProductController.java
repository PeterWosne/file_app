package com.ermakow.file_app.controller;

import com.ermakow.file_app.converters.ProductConverter;
import com.ermakow.file_app.dtos.ProductDto;
import com.ermakow.file_app.entities.Product;
import com.ermakow.file_app.service.FileService;
import com.ermakow.file_app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final FileService fileService;
    private final ProductConverter converter;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts().stream()
                .map(converter::entityToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable("id") Long id) {
        return converter.entityToDto(productService.getProductById(id).get());
    }

    @GetMapping("/delete/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        Product product = productService.getProductById(id).get();

        product.getFileInfos().forEach(s -> {
            try {
                fileService.deleteFile(s.getId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        productService.deleteProductById(id);
    }

    @PostMapping
    public void changeProduct(@RequestBody ProductDto productDto) {
        productService.saveChanges(productDto);
    }
}
