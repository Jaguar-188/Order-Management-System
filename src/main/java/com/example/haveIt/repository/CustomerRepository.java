package com.example.haveIt.repository;

import com.example.haveIt.entity.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findByCustomerId(String customerId);

    void deleteByCustomerId(String customerId);

    boolean existsByEmail(String email);
}
