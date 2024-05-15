package com.rest.product.restproduct.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rest.product.restproduct.Services.AllProductsService;
import com.rest.product.restproduct.entities.AllProducts;
import com.rest.product.restproduct.entities.Product;


@RestController
@RequestMapping("/api/allproducts")
public class AllProductsController {
    @Autowired
    private AllProductsService allProductsService;

    @GetMapping("/image/{imageName}")
    @CrossOrigin("**")
    public ResponseEntity<?> download(@PathVariable String imageName) {
        byte[] image = allProductsService.getImageByName(imageName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/jpeg"))
                .body(image);
    }
    @GetMapping  
    @CrossOrigin("**")
    public ResponseEntity<List<AllProducts>> getProducts() {
        List<AllProducts> list = allProductsService.getAllProducts();
        if(list.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(list));
    }
    // get single book
     @GetMapping("/{id}")
    public ResponseEntity<AllProducts> getProduct(@PathVariable("id") int id){
        AllProducts product = allProductsService.getProductById(id);
        if(product==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(product));

    }
    
    
    // @PostMapping
    // public ResponseEntity<AllProducts> addProduct(@RequestBody AllProducts product){
    //     AllProducts b = null;   
    //     try {
    //         b = this.allProductsService.addProduct(product);
    //         System.out.println(product);
    //         return ResponseEntity.status(HttpStatus.CREATED).build();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    //     }
    // }

    @PostMapping
    public ResponseEntity<String> uploadProductWithImage(
            @ModelAttribute AllProducts product,
            @RequestParam("file") MultipartFile file) {
        try {
           allProductsService.addProduct(product, file);
            
            return new ResponseEntity<>("Product and image uploaded successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to upload product and image"+ e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}



