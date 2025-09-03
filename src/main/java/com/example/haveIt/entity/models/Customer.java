package com.example.haveIt.entity.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(value = "customer")
public class Customer {

    @Id
    private String id;
    @Indexed(unique = true)
    private String customerId;
    private String name;
    private String email;
    private String address;

    public Customer() {
    }

    public Customer(String id, String customerId, String name, String email, String address) {
        this.id = id;
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(customerId, customer.customerId) && Objects.equals(name, customer.name) && Objects.equals(email, customer.email) && Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, name, email, address);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
