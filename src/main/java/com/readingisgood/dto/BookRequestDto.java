package com.readingisgood.dto;

import java.util.Objects;

/**
 * @author Umut Ismet Erdolu
 */
public record BookRequestDto(String bookName, int stock) {
    public BookRequestDto {
        Objects.requireNonNull(bookName);
    }
}
