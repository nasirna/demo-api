package com.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.models.entities.Product;
import com.domain.models.entities.Supplier;
import com.domain.models.repos.ProductRepo;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProductService {
 @Autowired
    private ProductRepo productRepo;

    public Product create(Product product){
        return productRepo.save(product);
    }

    public Product findOne(Long id ){
        //return productRepo.findById(id).get();

        Optional<Product> product = productRepo.findById(id);
        if(!product.isPresent()){
            return null;
        }
        
        return product.get();
    }

    public Iterable<Product> findAll(){
        return productRepo.findAll();
    }

    public void removeOne(Long id){
        //productRepo.deleteById(id);
        
        Optional<Product> product = productRepo.findById(id);
        if(product.isPresent()){
            productRepo.deleteById(id);
        }
        
    }

    public List<Product> findByName(String name){
        return productRepo.findByNameContains(name);
    }

    public void addSupplier(Supplier supplier, Long productId){
        Product product = findOne(productId);
        if(product == null){
            throw new RuntimeException("Product with id: "+ productId +" not found");
        }
        product.getSuppliers().add(supplier);
        create(product);
    }

    public Product findByProductName(String name){
        return productRepo.findProductByName(name);
    }

    public List<Product> findByProductNameLike(String name){
        return productRepo.findProductByNameLike("%"+name+"%");
    }
    
}
