package com.readingisgood.dto;

import javax.validation.constraints.Min;
import java.util.Objects;

/**
 * @author Umut Ismet Erdolu
 */
public record OrderRequestDto(String bookName, String email,
                              @Min(value = 1, message = "Count must be grater than 0!") int count) {
    public OrderRequestDto {
        Objects.requireNonNull(bookName);
        Objects.requireNonNull(email);
    }
}
