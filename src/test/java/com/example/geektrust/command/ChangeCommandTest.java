package com.example.geektrust.command;

import com.example.geektrust.model.Fund;
import com.example.geektrust.model.FundType;
import com.example.geektrust.model.Portfolio;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ChangeCommandTest {

    @Test
    void shouldChangeEachFundForGivenPercentage() {
        Fund equityFund = new Fund(FundType.EQUITY, new BigDecimal("12593"), Month.JANUARY);
        Fund debtFund = new Fund(FundType.DEBT, new BigDecimal("8897"), Month.JANUARY);
        Fund goldFund = new Fund(FundType.GOLD, new BigDecimal("2772"), Month.JANUARY);
        Portfolio portfolio = new Portfolio();
        portfolio.addFund(equityFund);
        portfolio.addFund(debtFund);
        portfolio.addFund(goldFund);
        List<String> parameters = Arrays.asList("8.00%", "-3.00%", "7.00%");
        ChangeCommand changeCommand = new ChangeCommand(portfolio, parameters, Month.JANUARY);

        changeCommand.execute();

        List<BigDecimal> expectedFundBalances = Arrays.asList(new BigDecimal("13600"), new BigDecimal("8630"), new BigDecimal("2966"));
        assertEquals(expectedFundBalances, portfolio.getEachFundBalance(Month.JANUARY));
    }

    @Test
    void shouldRebalanceEachFundForJune() {
        Portfolio portfolio = TestUtils.getPortfolioWith6MonthsFundMockData();
        List<String> parameters = Arrays.asList("10.00%", "8.00%", "-5.00%");
        ChangeCommand changeCommand = new ChangeCommand(portfolio, parameters, Month.JUNE);

        changeCommand.execute();

        List<BigDecimal> expectedFundBalances = Arrays.asList(new BigDecimal("23619"), new BigDecimal("11809"), new BigDecimal("3936"));
        assertEquals(expectedFundBalances, portfolio.getEachFundBalance(Month.JUNE));
    }

    @Test
    void shouldRebalanceEachFundForDecember() {
        Portfolio portfolio = TestUtils.getPortfolioWith6MonthsFundMockData();
        portfolio.addValueToFund(FundType.EQUITY, new BigDecimal("30000"), Month.DECEMBER);
        portfolio.addValueToFund(FundType.DEBT, new BigDecimal("40000"), Month.DECEMBER);
        portfolio.addValueToFund(FundType.GOLD, new BigDecimal("50000"), Month.DECEMBER);
        List<String> parameters = Arrays.asList("10.00%", "8.00%", "-5.00%");
        ChangeCommand changeCommand = new ChangeCommand(portfolio, parameters, Month.DECEMBER);

        changeCommand.execute();

        List<BigDecimal> expectedFundBalances = Arrays.asList(new BigDecimal("54590"), new BigDecimal("56864"), new BigDecimal("51612"));
        assertEquals(expectedFundBalances, portfolio.getEachFundBalance(Month.DECEMBER));
    }
}