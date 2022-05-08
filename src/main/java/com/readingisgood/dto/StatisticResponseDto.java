package com.readingisgood.dto;

/**
 * @author Umut Ismet Erdolu
 */
public record StatisticResponseDto(String month, int totalOrderCount, int totalBookCount, double totalPurchaseAmount) {

}
