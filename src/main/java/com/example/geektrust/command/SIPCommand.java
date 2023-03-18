package com.example.geektrust.command;

import com.example.geektrust.model.FundType;
import com.example.geektrust.model.Portfolio;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

public class SIPCommand implements Command {
    private final Portfolio portfolio;
    private List<String> parameters;
    private final Month month;

    public SIPCommand(Portfolio portfolio, List<String> parameters, Month month) {
        this.portfolio = portfolio;
        this.parameters = parameters;
        this.month = month;
    }

    @Override
    public void execute() {
        portfolio.addValueToFund(FundType.EQUITY, new BigDecimal(parameters.get(0)), month);
        portfolio.addValueToFund(FundType.DEBT, new BigDecimal(parameters.get(1)), month);
        portfolio.addValueToFund(FundType.GOLD, new BigDecimal(parameters.get(2)), month);
    }
}
