package com.example.geektrust.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FundTest {

    Fund equityFund;

    @BeforeEach
    void setUp() {
        equityFund = new Fund(FundType.EQUITY, new BigDecimal("6000"), Month.JANUARY);
    }

    @Test
    void shouldAddFundValue() {
        BigDecimal totalEquityValue = equityFund.addSIPValue(new BigDecimal("4000"), Month.FEBRUARY);

        assertEquals(new BigDecimal("10000"), totalEquityValue);
    }

    @Test
    void shouldUpdateValueForExistingFundData() {
        BigDecimal updatedEquityValue = equityFund.updateValue(new BigDecimal("4000"), Month.JANUARY);

        assertEquals(new BigDecimal("4000"), updatedEquityValue);
    }

    @Test
    void shouldAddFundValueBasedOnAddPercentageGiven() {
        BigDecimal addPercentage = new BigDecimal("4.00");
        BigDecimal updatedEquityValue = equityFund.addValueByPercentage(addPercentage, Month.JANUARY);

        assertEquals(new BigDecimal("6240"), updatedEquityValue);
    }

    @Test
    void shouldDecreaseFundValueBasedOnSubtractPercentageGiven() {
        BigDecimal subtractPercentage = new BigDecimal("6");
        Fund equityFund = new Fund(FundType.DEBT, new BigDecimal("11880"), Month.JANUARY);

        BigDecimal updatedEquityValue = equityFund.decreaseValueByPercentage(subtractPercentage, Month.JANUARY);

        assertEquals(new BigDecimal("11167"), updatedEquityValue);
    }

    @Test
    void shouldReturnMonthlyBalances() {
        Map<Month, BigDecimal> monthlyBalances = equityFund.getMonthlyBalances();

        assertEquals(new BigDecimal("6000"), monthlyBalances.get(Month.JANUARY));
    }

    @Test
    void shouldReturnInitialAllocation() {
        equityFund.addSIPValue(new BigDecimal("2000"), Month.FEBRUARY);

        assertEquals(new BigDecimal("6000"), equityFund.getInitialAllocation());
    }

    @Test
    void shouldReturnCurrentFlooredValue() {
        equityFund.addSIPValue(new BigDecimal("5000.56"), Month.FEBRUARY);

        assertEquals(new BigDecimal("11000"), equityFund.getCurrentValue());
    }
}