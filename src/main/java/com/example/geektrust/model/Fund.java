package com.example.geektrust.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Fund {
    private final FundTypes equity;
    private BigDecimal value;
    private final String HUNDRED = "100";

    public Fund(FundTypes equity, BigDecimal value) {
        this.equity = equity;
        this.value = value;
    }

    public BigDecimal addValue(BigDecimal addPercentage) {
        BigDecimal valueToAdd = getPercentageValue(addPercentage);
        this.value = this.value.add(valueToAdd);
        return this.value;
    }

    public BigDecimal decreaseValue(BigDecimal subtractPercentage) {
        BigDecimal valueToSubtract = getPercentageValue(subtractPercentage);
        this.value = this.value.subtract(valueToSubtract).setScale(0, RoundingMode.FLOOR);
        return this.value;
    }

    private BigDecimal getPercentageValue(BigDecimal changePercentage) {
        return changePercentage
                .divide(new BigDecimal(HUNDRED), 2, RoundingMode.FLOOR)
                .multiply(this.value);
    }
}
