package com.readingisgood.constants;

/**
 * @author Umut Ismet Erdolu
 */
public class ResponseMessagesConstants {
    public static final String CUSTOMER_IS_ALREADY_REGISTERED = "Customer is already registered.";
    public static final String CUSTOMER_HAS_BEEN_REGISTERED = "Customer has been registered.";
    public static final String BOOK_IS_ALREADY_REGISTERED = "Book is already registered.";
    public static final String BOOK_HAS_BEEN_REGISTERED = "Book has been registered.";
    public static final String BOOK_STOCK_HAS_BEEN_UPDATED = "Book's stock has been updated. New stock is: ";

    private ResponseMessagesConstants() {
        throw new IllegalStateException("This is a constant class.");
    }
}
