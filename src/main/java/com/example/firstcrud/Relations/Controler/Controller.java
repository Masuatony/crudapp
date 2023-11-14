package com.example.firstcrud.Relations.Controler;

import com.example.firstcrud.Relations.DTO.OrderReqsts;
import com.example.firstcrud.Relations.Entity.Customer;
import com.example.firstcrud.Relations.Repo.CustomerRepo;
import com.example.firstcrud.Relations.Repo.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class Controller {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ItemRepo itemRepo;
    public Controller(CustomerRepo customerRepo, ItemRepo itemRepo){
        this.customerRepo = customerRepo;
        this.itemRepo = itemRepo;
    }
    @PostMapping("/add")
    public Customer placeOrder(@RequestBody OrderReqsts request) {
        return customerRepo.save(request.getCustomer());
    }
    @GetMapping("/findAll")
    public List<Customer> findAlls(){
        return customerRepo.findAll();
    }
}
