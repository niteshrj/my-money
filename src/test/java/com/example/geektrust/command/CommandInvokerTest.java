package com.example.geektrust.command;

import com.example.geektrust.model.Portfolio;
import com.example.geektrust.printer.ConsolePrinter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommandInvokerTest {

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
    void ShouldExecuteTheAddedCommand() {
        List<String> commands = getCommands();
        Portfolio portfolio = new Portfolio();
        ConsolePrinter consolePrinter = new ConsolePrinter();
        CommandInvoker commandInvoker = new CommandInvoker(portfolio, consolePrinter);
        commands.forEach(commandInvoker::addCommand);

        commandInvoker.executeCommands();

        String expectedBalanceAndRebalanceOutput = "6240 3300 1020\nCANNOT_REBALANCE";
        assertEquals(expectedBalanceAndRebalanceOutput, outputStream.toString().trim());
    }

    @Test
    void ShouldPrintCOMMAND_NOT_FOUNDForUnknownCommand() {
        List<String> commands = getCommands();
        Portfolio portfolio = new Portfolio();
        ConsolePrinter consolePrinter = new ConsolePrinter();
        CommandInvoker commandInvoker = new CommandInvoker(portfolio, consolePrinter);
        String deAllocate = "DEALLOCATE 6000 3000 1000";
        commandInvoker.addCommand(deAllocate);

        commandInvoker.executeCommands();

        assertEquals("COMMAND_NOT_FOUND", outputStream.toString().trim());
    }


    private List<String> getCommands() {
        return Arrays.asList(
                "ALLOCATE 6000 3000 1000",
                "SIP 2000 1000 500",
                "CHANGE 4.00% 10.00% 2.00% JANUARY",
                "BALANCE JANUARY",
                "REBALANCE"
        );
    }
}