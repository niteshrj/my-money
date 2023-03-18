package com.example.geektrust.command;

import com.example.geektrust.model.Fund;
import com.example.geektrust.model.FundType;
import com.example.geektrust.model.Portfolio;
import com.example.geektrust.printer.ConsolePrinter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class BalanceCommandTest {

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
    void shouldPrintBalanceForFundsAtGivenMonth() {
        ConsolePrinter consolePrinter = new ConsolePrinter();
        Fund equityFund = new Fund(FundType.EQUITY, new BigDecimal("12593"), Month.JANUARY);
        Fund debtFund = new Fund(FundType.DEBT, new BigDecimal("8897"), Month.JANUARY);
        Fund goldFund = new Fund(FundType.GOLD, new BigDecimal("2772"), Month.JANUARY);
        Portfolio portfolio = new Portfolio();
        portfolio.addFund(equityFund);
        portfolio.addFund(debtFund);
        portfolio.addFund(goldFund);
        BalanceCommand balanceCommand = new BalanceCommand(portfolio, "JANUARY", consolePrinter);

        balanceCommand.execute();

        assertEquals("12593 8897 2772", outputStream.toString().trim());
    }
}