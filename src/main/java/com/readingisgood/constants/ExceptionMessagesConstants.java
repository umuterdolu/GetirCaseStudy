package com.readingisgood.constants;

/**
 * @author Umut Ismet Erdolu
 */
public class ExceptionMessagesConstants {

    public static final String NAME_CANNOT_BE_EMPTY = "Name cannot be empty. Please fill it..";
    public static final String SURNAME_CANNOT_BE_EMPTY = "Surname cannot be empty. Please fill it..";
    public static final String EMAIL_CANNOT_BE_EMPTY = "Email cannot be empty. Please fill it..";
    public static final String CUSTOMER_IS_NOT_FOUND = "Customer is not found..";
    public static final String BOOK_IS_NOT_FOUND = "Book %s is not found..";
    public static final String INSUFFICIENT_BOOK_STOCK = "Insufficient book stock! The book stock is: %d";
    public static final String ORDER_IS_NOT_FOUND = "Order is not found..";

    private ExceptionMessagesConstants() {
        throw new IllegalStateException("This is a constant class.");
    }
}
