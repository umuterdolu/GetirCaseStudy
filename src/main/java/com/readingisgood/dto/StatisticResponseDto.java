package com.readingisgood.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Umut Ismet Erdolu
 */
@Builder
@Getter
@Setter
public class StatisticResponseDto {
    private String month;
    private int totalOrderCount;
    private int totalBookCount;
    private double totalPurchaseAmount;

    public StatisticResponseDto(String month, int totalOrderCount, int totalBookCount, double totalPurchaseAmount) {
        this.month = month;
        this.totalBookCount = totalOrderCount;
        this.totalOrderCount = totalBookCount;
        this.totalPurchaseAmount = totalPurchaseAmount;
    }
}
