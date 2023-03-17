package com.example.geektrust.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

public class Fund {
    private final FundType fundType;
    private BigDecimal currentValue;
    private final BigDecimal initialAllocation;
    Map<Month, BigDecimal> monthlyBalances = new HashMap<>();

    public Fund(FundType fundType, BigDecimal currentValue, Month month) {
        this.fundType = fundType;
        this.currentValue = currentValue;
        this.initialAllocation = currentValue;
        this.monthlyBalances.put(month, currentValue);
    }

    public BigDecimal addValue(BigDecimal valueToAdd, Month month) {
        this.currentValue = getFloorRoundedValue(this.currentValue.add(valueToAdd));
        this.monthlyBalances.put(month, currentValue);
        return this.currentValue;
    }

    public BigDecimal updateValue(BigDecimal valueToUpdate, Month month) {
        this.currentValue = getFloorRoundedValue(valueToUpdate);
        this.monthlyBalances.put(month, currentValue);
        return this.currentValue;
    }

    public BigDecimal addValueByPercentage(BigDecimal addPercentage, Month month) {
        BigDecimal valueToAdd = getPercentageValue(addPercentage);
        this.currentValue = getFloorRoundedValue(this.currentValue.add(valueToAdd));
        this.monthlyBalances.put(month, currentValue);
        return getFloorRoundedValue(this.currentValue);
    }

    public BigDecimal decreaseValueByPercentage(BigDecimal subtractPercentage, Month month) {
        BigDecimal valueToSubtract = getPercentageValue(subtractPercentage);
        this.currentValue = getFloorRoundedValue(this.currentValue.subtract(valueToSubtract));
        this.monthlyBalances.put(month, currentValue);
        return getFloorRoundedValue(this.currentValue);
    }

    public Map<Month, BigDecimal> getMonthlyBalances() {
        return monthlyBalances;
    }

    public FundType getFundType() {
        return fundType;
    }

    public BigDecimal getInitialAllocation() {
        return initialAllocation;
    }

    public BigDecimal getCurrentValue() {
        return getFloorRoundedValue(currentValue);
    }

    public BigDecimal getBalanceInMonth(Month month) {
        return getFloorRoundedValue(monthlyBalances.get(month));
    }

    private BigDecimal getPercentageValue(BigDecimal changePercentage) {
        String HUNDRED = "100";
        int DECIMAL_PRECISION = 2;
        return changePercentage
                .divide(new BigDecimal(HUNDRED), 5, RoundingMode.FLOOR)
                .multiply(this.currentValue).setScale(DECIMAL_PRECISION, RoundingMode.HALF_UP);
    }


    private BigDecimal getFloorRoundedValue(BigDecimal value) {
        return value.setScale(0, RoundingMode.FLOOR);
    }

}
