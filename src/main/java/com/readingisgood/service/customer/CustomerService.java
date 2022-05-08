package com.readingisgood.service.customer;

import com.readingisgood.dto.CustomerRequestDto;
import com.readingisgood.entity.OrderEntity;

import java.util.List;

/**
 * @author Umut Ismet Erdolu
 */
public interface CustomerService {
    String registerNewCustomer(CustomerRequestDto customerRequestDto);

    List<OrderEntity> ordersOfCustomer(String email);
}
