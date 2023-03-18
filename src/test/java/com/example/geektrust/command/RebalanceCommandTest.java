package com.example.geektrust.command;

import com.example.geektrust.model.FundType;
import com.example.geektrust.model.Portfolio;
import com.example.geektrust.model.Rebalancer;
import com.example.geektrust.printer.ConsolePrinter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RebalanceCommandTest {

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }

    @Test
    void shouldPrintJuneRebalancedData() {
        ConsolePrinter consolePrinter = new ConsolePrinter();
        Portfolio portfolio = TestUtils.getPortfolioWith6MonthsFundMockData();
        new Rebalancer(portfolio).rebalancePortfolio();
        RebalanceCommand rebalanceCommand = new RebalanceCommand(portfolio, consolePrinter);

        rebalanceCommand.execute();

        assertEquals("21965 10982 3660", outputStream.toString().trim());
    }

    @Test
    void shouldPrintDecemberRebalancedData() {
        ConsolePrinter consolePrinter = new ConsolePrinter();
        Portfolio portfolio = TestUtils.getPortfolioWith12MonthsFundMockData();
        new Rebalancer(portfolio).rebalancePortfolio();
        RebalanceCommand rebalanceCommand = new RebalanceCommand(portfolio, consolePrinter);

        rebalanceCommand.execute();

        assertEquals("23619 11809 3936", outputStream.toString().trim());
    }
}