package com.example.haveIt.service;

import com.example.haveIt.constants.Constants;
import com.example.haveIt.constants.OrderStatus;
import com.example.haveIt.entity.exception.CustomerNotFoundException;
import com.example.haveIt.entity.exception.OrderNotFoundException;
import com.example.haveIt.entity.models.Customer;
import com.example.haveIt.entity.models.Order;
import com.example.haveIt.repository.CustomerRepository;
import com.example.haveIt.repository.ItemsRepository;
import com.example.haveIt.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderService {

    private ItemsRepository itemsRepository;
    private OrdersRepository ordersRepository;
    private CustomerRepository customerRepository;
    private OrderProcessingService orderProcessingService;

    private final Map<String, Object> customerLocks = new ConcurrentHashMap<>();

    public OrderService(ItemsRepository itemsRepository,
                        OrdersRepository ordersRepository,
                        CustomerRepository customerRepository,
                        OrderProcessingService orderProcessingService) {
        this.itemsRepository = itemsRepository;
        this.ordersRepository = ordersRepository;
        this.customerRepository = customerRepository;
        this.orderProcessingService = orderProcessingService;
    }

    public Order createOrder(Order order) throws InterruptedException {

        //Provided double lock on customer as well as on item
        Object lock = customerLocks.computeIfAbsent(order.getCustomerId(),k -> new Object());
        synchronized (lock){

            order.setId(UUID.randomUUID().toString());
            order.setOrderId(Constants.Order + "-"+ UUID.randomUUID());
            order.setTime(LocalDate.now().toString());
            order.setStatus(String.valueOf(OrderStatus.PENDING));
            Order pendingOrderResponse = ordersRepository.save(order);

            orderProcessingService.processOrder(pendingOrderResponse.getOrderId(),order);

            return ordersRepository.save(order);
        }
    }

    public Order removeOrder(String orderId) {

        Order order = ordersRepository.findByOrderId(orderId);
        order.setDeletedAt(LocalDate.now().toString());
        return ordersRepository.save(order);
    }

    public List<Order> fetchOrderInformation(String customerId) {

        Customer customer = findCustomerByCustomerId(customerId);
        return ordersRepository.findByCustomerId(customer.getCustomerId());
    }

    public Order updateOrderStatus(String orderId, String status) {

        Order order = findOrderByOrderId(orderId);
        switch (status) {
            case "pending" -> order.setStatus(String.valueOf(OrderStatus.PENDING));
            case "preparing" -> order.setStatus(String.valueOf(OrderStatus.PREPARING));
            case "shipped" -> order.setStatus(String.valueOf(OrderStatus.SHIPPED));
            case "cancelled" -> order.setStatus(String.valueOf(OrderStatus.CANCELLED));
        }
        return ordersRepository.save(order);
    }

    public Order findOrderByOrderId(String orderId) {

        return Optional.ofNullable(ordersRepository.findByOrderId(orderId))
                .orElseThrow(() -> new OrderNotFoundException("Order not present in application"));
    }

    public Customer findCustomerByCustomerId(String customerId){

        return Optional.ofNullable(customerRepository.findByCustomerId(customerId))
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Present in application"));
    }
}
