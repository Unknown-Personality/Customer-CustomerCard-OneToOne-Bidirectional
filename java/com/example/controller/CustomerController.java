package com.example.controller;

import com.example.exception.InvalidDataException;
import com.example.model.Customer;
import com.example.model.CustomerCard;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService service;

    //Api - 1
    @PostMapping("/customer")
    public ResponseEntity addCustomer(@RequestBody Customer cust){
        try{
            return new ResponseEntity(service.createCustomer(cust), HttpStatusCode.valueOf(201));
        }catch(InvalidDataException e){
            return new ResponseEntity(e.getMessage(), HttpStatusCode.valueOf(400));
        }catch(Exception e){
            return new ResponseEntity(HttpStatusCode.valueOf(500));
        }
    }

    //Api - 3
    @GetMapping("/customer")
    public ResponseEntity retrieveCustomer(){
        List<Customer> list=service.getCustomers();
        if(!list.isEmpty()){
            return new ResponseEntity(list,HttpStatusCode.valueOf(200));
        }

        return new ResponseEntity("No customers found.",HttpStatusCode.valueOf(404));
    }

    //Api - 5
    @GetMapping("/customer/{customerId}")
    public ResponseEntity getCustomerCardById(@PathVariable long customerId){
        CustomerCard card =service.getCustomerCardById(customerId);
        if(card!=null){
            return new ResponseEntity(card,HttpStatusCode.valueOf(200));
        }

        return new ResponseEntity("Not Found",HttpStatusCode.valueOf(404));
    }

    //Api - 8
    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity deleteCustomer(@PathVariable long customerId){
        boolean isDelete=service.deleteCustomerById(customerId);
        if(isDelete){
            return new ResponseEntity(HttpStatusCode.valueOf(204));
        }

        return new ResponseEntity("Customer not found for deletion.",HttpStatusCode.valueOf(404));
    }

}
