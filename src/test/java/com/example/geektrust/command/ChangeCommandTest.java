package com.example.geektrust.command;

import com.example.geektrust.model.FundType;
import com.example.geektrust.model.Portfolio;
import org.junit.jupiter.api.Test;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;


class ChangeCommandTest {

    @Test
    void shouldChangeEachFundForGivenPercentage() {
        Portfolio portfolio = mock(Portfolio.class);
        List<String> parameters = Arrays.asList("CHANGE", "8.00%", "-3.00%", "7.00%");
        ChangeCommand changeCommand = new ChangeCommand(portfolio, parameters, Month.MAY);

        changeCommand.execute();

        verify(portfolio, times(3)).updateFundValue(any(FundType.class), any(), any(Month.class));
    }
}