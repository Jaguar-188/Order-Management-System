package com.example.haveIt.entity.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document(value = "order")
public class Order {

    @Id
    private String id;
    private String orderId;
    private String customerId;
    private String time;
    private List<Items> items;
    private double price;
    private int quantity;
    private String status;
    private String deletedAt;

    public Order() {
    }

    public Order(String id, String orderId, String customerId, String time, List<Items> items, double price, int quantity, String status, String deletedAt) {
        this.id = id;
        this.orderId = orderId;
        this.customerId = customerId;
        this.time = time;
        this.items = items;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.deletedAt = deletedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(order.price, price) == 0 && quantity == order.quantity && Objects.equals(id, order.id) && Objects.equals(orderId, order.orderId) && Objects.equals(customerId, order.customerId) && Objects.equals(time, order.time) && Objects.equals(items, order.items) && Objects.equals(status, order.status) && Objects.equals(deletedAt, order.deletedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, customerId, time, items, price, quantity, status, deletedAt);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", time='" + time + '\'' +
                ", items=" + items +
                ", price=" + price +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                ", deletedAt='" + deletedAt + '\'' +
                '}';
    }
}
