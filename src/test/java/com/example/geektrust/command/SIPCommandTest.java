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

class SIPCommandTest {

    @Test
    void shouldAddSIPToEachFundForGivenMonth() {
        Fund equityFund = new Fund(FundType.EQUITY, new BigDecimal("6000"), Month.JANUARY);
        Fund debtFund = new Fund(FundType.DEBT, new BigDecimal("3000"), Month.JANUARY);
        Fund goldFund = new Fund(FundType.GOLD, new BigDecimal("1000"), Month.JANUARY);
        Portfolio portfolio = new Portfolio();
        portfolio.addFund(equityFund);
        portfolio.addFund(debtFund);
        portfolio.addFund(goldFund);
        List<String> parameters = Arrays.asList("2000", "3000", "4000");
        SIPCommand sipCommand = new SIPCommand(portfolio, parameters, Month.FEBRUARY);

        sipCommand.execute();

        List<BigDecimal> expectedFundBalances = Arrays.asList(new BigDecimal("8000"), new BigDecimal("6000"), new BigDecimal("5000"));
        assertEquals(portfolio.getEachFundBalance(Month.FEBRUARY), expectedFundBalances);
    }
}