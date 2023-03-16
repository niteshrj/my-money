package com.example.geektrust.command;

import com.example.geektrust.command.SIPCommand;
import com.example.geektrust.model.Fund;
import com.example.geektrust.model.FundType;
import com.example.geektrust.model.Portfolio;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Month;

import static org.mockito.Mockito.*;

class SIPCommandTest {

    @Test
    void shouldAddSIPToEachFundForGivenMonth() {
        Fund equityFund = new Fund(FundType.EQUITY, new BigDecimal("6000"), Month.JANUARY);
        Fund debtFund = new Fund(FundType.DEBT, new BigDecimal("3000"), Month.JANUARY);
        Fund goldFund = new Fund(FundType.GOLD, new BigDecimal("1000"), Month.JANUARY);
        Portfolio portfolio = mock(Portfolio.class);
        portfolio.addFund(equityFund);
        portfolio.addFund(debtFund);
        portfolio.addFund(goldFund);

        SIPCommand sipCommand = new SIPCommand(portfolio, "2000", "3000", "4000", Month.FEBRUARY);
        sipCommand.execute();

        verify(portfolio, times(3)).addValueToFund(any(FundType.class), any(BigDecimal.class), any(Month.class));
    }
}