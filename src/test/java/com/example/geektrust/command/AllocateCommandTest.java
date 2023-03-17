package com.example.geektrust.command;

import com.example.geektrust.command.AllocateCommand;
import com.example.geektrust.model.Fund;
import com.example.geektrust.model.Portfolio;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AllocateCommandTest {

    @Test
    void shouldAllocateEquityDebtAndGoldFundToPortfolioForGivenMonth() {
        String equityValue = "6000";
        String debtValue = "3000";
        String goldValue = "1000";
        List<String> parameters = Arrays.asList(equityValue, debtValue, goldValue);
        Portfolio mockPortfolio = mock(Portfolio.class);

        AllocateCommand allocateCommand = new AllocateCommand(mockPortfolio, parameters);
        allocateCommand.execute();

        verify(mockPortfolio, times(3)).addFund(any(Fund.class));
    }
}