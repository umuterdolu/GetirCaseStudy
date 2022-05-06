package com.readingisgood.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Umut Ismet Erdolu
 */
@Document(collection = "books")
public record BookEntity(@Id String bookName, int stock) {
}
