package com.example.geektrust.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class RebalancerTest {

    @Test
    void shouldNotRebalanceFundsForLessThan6MonthsData() {
        Fund equityFund = new Fund(FundType.EQUITY, new BigDecimal("6000"), Month.JANUARY);
        Fund debtFund = new Fund(FundType.DEBT, new BigDecimal("3000"), Month.JANUARY);
        Fund goldFund = new Fund(FundType.GOLD, new BigDecimal("1000"), Month.JANUARY);
        Portfolio portfolio = new Portfolio();
        portfolio.addFund(equityFund);
        portfolio.addFund(debtFund);
        portfolio.addFund(goldFund);

        Rebalancer rebalancer = new Rebalancer(portfolio);
        rebalancer.rebalancePortfolio();

        BigDecimal equityCurrentValue = portfolio.getFund(FundType.EQUITY).getCurrentValue();
        BigDecimal debtCurrentValue = portfolio.getFund(FundType.DEBT).getCurrentValue();
        BigDecimal goldCurrentValue = portfolio.getFund(FundType.GOLD).getCurrentValue();
        assertEquals(new BigDecimal("6000"), equityCurrentValue);
        assertEquals(new BigDecimal("3000"), debtCurrentValue);
        assertEquals(new BigDecimal("1000"), goldCurrentValue);
    }

    @Test
    void shouldRebalanceFundsIfMoreThan6MonthsDataAvailable() {
        Portfolio portfolio = getPortfolioWith6MonthsData();

        Rebalancer rebalancer = new Rebalancer(portfolio);
        rebalancer.rebalancePortfolio();

        BigDecimal equityCurrentValue = portfolio.getFund(FundType.EQUITY).getCurrentValue();
        BigDecimal debtCurrentValue = portfolio.getFund(FundType.DEBT).getCurrentValue();
        BigDecimal goldCurrentValue = portfolio.getFund(FundType.GOLD).getCurrentValue();
        assertEquals(new BigDecimal("23619"), equityCurrentValue);
        assertEquals(new BigDecimal("11809"), debtCurrentValue);
        assertEquals(new BigDecimal("3936"), goldCurrentValue);
    }

    private Portfolio getPortfolioWith6MonthsData() {
        Fund equityFund = new Fund(FundType.EQUITY, new BigDecimal("6000"), Month.JANUARY);
        Fund debtFund = new Fund(FundType.DEBT, new BigDecimal("3000"), Month.JANUARY);
        Fund goldFund = new Fund(FundType.GOLD, new BigDecimal("1000"), Month.JANUARY);
        equityFund.updateValue(new BigDecimal("6240"), Month.JANUARY);
        debtFund.updateValue(new BigDecimal("3300"), Month.JANUARY);
        goldFund.updateValue(new BigDecimal("1020"), Month.JANUARY);
        equityFund.updateValue(new BigDecimal("7416"), Month.FEBRUARY);
        debtFund.updateValue(new BigDecimal("6020"), Month.FEBRUARY);
        goldFund.updateValue(new BigDecimal("1520"), Month.FEBRUARY);
        equityFund.updateValue(new BigDecimal("7416"), Month.MARCH);
        debtFund.updateValue(new BigDecimal("6020"), Month.MARCH);
        goldFund.updateValue(new BigDecimal("1520"), Month.MARCH);
        equityFund.updateValue(new BigDecimal("7416"), Month.APRIL);
        debtFund.updateValue(new BigDecimal("6020"), Month.APRIL);
        goldFund.updateValue(new BigDecimal("1520"), Month.APRIL);
        equityFund.updateValue(new BigDecimal("7416"), Month.MAY);
        debtFund.updateValue(new BigDecimal("6020"), Month.MAY);
        goldFund.updateValue(new BigDecimal("1520"), Month.MAY);
        equityFund.updateValue(new BigDecimal("21590"), Month.JUNE);
        debtFund.updateValue(new BigDecimal("13664"), Month.JUNE);
        goldFund.updateValue(new BigDecimal("4112"), Month.JUNE);
        Portfolio portfolio = new Portfolio();
        portfolio.addFund(equityFund);
        portfolio.addFund(debtFund);
        portfolio.addFund(goldFund);
        return portfolio;
    }
}