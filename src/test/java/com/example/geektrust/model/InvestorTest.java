package com.example.geektrust.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertSame;

public class InvestorTest {

    @Test
    void shouldReturnPortfolioHavingEquityDebtAndGoldFund() {
        Fund equityFund = new Fund(FundTypes.EQUITY, new BigDecimal("6000"));
        Fund debtFund = new Fund(FundTypes.DEBT, new BigDecimal("3000"));
        Fund goldFund = new Fund(FundTypes.GOLD, new BigDecimal("1000"));
        Portfolio portfolio = new Portfolio();
        portfolio.addFund(equityFund);
        portfolio.addFund(debtFund);
        portfolio.addFund(goldFund);

        Investor investor = new Investor("Nitesh", portfolio);
        investor.buyFund(equityFund);
        investor.buyFund(debtFund);
        investor.buyFund(goldFund);

        assertSame(portfolio, investor.getPortfolio());
    }
}
