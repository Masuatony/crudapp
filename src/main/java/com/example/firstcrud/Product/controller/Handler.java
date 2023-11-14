package com.example.firstcrud.Product.controller;

import com.example.firstcrud.Product.Model.Product;
import com.example.firstcrud.Product.Repository.ProductsRepository;
import com.example.firstcrud.Product.Service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/product")
public class Handler {
    @Autowired
    private ProductService service;
    @Autowired
    private final ProductsRepository repo;

    public Handler(ProductsRepository repo){
        this.repo = repo;
    }


    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product){
        return service.saveProduct(product);
    }
    @PostMapping("/addMany")
    public List<Product> addManyProducts(@RequestBody List<Product> product){
        return service.saveManyProds(product);
    }
    @GetMapping("/getAll")
    public List<Product> getAll(){
        return service.getProducts();
    }
    @GetMapping("/getById/{id}")
    public Product findById(@PathVariable int id){
        return service.findById(id);
//        log.info("size" +id);
//        log.info("------------getting by Id");
    }
    @GetMapping("/getByName/{name}")
    public Product findByName(@PathVariable String name){
        return service.findByName(name);
    }
//    @PutMapping()
    @PutMapping("/update")
    public Product updateById(@RequestBody Product product){
        return service.updateProd(product);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable int id) {
       return service.deleteProduct(id);
    }
//    @PutMapping("/updated")
//    public updateNew(@RequestBody Product product){
//        return repo
//    }
    @PutMapping("/updateNew")
    private ResponseEntity<?> update(@RequestBody Product product){
        return new ResponseEntity<>(repo.save(product), HttpStatus.OK);
    }
    }
