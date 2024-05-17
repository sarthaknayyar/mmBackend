package com.rest.product.restproduct.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    private Long product_id;
    private int artist_id;
    private String artist_name;
    private String product_name;
    private float price;
    private String product_image;
    private String productDescription;
    private int quantity=1;

    // add default constructor
    public Product() {
    }


    public Product(int artist_id, String artist_name,String product_name, float price, long product_id, String product_image, String productDescription, int quantity) {
        this.artist_id = artist_id;
        this.artist_name = artist_name;
        this.product_name = product_name;
        this.price = price;
        this.product_id = product_id;
        this.product_image = product_image;
        this.productDescription = productDescription;
        this.quantity = quantity;
    }

    public int getArtistId() {
        return artist_id;
    }

    public void setArtistId(int artist_id) {
        this.artist_id = artist_id;
    }
    public long getProductId() {
        return product_id;
    }
    public void setProductId(long product_id){
        this.product_id = product_id;
    }
    public String getArtistName() {
        return artist_name;
    }

    public void setArtistName(String artist_name) {
        this.artist_name = artist_name;
    }
    public String getProductName() {
        return product_name;
    }

    public void setProductName(String product_name) {
        this.product_name = product_name;
    }
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    public String getProductImage() {
        return product_image;
    }
    public void setProductImage(String product_image) {
        this.product_image = product_image;
    }


    public String getProductDescription() {
        return productDescription;
    }


    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
    
}