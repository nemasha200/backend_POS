package com.example.pos.order;


import com.example.pos.orderitem.OrderItem;
import com.example.pos.orderitem.OrderItemService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/order")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {

    private final OrderService orderService;



    public OrderController(OrderService orderService) {
        this.orderService = orderService;

    }

    @PostMapping
    public Order addNewOrder(@RequestBody List<OrderItem> orders) {



        return orderService.saveOrder(orders);
    }
}
