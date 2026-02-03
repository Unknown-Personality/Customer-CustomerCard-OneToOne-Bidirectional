package com.example.service;

import com.example.exception.AlreadyAssignedException;
import com.example.model.Customer;
import com.example.model.CustomerCard;
import com.example.repository.CustomerCardRepo;
import com.example.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerCardServiceImpl implements CustomerCardService{

    @Autowired
    private CustomerRepo cRepo;

    @Autowired
    private CustomerCardRepo ccRepo;

    //Service Method for Api- 2
    @Override
    public CustomerCard cardAssignToCustomer(long custId,CustomerCard card){
        Customer c=cRepo.findById(custId).orElse(null);

        if(c.getCard()!=null){
            throw new AlreadyAssignedException("Card is already assigned to Customer with ID:"+custId);
        }
        card.setCustomer(c);
        return ccRepo.save(card);
    }

    //Service Method for Api - 4
    @Override
    public List<CustomerCard> getAllCards(){
        return ccRepo.findAll();
    }

    //Service Method for Api - 6
    @Override
    public CustomerCard updateCard(long cardId, CustomerCard newCard) {
        CustomerCard c=ccRepo.findById(cardId).orElse(null);

        if(c!=null){
            c.setCardNumber(newCard.getCardNumber());
            c.setExpirationDate(newCard.getExpirationDate());
            //c.setCustomer(newCard.getCustomer());
            return ccRepo.save(c);
        }

        return null;
    }

    //Service Method for Api - 7
    @Override
    public CustomerCard deleteCard(long cardId) {
        CustomerCard c=ccRepo.findById(cardId).orElse(null);

        if(c!=null){
            ccRepo.deleteById(cardId);
            return c;
        }

        return null;
    }

}
