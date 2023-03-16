package com.example.geektrust.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class FundsTest {

    @Test
    void shouldReturnFundForGivenType() {
        Fund equityFund = new Fund(FundType.EQUITY, new BigDecimal("6000"), Month.JANUARY);
        Fund debtFund = new Fund(FundType.DEBT, new BigDecimal("10000"), Month.JANUARY);
        Funds funds = new Funds();
        funds.add(equityFund);
        funds.add(debtFund);

        Fund actualfund = funds.getFund(FundType.DEBT);

        assertEquals(FundType.DEBT, actualfund.getFundType());
    }
}