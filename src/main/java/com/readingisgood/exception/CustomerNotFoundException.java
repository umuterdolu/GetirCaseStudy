package com.readingisgood.exception;

import java.io.Serial;

/**
 * @author Umut Ismet Erdolu
 */
public class CustomerNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 7819764911297506501L;

    public CustomerNotFoundException() {
        super();
    }

}
