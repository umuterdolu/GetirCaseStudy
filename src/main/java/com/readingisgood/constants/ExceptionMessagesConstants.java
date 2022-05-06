package com.readingisgood.constants;

/**
 * @author Umut Ismet Erdolu
 */
public class ExceptionMessagesConstants {

    public static final String NAME_CANNOT_BE_EMPTY = "Name cannot be empty. Please fill it.";
    public static final String SURNAME_CANNOT_BE_EMPTY = "Surname cannot be empty. Please fill it.";
    public static final String ID_NO_CANNOT_BE_EMPTY = "ID No cannot be empty. Please fill it.";

    private ExceptionMessagesConstants() {
        throw new IllegalStateException("This is a constant class.");
    }
}
