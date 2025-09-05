package com.example.haveIt.service;

import com.example.haveIt.constants.OrderStatus;
import com.example.haveIt.entity.exception.NotEnoughStockForItemException;
import com.example.haveIt.entity.models.Order;
import com.example.haveIt.repository.CustomerRepository;
import com.example.haveIt.repository.ItemsRepository;
import com.example.haveIt.repository.OrdersRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.example.haveIt.entity.models.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderProcessingService {

    private ItemsRepository itemsRepository;
    private OrdersRepository ordersRepository;
    private CustomerRepository customerRepository;

    private final Map<String, Object> itemsLocks = new ConcurrentHashMap<>();

    public OrderProcessingService(ItemsRepository itemsRepository, OrdersRepository ordersRepository, CustomerRepository customerRepository) {
        this.itemsRepository = itemsRepository;
        this.ordersRepository = ordersRepository;
        this.customerRepository = customerRepository;
    }

    @Async
    public void processOrder(String orderId, Order order) throws InterruptedException {

        Order pendingOrder = ordersRepository.findByOrderId(orderId);
        double finalPrice = 0.0;
        int finalQuantity = 0;
        List<Items> itemsList = new ArrayList<>();
        for(Items items: order.getItems())
        {
            Object lock = itemsLocks.computeIfAbsent(items.getItemId(),k -> new Object());
            synchronized (lock){
                Items itemToBeReturned = new Items();
                Items item = itemsRepository.findByItemId(items.getItemId());
                if(item.getQuantity() < items.getQuantity()){
                    throw new NotEnoughStockForItemException("Not Enough stock For Item is Present");
                }
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
        }
        pendingOrder.setItems(itemsList);
        pendingOrder.setStatus(OrderStatus.CONFIRMED.toString());
        pendingOrder.setQuantity(finalQuantity);
        pendingOrder.setPrice(finalPrice);

        Thread.sleep(5000);

        ordersRepository.save(pendingOrder);
    }
}
