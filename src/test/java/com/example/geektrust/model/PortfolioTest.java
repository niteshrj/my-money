package com.example.geektrust.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PortfolioTest {

    private Portfolio portfolio;

    @BeforeEach
    public void setUp() {
        portfolio = new Portfolio();
        Fund equityFund = new Fund(FundType.EQUITY, new BigDecimal("8240"), Month.JANUARY);
        Fund debtFund = new Fund(FundType.DEBT, new BigDecimal("4300"), Month.JANUARY);
        Fund goldFund = new Fund(FundType.GOLD, new BigDecimal("1520"), Month.JANUARY);
        portfolio.addFund(equityFund);
        portfolio.addFund(debtFund);
        portfolio.addFund(goldFund);
    }

    @Test
    public void shouldUpdateFundValue() {
        portfolio.updateFundValue(FundType.EQUITY, "-10.00%", Month.JANUARY);
        portfolio.updateFundValue(FundType.DEBT, "40.00%", Month.JANUARY);
        portfolio.updateFundValue(FundType.GOLD, "0.00%", Month.JANUARY);

        List<BigDecimal> balances = portfolio.getEachFundBalance(Month.JANUARY);

        assertEquals(new BigDecimal("7416"), balances.get(0));
        assertEquals(new BigDecimal("6020"), balances.get(1));
        assertEquals(new BigDecimal("1520"), balances.get(2));
    }

    @Test
    public void shouldGetEachFundBalance() {
        List<BigDecimal> balances = portfolio.getEachFundBalance(Month.JANUARY);

        assertEquals(new BigDecimal("8240"), balances.get(0));
        assertEquals(new BigDecimal("4300"), balances.get(1));
        assertEquals(new BigDecimal("1520"), balances.get(2));
    }

    @Test
    public void shouldAddValueToFund() {
        portfolio.addValueToFund(FundType.EQUITY, new BigDecimal("5000"), Month.JANUARY);

        BigDecimal expectedEquityValue = new BigDecimal("13240");
        assertEquals(expectedEquityValue, portfolio.getFund(FundType.EQUITY).getBalanceInMonth(Month.JANUARY));
    }

    @Test
    public void shouldGetFunds() {
        List<Fund> funds = portfolio.getFunds();

        assertEquals(3, funds.size());
    }
}