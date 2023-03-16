package com.example.geektrust.command;

import com.example.geektrust.model.Portfolio;
import com.example.geektrust.printer.Printer;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

public class BalanceCommand implements Command {
    private final Portfolio portfolio;
    private Month month;
    private Printer printer;

    public BalanceCommand(Portfolio portfolio, String month, Printer printer) {
        this.portfolio = portfolio;
        this.month = Month.valueOf(month);
        this.printer = printer;
    }

    @Override
    public void execute() {
        List<BigDecimal> eachFundBalance = this.portfolio.getEachFundBalance(month);
        printer.print(eachFundBalance.get(0) + " " + eachFundBalance.get(1) + " " + eachFundBalance.get(2));
    }
}
