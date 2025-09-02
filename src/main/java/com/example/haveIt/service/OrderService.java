package com.example.haveIt.service;

import com.example.haveIt.constants.Constants;
import com.example.haveIt.constants.OrderStatus;
import com.example.haveIt.entity.exception.CustomerNotFoundException;
import com.example.haveIt.entity.exception.OrderNotFoundException;
import com.example.haveIt.entity.models.Customer;
import com.example.haveIt.entity.models.Items;
import com.example.haveIt.entity.models.Order;
import com.example.haveIt.repository.CustomerRepository;
import com.example.haveIt.repository.ItemsRepository;
import com.example.haveIt.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    private ItemsRepository itemsRepository;
    private OrdersRepository ordersRepository;
    private CustomerRepository customerRepository;

    public OrderService(ItemsRepository itemsRepository, OrdersRepository ordersRepository, CustomerRepository customerRepository) {
        this.itemsRepository = itemsRepository;
        this.ordersRepository = ordersRepository;
        this.customerRepository = customerRepository;
    }

    public Order createOrder(Order order) {

        double finalPrice = 0.0;
        int finalQuantity = 0;
        List<Items> itemsList = new ArrayList<>();
        for(Items items: order.getItems())
        {
            Items itemToBeReturned = new Items();
            Items item = itemsRepository.findByItemId(items.getItemId());
            finalPrice = finalPrice + item.getPrice() * items.getQuantity();
            finalQuantity = finalQuantity + items.getQuantity();
            item.setQuantity(item.getQuantity() - items.getQuantity());
            itemToBeReturned.setId(items.getId());
            itemToBeReturned.setItemId(items.getItemId());
            itemToBeReturned.setQuantity(items.getQuantity());
            itemToBeReturned.setPrice(item.getPrice() * items.getQuantity());
            itemToBeReturned.setName(items.getName());
            itemsRepository.save(item);
            itemsList.add(itemToBeReturned);
        }
        order.setItems(itemsList);
        order.setId(UUID.randomUUID().toString());
        order.setOrderId(Constants.Order + "-"+ UUID.randomUUID());
        order.setTime(LocalDate.now().toString());
        order.setQuantity(finalQuantity);
        order.setPrice(finalPrice);
        order.setStatus(String.valueOf(OrderStatus.CONFIRMED));
        return ordersRepository.save(order);
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
