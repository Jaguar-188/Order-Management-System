package com.example.haveIt.repository;

import com.example.haveIt.entity.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends MongoRepository<Order, String> {
    Order findByOrderId(String orderId);

    List<Order> findByCustomerId(String customerId);
}
