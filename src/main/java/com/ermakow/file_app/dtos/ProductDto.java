package com.ermakow.file_app.dtos;

import com.ermakow.file_app.entities.FileInfo;

import java.math.BigDecimal;
import java.util.List;

public class ProductDto {

    private Long id;

    private String title;

    private BigDecimal price;

    private Integer year;

    private Integer cc;

    private Integer mileage;

    private List<FileInfo> fileInfos;

    public ProductDto() {}

    public ProductDto(Long id, String title, BigDecimal price, Integer year, Integer cc, Integer mileage, List<FileInfo> fileInfos) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.year = year;
        this.cc = cc;
        this.mileage = mileage;
        this.fileInfos = fileInfos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getCc() {
        return cc;
    }

    public void setCc(Integer cc) {
        this.cc = cc;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public List<FileInfo> getFileInfos() {
        return fileInfos;
    }

    public void setFileInfos(List<FileInfo> fileInfos) {
        this.fileInfos = fileInfos;
    }
}
