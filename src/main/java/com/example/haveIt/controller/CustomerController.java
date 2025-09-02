package com.example.haveIt.controller;

import com.example.haveIt.entity.models.Customer;
import com.example.haveIt.service.CustomerService;
import com.example.haveIt.utils.Logging;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.boot.web.error.Error;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/rest/v1/customer-controller/")
public class CustomerController {


    private CustomerService customerService;

    private static final Logging log = Logging.getLogger();

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/createCustomer")
    @Operation(summary = "The endpoint creates the customer.",
            description = "The endpoint creates the customer with provided data in application.",
            method = "POST",
            responses = {@ApiResponse(responseCode = "201",
                    description = "CREATED",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class))),
                    @ApiResponse(responseCode = "400",
                            description = "Bad Request",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    public Customer createCustomer(@RequestBody Customer customer){

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("Creating customer with customer name : " + customer.getName());
        Customer customerResponse = customerService.createCustomer(customer);
        stopWatch.stop();
        log.info("Total time taken to create customer with customer id : "
                + customerResponse.getCustomerId() + " is : " + stopWatch.getTotalTimeSeconds());
        return customerResponse;
    }

    @DeleteMapping("/removeCustomer/{customerId}")
    @Operation(summary = "The endpoint removes the customer.",
            description = "The endpoint removes the customer with provided data from application.",
            method = "DELETE",
            responses = {@ApiResponse(responseCode = "204",
                    description = "No Content",
                    content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400",
                            description = "Bad Request",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    public String removeCustomer(@PathVariable String customerId){

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("Removing customer with customer id : " + customerId);
        customerService.removeCustomer(customerId);
        stopWatch.stop();
        log.info("Total time taken to remove customer with customer id : "
                + customerId + " is : " + stopWatch.getTotalTimeSeconds());

        return "Successfully deleted the customer with customer id : " + customerId;
    }

    @GetMapping("/getCustomerDetails")
    @Operation(summary = "The endpoint fetches the customer information.",
            description = "The endpoint fetches the customer with provided data from application.",
            method = "GET",
            responses = {@ApiResponse(responseCode = "200",
                    description = "OK",
                    content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400",
                            description = "Bad Request",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    public Customer getCustomerDetails(@RequestParam String customerId){

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("Retrieving customer with customer id : " + customerId);
        Customer customerResponse = customerService.getCustomerDetails(customerId);
        stopWatch.stop();
        log.info("Total time taken to fetch customer with customer id : "
                + customerId + " is : " + stopWatch.getTotalTimeSeconds());

        return customerResponse;
    }

    @PutMapping("/updateCustomer")
    @Operation(summary = "The endpoint updates the customer information.",
            description = "The endpoint updates the customer with provided data from application.",
            method = "PUT",
            responses = {@ApiResponse(responseCode = "200",
                    description = "OK",
                    content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400",
                            description = "Bad Request",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    public Customer updateCustomer(@RequestBody Customer customer){

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("Updating customer with customer id : " + customer.getCustomerId());
        Customer customerResponse = customerService.updateCustomer(customer);
        stopWatch.stop();
        log.info("Total time taken to update customer with customer id : "
                + customer.getCustomerId() + " is : " + stopWatch.getTotalTimeSeconds());

        return customerResponse;
    }
}
