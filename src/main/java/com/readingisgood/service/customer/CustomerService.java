package com.readingisgood.service.customer;

import com.readingisgood.dto.CustomerRequestDto;
import com.readingisgood.entity.OrderEntity;
import org.springframework.data.domain.Page;

/**
 * @author Umut Ismet Erdolu
 */
public interface CustomerService {
    String registerNewCustomer(CustomerRequestDto customerRequestDto);

    Page<OrderEntity> ordersOfCustomer(String customerId);
}
