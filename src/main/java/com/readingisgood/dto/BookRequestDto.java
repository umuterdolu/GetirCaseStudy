package com.readingisgood.dto;

import javax.validation.constraints.Min;
import java.util.Objects;

/**
 * @author Umut Ismet Erdolu
 */
public record BookRequestDto(String bookName,
                             @Min(value = 0, message = "Stock must be greater or equal than 0!") int stock,
                             @Min(value = 10, message = "Price must be greater or equal than 10!") double price) {
    public BookRequestDto {
        Objects.requireNonNull(bookName);
    }
}
