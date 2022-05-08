package com.readingisgood.exception;

import java.io.Serial;

/**
 * @author Umut Ismet Erdolu
 */
public class OrderNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 3819764916297506501L;

    public OrderNotFoundException(String message) {
        super(message);
    }
}
