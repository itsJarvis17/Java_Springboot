package com.itsjarvis.JarvisEcomWebsite.service;

import com.itsjarvis.JarvisEcomWebsite.model.Product;
import com.itsjarvis.JarvisEcomWebsite.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;


    public List<Product> findAllProducts() {
        return productRepo.findAll();
    }

    public Product addOrUpdateProduct(Product product, MultipartFile image) throws IOException {
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());
        if(product.getId()>0) {
            product.setName(product.getName());
            product.setDescription(product.getDescription());
            product.setBrand(product.getBrand());
            product.setPrice(product.getPrice());
            product.setProductAvailability(product.isProductAvailability());
            product.setCategory(product.getCategory());
            product.setReleaseDate(product.getReleaseDate());
            product.setStockQuantity(product.getStockQuantity());
            productRepo.save(product);
            return product;
        }
        return productRepo.save(product);
    }


    public ResponseEntity deleteProduct(int id) {
        if(findProduct(id).getId()>0) {
            productRepo.deleteById(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    public Product findProduct(int id) {
        return productRepo.findById(id).orElse(new Product(-1));
    }


    public List<Product> searchProducts(String keyword) {
        return productRepo.searchProducts(keyword);
    }
}
