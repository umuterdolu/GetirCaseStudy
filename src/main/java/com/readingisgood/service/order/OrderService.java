package com.readingisgood.service.order;

import com.readingisgood.dto.OrderRequestDto;
import com.readingisgood.entity.OrderEntity;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Umut Ismet Erdolu
 */
public interface OrderService {
    String takeNewOrder(OrderRequestDto orderRequestDto);

    List<OrderEntity> orderById();

    List<OrderEntity> orderByDateInterval(LocalDate startDate, LocalDate endDate);
}
