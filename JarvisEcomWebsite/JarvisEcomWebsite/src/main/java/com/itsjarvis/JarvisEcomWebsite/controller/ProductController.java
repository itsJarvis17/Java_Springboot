package com.itsjarvis.JarvisEcomWebsite.controller;

import com.itsjarvis.JarvisEcomWebsite.model.Product;
import com.itsjarvis.JarvisEcomWebsite.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String home() {
        return "It works";
    }

    @GetMapping("/products")
    public ResponseEntity<?> findAllProducts() {
       return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> findProduct(@PathVariable("id") int id) {
        Product product = productService.findProduct(id);
        if(product.getId()>0)
            return new ResponseEntity<>(product, HttpStatus.OK);
        else
            return new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/product/{id}/image")
    public ResponseEntity<?> findProductImageById(@PathVariable("id") int id) {
        Product product = productService.findProduct(id);
        return new ResponseEntity<>(product.getImageData(), HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile image) {
        try {
            return new ResponseEntity<>(productService.addOrUpdateProduct(product, image), HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProductById(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile image) {
        try {
            productService.addOrUpdateProduct(product, image);
            return new ResponseEntity<>("Product Updated", HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") int id) {
        return new ResponseEntity<>(productService.deleteProduct(id).getStatusCode());
    }


    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam("keyword") String keyword) {
        System.out.println(keyword);
        return new ResponseEntity<>(productService.searchProducts(keyword), HttpStatus.OK);
    }


}
