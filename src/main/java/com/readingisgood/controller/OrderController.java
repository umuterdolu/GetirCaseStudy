package com.readingisgood.controller;

import com.readingisgood.dto.OrderRequestDto;
import com.readingisgood.entity.OrderEntity;
import com.readingisgood.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Umut Ismet Erdolu
 */
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {

    private final OrderService orderService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/newOrder")
    public String takeNewOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        return orderService.takeNewOrder(orderRequestDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/orderById")
    public List<OrderEntity> orderById() {
        return orderService.orderById();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/orderByDateInterval")
    public List<OrderEntity> orderByDateInterval(@RequestParam String startDate, @RequestParam String endDate) {
        return orderService.orderByDateInterval(LocalDate.parse(startDate), LocalDate.parse(endDate));
    }
}
