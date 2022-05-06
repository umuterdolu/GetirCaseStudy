package com.readingisgood.exception;

import java.io.Serial;

/**
 * @author Umut Ismet Erdolu
 */
public class BookNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 7819764911297516501L;

    public BookNotFoundException() {
        super();
    }
}
