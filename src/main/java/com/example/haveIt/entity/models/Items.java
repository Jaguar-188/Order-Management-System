package com.example.haveIt.entity.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

@Document(value = "items")
public class Items {

    @Id
    private String id;

    private String itemId;
    private String name;
    private double price;
    @Field(value = "availableStock")
    private int quantity;

    public Items() {
    }

    public Items(String id, String itemId,String name, double price, int quantity) {
        this.id = id;
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Items items = (Items) o;
        return Objects.equals(id, items.id) && Objects.equals(itemId, items.itemId) && Objects.equals(name, items.name) && Objects.equals(price, items.price) && Objects.equals(quantity, items.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemId, name, price, quantity);
    }

    @Override
    public String toString() {
        return "Items{" +
                "id='" + id + '\'' +
                ", itemId='" + itemId + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
