package main.java.ru.clevertec.check.controller;

import main.java.ru.clevertec.check.dto.request.Bucket;
import main.java.ru.clevertec.check.dto.response.Check;
import main.java.ru.clevertec.check.service.OrderService;

public class MainOrderController {

    private final OrderService orderService;

    public MainOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public Check createCheck(Bucket bucket) {
        return orderService.generateCheck(bucket);
    }
}