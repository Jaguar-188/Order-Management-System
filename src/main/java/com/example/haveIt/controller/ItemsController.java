package com.example.haveIt.controller;

import com.example.haveIt.entity.models.Items;
import com.example.haveIt.service.ItemsService;
import com.example.haveIt.utils.Logging;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.boot.web.error.Error;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rest/v1/orders-controller/")
public class ItemsController {

    private ItemsService itemsService;

    private static final Logging log = Logging.getLogger();

    public ItemsController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @PostMapping("/createItem")
    @Operation(summary = "The endpoint creates the item.",
            description = "The endpoint creates the item with provided data in application.",
            method = "POST",
            responses = {@ApiResponse(responseCode = "201",
                    description = "CREATED",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Items.class))),
                    @ApiResponse(responseCode = "400",
                            description = "Bad Request",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    public Items createItem(@RequestBody Items items){


        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("Creating item with item name : " + items.getName());
        Items itemsResponse = itemsService.createItem(items);
        stopWatch.stop();
        log.info("Total time taken to create item with item id : "
                + itemsResponse.getItemId() + " is : " + stopWatch.getTotalTimeSeconds());
        return itemsResponse;
    }

    @DeleteMapping("/removeItem/{itemId}")
    @Operation(summary = "The endpoint removes the item.",
            description = "The endpoint removes the item with provided data from application.",
            method = "DELETE",
            responses = {@ApiResponse(responseCode = "204",
                    description = "No Content",
                    content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400",
                            description = "Bad Request",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    public String removeItem(@PathVariable String itemId){


        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("Removing item with itemId : " + itemId);
        itemsService.removeItem(itemId);
        stopWatch.stop();
        log.info("Total time taken to remove item with item id : "
                + itemId + " is : " + stopWatch.getTotalTimeSeconds());
        return "Successfully deleted the customer with customer id : " + itemId;
    }

    @GetMapping("/fetchItemInformation")
    @Operation(summary = "The endpoint fetches the item information.",
            description = "The endpoint fetches the item information with provided data from application.",
            method = "GET",
            responses = {@ApiResponse(responseCode = "200",
                    description = "OK",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Items.class))),
                    @ApiResponse(responseCode = "400",
                            description = "Bad Request",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    public Items fetchItemInformation(@RequestParam String itemId){


        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("Fetching item with itemId : " + itemId);
        Items itemsResponse = itemsService.fetchItemInformation(itemId);
        stopWatch.stop();
        log.info("Total time taken to fetch item information with item id : "
                + itemId + " is : " + stopWatch.getTotalTimeSeconds());
        return itemsResponse;
    }
}
