package com.example.geektrust.command;

import com.example.geektrust.model.Portfolio;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AllocateCommandTest {

    @Test
    void shouldAllocateEquityDebtAndGoldFundToPortfolioForGivenMonth() {
        String equityValue = "6000";
        String debtValue = "3000";
        String goldValue = "1000";
        List<String> parameters = Arrays.asList(equityValue, debtValue, goldValue);
        Portfolio portfolio = new Portfolio();
        AllocateCommand allocateCommand = new AllocateCommand(portfolio, parameters);

        allocateCommand.execute();

        assertEquals(portfolio.getFunds().size(), 3);
    }
}