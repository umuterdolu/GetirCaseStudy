package com.readingisgood.dto;

import java.util.Objects;

/**
 * @author Umut Ismet Erdolu
 */
public record OrderRequestDto(String bookName, String email, int count) {
    public OrderRequestDto {
        Objects.requireNonNull(bookName);
        Objects.requireNonNull(email);
    }
}
