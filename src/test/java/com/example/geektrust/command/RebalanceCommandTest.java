package com.example.geektrust.command;

import com.example.geektrust.command.RebalanceCommand;
import com.example.geektrust.model.Fund;
import com.example.geektrust.model.FundType;
import com.example.geektrust.model.Portfolio;
import com.example.geektrust.printer.ConsolePrinter;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RebalanceCommandTest {

    @Disabled
    @Test
    void shouldRebalanceEachFund() {
        Fund equityFund = new Fund(FundType.EQUITY, new BigDecimal("6000"), Month.JANUARY);
        Fund debtFund = new Fund(FundType.DEBT, new BigDecimal("3000"), Month.JANUARY);
        Fund goldFund = new Fund(FundType.GOLD, new BigDecimal("1000"), Month.JANUARY);
        ConsolePrinter consolePrinter = new ConsolePrinter();
        Portfolio portfolio = new Portfolio();
        portfolio.addFund(equityFund);
        portfolio.addFund(debtFund);
        portfolio.addFund(goldFund);

        RebalanceCommand rebalanceCommand = new RebalanceCommand(portfolio, consolePrinter);
        rebalanceCommand.execute();

        assertEquals(new BigDecimal("13600"), portfolio.getFund(FundType.EQUITY).getCurrentValue());
        assertEquals(new BigDecimal("8630"), portfolio.getFund(FundType.DEBT).getCurrentValue());
        assertEquals(new BigDecimal("2966"), portfolio.getFund(FundType.GOLD).getCurrentValue());
    }
}