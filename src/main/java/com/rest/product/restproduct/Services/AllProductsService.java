package com.rest.product.restproduct.Services;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rest.product.restproduct.dao.AllProductsRepository;
import com.rest.product.restproduct.entities.AllProducts;
import com.rest.product.restproduct.util.ImageUtils;

@Service
public class AllProductsService {
    
    
    @Autowired
    private AllProductsRepository allProductsRepository;

    public List<AllProducts> getAllProducts() {
        List<AllProducts> list =(List<AllProducts>)this.allProductsRepository.findAll();
        return list;
    }
    public AllProducts getProductById(int id){
        AllProducts product=null;
        // product = list.stream().filter(e->e.getArtistId()==id).findFirst().get();
        product = this.allProductsRepository.findById(id).orElse(null);
        return product;

    }

    public AllProducts addProduct(AllProducts b, MultipartFile file) throws Exception{
        
        AllProducts  savedProduct = new AllProducts();
        savedProduct.setProductId(b.getProductId());
        savedProduct.setProductName(b.getProductName());
        savedProduct.setPrice(b.getPrice());
        // savedProduct.setQuantity(b.getQuantity());
        savedProduct.setArtistId(b.getArtistId());
        savedProduct.setArtistName(b.getArtistName());
        savedProduct.setData(ImageUtils.compressImage(file.getBytes()));
        savedProduct.setProductDescription(b.getProductDescription());
        savedProduct.setImageName(b.getProductName());
        savedProduct.setImageType(file.getContentType());
        AllProducts  product = this.allProductsRepository.save(savedProduct);

        
        // AllProducts  product = this.allProductsRepository.save(b);
        return product;
        
    }

    public byte[] getImageByName(String imageName){
        Optional<AllProducts> dbImageData = allProductsRepository.findByImageName(imageName);
        byte[] images= ImageUtils.decompressImage(dbImageData.get().getData());
        return images;


    }


}
