package com.readingisgood.constants;

/**
 * @author Umut Ismet Erdolu
 */
public class ResponseMessagesConstants {
    public static final String CUSTOMER_IS_ALREADY_HAVE_REGISTERED = "Customer #{0} is already registered.";
    public static final String CUSTOMER_HAS_BEEN_REGISTERED = "Customer #{0} has been registered.";
    public static final String BOOK_IS_ALREADY_HAVE_REGISTERED = "Book #{0} is already registered.";
    public static final String BOOK_HAS_BEEN_REGISTERED = "Book #{0} has been registered.";
    public static final String BOOK_STOCK_HAS_BEEN_UPDATED = "Book #{0} stock has been updated. New stock is {1}";

    private ResponseMessagesConstants() {
        throw new IllegalStateException("This is a constant class.");
    }
}
