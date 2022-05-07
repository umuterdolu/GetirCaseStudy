package com.readingisgood.entity;

import com.readingisgood.enums.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * @author Umut Ismet Erdolu
 */
@Document(collection = "orders")
public record OrderEntity(@Id String orderId, CustomerEntity customer, BookEntity book,
                          Status status, LocalDate orderDate) {

}
