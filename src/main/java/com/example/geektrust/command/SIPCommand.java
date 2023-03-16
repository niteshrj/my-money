package com.example.geektrust.command;

import com.example.geektrust.model.FundType;
import com.example.geektrust.model.Portfolio;

import java.math.BigDecimal;
import java.time.Month;

public class SIPCommand implements Command {
    private final Portfolio portfolio;
    private final String equityValue;
    private final String debtValue;
    private final String goldValue;
    private final Month month;

    public SIPCommand(Portfolio portfolio, String equityValue, String debtValue, String goldValue, Month month) {
        this.portfolio = portfolio;
        this.equityValue = equityValue;
        this.debtValue = debtValue;
        this.goldValue = goldValue;
        this.month = month;
    }

    @Override
    public void execute() {
        portfolio.addValueToFund(FundType.EQUITY, new BigDecimal(equityValue), month);
        portfolio.addValueToFund(FundType.DEBT, new BigDecimal(debtValue), month);
        portfolio.addValueToFund(FundType.GOLD, new BigDecimal(goldValue), month);
    }
}
