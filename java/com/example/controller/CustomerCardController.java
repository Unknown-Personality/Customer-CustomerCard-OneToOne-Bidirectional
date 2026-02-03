package com.example.controller;

import com.example.exception.AlreadyAssignedException;
import com.example.model.Customer;
import com.example.model.CustomerCard;
import com.example.service.CustomerCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerCardController {

    @Autowired
    private CustomerCardService service;

    //Api- 2
    @PostMapping("/card/customer/{customerId}")
    public ResponseEntity assignCardToCustomer(@PathVariable long customerId, @RequestBody CustomerCard card){
        try{
            return new ResponseEntity(service.cardAssignToCustomer(customerId,card), HttpStatusCode.valueOf(201));
        }catch(AlreadyAssignedException e){
            return new ResponseEntity(e.getMessage(), HttpStatusCode.valueOf(500));
        }
    }

    //Api - 4
    @GetMapping("/card/status")
    public ResponseEntity getAllCards(){
        List<CustomerCard> list=service.getAllCards();
        if(!list.isEmpty()){
            return new ResponseEntity(list, HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity("No cards found!",HttpStatusCode.valueOf(500));
    }


    //Api - 6
    @PutMapping("/card/{cardId}")
    public ResponseEntity updateCard(@PathVariable long cardId,@RequestBody CustomerCard newCard){
        CustomerCard card=service.updateCard(cardId,newCard);
        if(card!=null){
            return new ResponseEntity(card, HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity("No cards found for updation!",HttpStatusCode.valueOf(404));
    }

    //Api - 7
    @DeleteMapping("/card/{cardId}")
    public ResponseEntity deleteCard(@PathVariable long cardId){
        CustomerCard card=service.deleteCard(cardId);
        if(card!=null){
            return new ResponseEntity("Card Deleted Successfully", HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity("Card Not found with ID:"+cardId,HttpStatusCode.valueOf(404));
    }
}
