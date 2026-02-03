package com.example.service;

import com.example.model.CustomerCard;

import java.util.List;

public interface CustomerCardService {
    public CustomerCard cardAssignToCustomer(long custId, CustomerCard card);
    public List<CustomerCard> getAllCards();
    public CustomerCard updateCard(long cardId,CustomerCard newCard);
    public CustomerCard deleteCard(long cardId);
}
