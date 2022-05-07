package com.readingisgood.repository;

import com.readingisgood.entity.BookEntity;
import com.readingisgood.entity.CustomerEntity;
import com.readingisgood.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Umut Ismet Erdolu
 */
public interface OrderRepository extends MongoRepository<OrderEntity, String> {
    Page<OrderEntity> findAllByCustomer(CustomerEntity customer, Pageable pageable);

    OrderEntity findByCustomerAndBook(CustomerEntity customer, BookEntity book);

    List<OrderEntity> findByOrderDateBetween(LocalDate startDate, LocalDate endDate);
}
