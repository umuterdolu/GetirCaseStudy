package com.readingisgood.dto;

import com.readingisgood.constants.ExceptionMessagesConstants;

import java.util.Objects;

/**
 * @author Umut Ismet Erdolu
 */
public record CustomerRequestDto(String name, String surname, String email) {
    public CustomerRequestDto {
        Objects.requireNonNull(name, ExceptionMessagesConstants.NAME_CANNOT_BE_EMPTY);
        Objects.requireNonNull(surname, ExceptionMessagesConstants.SURNAME_CANNOT_BE_EMPTY);
        Objects.requireNonNull(email, ExceptionMessagesConstants.EMAIL_CANNOT_BE_EMPTY);
    }
}
