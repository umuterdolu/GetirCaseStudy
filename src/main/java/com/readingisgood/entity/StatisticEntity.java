package com.readingisgood.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Umut Ismet Erdolu
 */
@Document("statistics")
public record StatisticEntity(@Id String email, CustomerEntity customer, BookEntity book, OrderEntity order) {
}
