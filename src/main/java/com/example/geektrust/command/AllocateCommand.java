package com.example.geektrust.command;

import com.example.geektrust.model.Fund;
import com.example.geektrust.model.FundType;
import com.example.geektrust.model.Portfolio;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

public class AllocateCommand implements Command {
    private final Portfolio portfolio;
    private final List<String> parameters;

    public AllocateCommand(Portfolio portfolio, List<String> parameters) {
        this.portfolio = portfolio;
        this.parameters = parameters;
    }

    @Override
    public void execute() {
        Fund equityFund = new Fund(FundType.EQUITY, new BigDecimal(this.parameters.get(0)), Month.JANUARY);
        Fund debtFund = new Fund(FundType.DEBT, new BigDecimal(this.parameters.get(1)), Month.JANUARY);
        Fund goldFund = new Fund(FundType.GOLD, new BigDecimal(this.parameters.get(2)), Month.JANUARY);
        portfolio.addFund(equityFund);
        portfolio.addFund(debtFund);
        portfolio.addFund(goldFund);
    }
}
