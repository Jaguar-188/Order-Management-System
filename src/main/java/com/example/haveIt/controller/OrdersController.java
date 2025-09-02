package com.example.haveIt.controller;

import com.example.haveIt.constants.OrderStatus;
import com.example.haveIt.entity.models.Order;
import com.example.haveIt.service.OrderService;
import com.example.haveIt.utils.Logging;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.boot.web.error.Error;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/rest/v1/orders-controller/")
public class OrdersController {


    private OrderService orderService;
    private static final Logging log = Logging.getLogger();

    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/createOrder")
    @Operation(summary = "The endpoint creates the order.",
            description = "The endpoint creates the order with provided data in application.",
            method = "POST",
            responses = {@ApiResponse(responseCode = "201",
                    description = "CREATED",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Order.class))),
                    @ApiResponse(responseCode = "400",
                            description = "Bad Request",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    public Order createOrder(@RequestBody Order order){


        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("Creating order with customerId : " + order.getCustomerId());
        Order orderResponse = orderService.createOrder(order);
        stopWatch.stop();
        log.info("Total time taken to create order with customer id : "
                + orderResponse.getCustomerId() + " is : " + stopWatch.getTotalTimeSeconds());
        return orderResponse;
    }

    @DeleteMapping("/removeOrder/{orderId}")
    @Operation(summary = "The endpoint soft deletes the order.",
            description = "The endpoint soft deletes the order with provided data from application.",
            method = "DELETE",
            responses = {@ApiResponse(responseCode = "204",
                    description = "No Content",
                    content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400",
                            description = "Bad Request",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    public Order removeOrder(@PathVariable String orderId){


        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("Soft deleting order with order id : " + orderId);
        Order orderResponse = orderService.removeOrder(orderId);
        stopWatch.stop();
        log.info("Total time taken to soft delete the order with order id : "
                + orderId + " is : " + stopWatch.getTotalTimeSeconds());
        return orderResponse;
    }

    @GetMapping("/fetchOrderInformation/{customerId}")
    @Operation(summary = "The endpoint fetches the order details.",
            description = "The endpoint fetches the orders with provided data from application.",
            method = "GET",
            responses = {@ApiResponse(responseCode = "200",
                    description = "No Content",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Order.class))),
                    @ApiResponse(responseCode = "400",
                            description = "Bad Request",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    public List<Order> fetchOrderInformation(@PathVariable String customerId){


        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("Fetching order information with customer id : " + customerId);
        List<Order> orderResponse = orderService.fetchOrderInformation(customerId);
        stopWatch.stop();
        log.info("Total time taken to fetch the order details with customer id : "
                + customerId + " is : " + stopWatch.getTotalTimeSeconds());
        return orderResponse;
    }

    @PutMapping("/updateOrder")
    @Operation(summary = "The endpoint updates the order status.",
            description = "The endpoint updates the order status to provided status in application.",
            method = "PUT",
            responses = {@ApiResponse(responseCode = "200",
                    description = "OK",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Order.class))),
                    @ApiResponse(responseCode = "400",
                            description = "Bad Request",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    public Order updateOrder(@RequestParam String orderId,
                             @Parameter(
                                     description = "Choose one of the statuses",
                                     examples = {
                                             @ExampleObject(name = "PENDING", value = "pending"),
                                             @ExampleObject(name = "PREPARING", value = "preparing"),
                                             @ExampleObject(name = "SHIPPED", value = "shipped"),
                                             @ExampleObject(name = "CANCELLED", value = "cancelled")

                                     },
                                     allowEmptyValue = false
                             )
                             @RequestParam String status){


        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("Updating order status : " + status + " with order id : " + orderId);
        Order orderResponse = orderService.updateOrderStatus(orderId,status);
        stopWatch.stop();
        log.info("Total time taken to update the order status with order id : "
                + orderId + " is : " + stopWatch.getTotalTimeSeconds());
        return orderResponse;
    }
}
