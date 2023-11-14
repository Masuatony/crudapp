package com.example.firstcrud.Product.Service;

import com.example.firstcrud.Product.Model.Product;
import com.example.firstcrud.Product.Repository.ProductsRepository;
import com.example.firstcrud.Relationships.Employee.Orders.Repos.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductsRepository repo;
    public ProductService(ProductsRepository productRepository){
        this.repo = productRepository;
    }

    public Product saveProduct(Product product){
        try{
            return repo.save(product);
//            System.out.println("");
        } catch (Exception e) {
            return null;
        }

//        return product;
    }
    public List<Product> saveManyProds(List<Product> product){
        return repo.saveAll(product);
    }

    public List<Product> getProducts(){
        return repo.findAll();
    }
//    public List<Product> getById(int id){
//        return repo.findById(id);
//    }

    public Product findById(int id){
        return repo.findById(id).orElse(null);
    }
    public Product findByName(String name){
        return repo.findByName(name);
    }

    public String deleteProduct(int id){
        repo.deleteById(id);
        return "Product removed!! " +id;
    }
    public Product updateProd(Product product){
        Product exists = repo.findById(product.getId()).orElse(null);
        exists.setName(product.getName());
        exists.setAmount(product.getAmount());
        exists.setPrice(product.getPrice());
        return repo.save(exists);
    }
    private ResponseEntity<?> update(@RequestBody Product product) {
        return new ResponseEntity<>(repo.save(product), HttpStatus.OK);
    }

}
