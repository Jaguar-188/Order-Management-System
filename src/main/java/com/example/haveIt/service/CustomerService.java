package com.example.haveIt.service;

import com.example.haveIt.entity.exception.CustomerNotFoundException;
import com.example.haveIt.entity.exception.EmailAlreadyExistsException;
import com.example.haveIt.entity.models.Customer;
import com.example.haveIt.repository.CustomerRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {

        boolean isExist = customerRepository.existsByEmail(customer.getEmail());
        if(isExist){
            throw new EmailAlreadyExistsException("Provided email is already linked with another customer.");
        }
        customer.setId(UUID.randomUUID().toString());
        customer.setCustomerId(customer.getName() + "--" + UUID.randomUUID());
        return customerRepository.save(customer);
    }


    public void removeCustomer(String customerId) {

        Customer customer1 = findCustomerByCustomerId(customerId);
        customerRepository.deleteByCustomerId(customer1.getCustomerId());
    }

    public Customer getCustomerDetails(String customerId) {

        return findCustomerByCustomerId(customerId);
    }

    public Customer updateCustomer(Customer customer) {

        Customer customer1 = customerRepository.findByCustomerId(customer.getCustomerId());
        customer1.setId(StringUtils.isEmpty(customer.getId()) ? customer1.getId() : customer.getId());
        customer1.setName(StringUtils.isEmpty(customer.getName()) ? customer1.getName() : customer.getName());
        customer1.setEmail(StringUtils.isEmpty(customer.getEmail()) ? customer1.getEmail() : customer.getEmail());
        customer1.setAddress(StringUtils.isEmpty(customer.getAddress()) ? customer1.getAddress() : customer.getAddress());
        return customerRepository.save(customer1);
    }

    public Customer findCustomerByCustomerId(String customerId){

        return Optional.ofNullable(customerRepository.findByCustomerId(customerId))
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Present in application"));
    }
}
