package com.ermakow.file_app.service;


import com.ermakow.file_app.dtos.ProductDto;
import com.ermakow.file_app.entities.Product;
import com.ermakow.file_app.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    private final FileService fileService;

    public void saveNewProduct(String title, BigDecimal price, Integer year, Integer cc, Integer mileage, MultipartFile file) throws IOException {
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setYear(year);
        product.setCc(cc);
        product.setMileage(mileage);

        product.setFileInfos(new ArrayList<>());
        repository.save(product);
        fileService.upload(file, product);
    }

    public void saveChanges(ProductDto productDto) {
        Product product = getProductById(productDto.getId()).get();
        product.setTitle(productDto.getTitle());
        product.setYear(productDto.getYear());
        product.setPrice(productDto.getPrice());
        product.setMileage(productDto.getMileage());
        repository.save(product);
    }

    public void deleteProductById(Long id) {
        repository.deleteById(id);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return repository.findById(id);
    }
}
