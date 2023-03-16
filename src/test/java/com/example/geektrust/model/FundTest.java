package com.example.geektrust.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FundTest {

    @Test
    void shouldAddFundValue() {
        Fund equityFund = new Fund(FundType.EQUITY, new BigDecimal("6000"), Month.JANUARY);

        BigDecimal updatedEquityValue = equityFund.addValue(new BigDecimal("4000"), Month.FEBRUARY);

        assertEquals(new BigDecimal("10000"), updatedEquityValue);
    }

    @Test
    void shouldAddFundValueBasedOnAddPercentageGiven() {
        BigDecimal addPercentage = new BigDecimal("4.00");
        Fund equityFund = new Fund(FundType.EQUITY, new BigDecimal("6000"), Month.JANUARY);

        BigDecimal updatedEquityValue = equityFund.addValueByPercentage(addPercentage, Month.JANUARY);

        assertEquals(new BigDecimal("6240"), updatedEquityValue);
    }

    @Test
    void shouldSubtractFundValueBasedOnSubtractPercentageGiven() {
        BigDecimal subtractPercentage = new BigDecimal("6");
        Fund equityFund = new Fund(FundType.DEBT, new BigDecimal("11880"), Month.JANUARY);

        BigDecimal updatedEquityValue = equityFund.decreaseValueByPercentage(subtractPercentage, Month.JANUARY);

        assertEquals(new BigDecimal("11167"), updatedEquityValue);
    }
}