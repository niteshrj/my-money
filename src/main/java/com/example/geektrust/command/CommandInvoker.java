package com.example.geektrust.command;

import com.example.geektrust.model.Portfolio;
import com.example.geektrust.printer.Printer;

import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandInvoker {
    private final List<Command> commands;
    private final Portfolio portfolio;
    private final Printer printer;
    private String sipEquityValue;
    private String sipDebtValue;
    private String sipGoldValue;

    public CommandInvoker(Portfolio portfolio, Printer printer) {
        this.portfolio = portfolio;
        this.printer = printer;
        this.commands = new ArrayList<>();
    }

    public void addCommand(String line) {
        List<String> instructions = Arrays.asList(line.trim().split(" "));
        CommandTypes command = CommandTypes.valueOf(instructions.get(0));
        List<String> parameters = instructions.subList(1, instructions.size());

        switch (command) {
            case ALLOCATE:
                this.commands.add(new AllocateCommand(portfolio, parameters));
                break;
            case SIP:
                this.sipEquityValue = parameters.get(0);
                this.sipDebtValue = parameters.get(1);
                this.sipGoldValue = parameters.get(2);
                break;
            case CHANGE:
                Month changeMonth = Month.valueOf(parameters.get(3));
                if (changeMonth != Month.JANUARY) {
                    this.commands.add(new SIPCommand(portfolio, this.sipEquityValue, this.sipDebtValue, this.sipGoldValue, changeMonth));
                }
                this.commands.add(new ChangeCommand(portfolio, parameters, changeMonth));
                break;
            case BALANCE:
                this.commands.add(new BalanceCommand(portfolio, parameters.get(0), printer));
                break;
            case REBALANCE:
                this.commands.add(new RebalanceCommand(portfolio, printer));
                break;
        }
    }

    public void executeCommands() {
        this.commands.forEach(Command::execute);
    }
}
