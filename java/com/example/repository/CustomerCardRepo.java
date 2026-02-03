package com.example.repository;

import com.example.model.CustomerCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCardRepo extends JpaRepository<CustomerCard,Long> {

}
