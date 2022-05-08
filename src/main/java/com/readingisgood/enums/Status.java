package com.readingisgood.enums;

import lombok.Getter;

/**
 * @author Umut Ismet Erdolu
 */
@Getter
public enum Status {
    PENDING("PENDING"),
    CONFIRMED("CONFIRMED"),
    SHIPPED("SHIPPED"),
    CANCELED("CANCELED"),
    READY_FOR_DELIVERY("READY_FOR_DELIVERY");

    private final String value;

    Status(String value) {
        this.value = value;
    }
}
