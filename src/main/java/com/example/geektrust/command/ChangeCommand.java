package com.example.geektrust.command;

import com.example.geektrust.model.FundType;
import com.example.geektrust.model.Portfolio;
import com.example.geektrust.model.Rebalancer;

import java.time.Month;
import java.util.List;

public class ChangeCommand implements Command {
    private final Portfolio portfolio;
    private List<String> parameters;
    private final Month month;

    public ChangeCommand(Portfolio portfolio, List<String> parameters, Month month) {
        this.portfolio = portfolio;
        this.parameters = parameters;
        this.month = month;
    }

    @Override
    public void execute() {
        portfolio.updateFundValue(FundType.EQUITY, parameters.get(1), month);
        portfolio.updateFundValue(FundType.DEBT, parameters.get(2), month);
        portfolio.updateFundValue(FundType.GOLD, parameters.get(3), month);

        if (month == Month.JUNE || month == Month.DECEMBER) {
            new Rebalancer(portfolio).rebalance();
        }
    }
}
