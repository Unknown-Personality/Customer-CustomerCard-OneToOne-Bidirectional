package com.example.service;

import com.example.exception.InvalidDataException;
import com.example.model.Customer;
import com.example.model.CustomerCard;
import com.example.repository.CustomerCardRepo;
import com.example.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepo cRepo;

    @Autowired
    private CustomerCardRepo ccRepo;

    //Service Method for Api - 1
    @Override
    public Customer createCustomer(Customer customer) {
        String name=customer.getCustomerName();
        String email= customer.getEmail();
        String phone=customer.getPhone();
        if(name.isEmpty()||email.isEmpty()||phone.isEmpty()){
            throw new InvalidDataException("Customer Data is Not Valid");
        }

        return cRepo.save(customer);
    }

    //Service Method for Api - 3
    @Override
    public List<Customer> getCustomers() {
        return cRepo.getCustomerNameSorting();
    }


    //Service Method for Api - 5
    @Override
    public CustomerCard getCustomerCardById(long customerId) {
        Customer c=cRepo.findById(customerId).orElse(null);

        if(c!=null){
            if(c.getCard()!=null){
                return c.getCard();
            }
        }

        return null;
    }

    //Service Method for Api - 8
    @Override
    public boolean deleteCustomerById(long custId) {
        if(cRepo.existsById(custId)){
            Customer c=cRepo.findById(custId).orElse(null);
            long cardId=c.getCard().getCardId();
            ccRepo.deleteById(cardId);
            cRepo.deleteById(custId);
            return true;
        }
        return false;
    }

}
