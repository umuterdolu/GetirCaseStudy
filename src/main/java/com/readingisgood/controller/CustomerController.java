package com.readingisgood.controller;

import com.readingisgood.dto.CustomerRequestDto;
import com.readingisgood.entity.OrderEntity;
import com.readingisgood.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Umut Ismet Erdolu
 */
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class CustomerController {

    private final CustomerService customerService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/customerOrders/{email}")
    public List<OrderEntity> ordersOfCustomer(@PathVariable String email) {
        return customerService.ordersOfCustomer(email);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/registerNewCustomer")
    public String registerNewCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
        return customerService.registerNewCustomer(customerRequestDto);
    }
}
