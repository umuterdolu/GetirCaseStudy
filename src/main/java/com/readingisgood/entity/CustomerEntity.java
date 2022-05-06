package com.readingisgood.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Umut Ismet Erdolu
 */
@Document(collection = "customers")
public record CustomerEntity(String name, String surname, @Id String idNumber) {
}
