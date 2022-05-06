package com.readingisgood.controller;

import com.readingisgood.dto.CustomerRequestDto;
import com.readingisgood.entity.OrderEntity;
import com.readingisgood.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Umut Ismet Erdolu
 */
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class CustomerController {

    private final CustomerService customerService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/customerOrders/{customerId}")
    public Page<OrderEntity> ordersOfCustomer(@PathVariable String customerId) {
        return customerService.ordersOfCustomer(customerId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/registerCustomer")
    public String registerNewCustomer(@Valid CustomerRequestDto customerRequestDto) {
        return customerService.registerNewCustomer(customerRequestDto);
    }
}
