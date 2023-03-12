package com.example.geektrust.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FundTest {

    @Test
    void shouldAddFundValueBasedOnAddPercentageGiven() {
        BigDecimal addPercentage = new BigDecimal("4.00");
        Fund equityFund = new Fund(FundTypes.EQUITY, new BigDecimal("6000"));

        BigDecimal updatedEquityValue = equityFund.addValue(addPercentage);

        assertEquals(new BigDecimal("6240"), updatedEquityValue);
    }

    @Test
    void shouldSubtractFundValueBasedOnSubtractPercentageGiven() {
        BigDecimal subtractPercentage = new BigDecimal("6");
        Fund equityFund = new Fund(FundTypes.DEBT, new BigDecimal("11880"));

        BigDecimal updatedEquityValue = equityFund.decreaseValue(subtractPercentage);

        assertEquals(new BigDecimal("11167"), updatedEquityValue);
    }
}