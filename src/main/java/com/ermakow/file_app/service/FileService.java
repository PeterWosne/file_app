package com.ermakow.file_app.service;

import com.ermakow.file_app.entities.FileInfo;
import com.ermakow.file_app.entities.Product;
import com.ermakow.file_app.repositories.FileInfoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileInfoRepository repository;


    @Transactional
    public void upload(MultipartFile resource, Product product) throws IOException {
        String key = UUID.randomUUID().toString();

        Path path = Paths.get("src/main/resources/storage", key);
        Path file = Files.createFile(path);
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file.toString());
            stream.write(resource.getBytes());
        } finally {
            stream.close();
        }

        FileInfo fileInfo = new FileInfo();
        fileInfo.setName(resource.getOriginalFilename());
        fileInfo.setProduct(product);
        fileInfo.setSize(resource.getSize());
        fileInfo.setFileKey(key);
        repository.save(fileInfo);
    }

    public void deleteFile(Long id) throws IOException {
        FileInfo fileInfo = repository.findById(id).get();
        String key = fileInfo.getFileKey();

        Path path = Paths.get("src/main/resources/storage", key);
        Files.delete(path);

        repository.deleteById(id);
    }

    public Optional<FileInfo> getFileInfoById(Long id) {
        return repository.findById(id);
    }
}