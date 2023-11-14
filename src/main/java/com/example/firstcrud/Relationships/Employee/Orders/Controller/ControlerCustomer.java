package com.example.firstcrud.Relationships.Employee.Orders.Controller;

import com.example.firstcrud.Product.Repository.ProductsRepository;
import com.example.firstcrud.Relationships.Employee.Orders.DTOS.OrderRequest;
import com.example.firstcrud.Relationships.Employee.Orders.Models.Customers;
import com.example.firstcrud.Relationships.Employee.Orders.Repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControlerCustomer {
    @Autowired
    private  CustomerRepository customerRepository;
    @Autowired
    private ProductsRepository productRepository;

//    public ControlerCustomer(CustomerRepository customerRepository, ProductRepository productRepository){
//        this.customerRepository = customerRepository;
//        this.productRepository = productRepository;
//    }
    @PostMapping("/placeOrder")
public Customers placeOrder(@RequestBody OrderRequest request){
        return customerRepository.save(request.getCustomers());
}
@GetMapping("/getAll")
public List<Customers> findAllOrders(){
        return customerRepository.findAll();
}

}
