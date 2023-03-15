package com.ermakow.file_app.controller;

import com.ermakow.file_app.entities.FileInfo;
import com.ermakow.file_app.entities.Product;
import com.ermakow.file_app.service.FileService;
import com.ermakow.file_app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Base64;


@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin("*")
public class FileController {

    private final ProductService productService;

    private final FileService fileService;


    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestParam MultipartFile file, @RequestParam String data) {
        try {
            String productInfo = data.replaceAll("\"", "");
            String title = productInfo.split(",")[0];
            BigDecimal price = new BigDecimal(productInfo.split(",")[1]);
            Integer year = Integer.parseInt(productInfo.split(",")[2]);
            Integer cc = Integer.parseInt(productInfo.split(",")[3]);
            Integer mileage = Integer.parseInt(productInfo.split(",")[4]);

            productService.saveNewProduct(title, price, year, cc, mileage, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //метод для добавления фото к уже имеющемуся продукту
    @PostMapping("/uploadf")
    public void uploadImageForProduct(@RequestParam MultipartFile file, @RequestParam String data) {
        Long id = Long.valueOf(data);
        Product product = productService.getProductById(id).get();
        try {
            fileService.upload(file, product);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //удаление файла по id
    @GetMapping("/delete/{id}")
    public void deleteFile(@PathVariable("id") Long id) {
        try {
            fileService.deleteFile(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable("id") Long id) throws IOException {
        FileInfo fileInfo = fileService.getFileInfoById(id).get();
        String fileKey = fileInfo.getFileKey();

        byte[] fileContent = FileUtils.readFileToByteArray(new File("src/main/resources/storage/" + fileKey));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        return Base64.getDecoder().decode(encodedString);
    }
}

// контроллер для angularjs https://www.tutorialspoint.com/angularjs/angularjs_upload_file.htm
