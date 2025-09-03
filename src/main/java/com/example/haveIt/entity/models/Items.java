package com.example.haveIt.entity.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

@Document(value = "items")
public class Items {

    @Id
    private String id;
    @Indexed(unique = true)
    private String itemId;
    private String name;
    private double price;
    @Field(value = "availableStock")
    private int quantity;

    public Items() {
    }

    public Items(String id, String itemId, String name, double price, int quantity) {
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

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Items items = (Items) o;
        return Double.compare(items.price, price) == 0 && quantity == items.quantity && Objects.equals(id, items.id) && Objects.equals(itemId, items.itemId) && Objects.equals(name, items.name);
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
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
