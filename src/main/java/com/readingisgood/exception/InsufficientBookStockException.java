package com.readingisgood.exception;

import java.io.Serial;

/**
 * @author Umut Ismet Erdolu
 */
public class InsufficientBookStockException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 3819764911297506501L;

    public InsufficientBookStockException(String message) {
        super(message);
    }
}
