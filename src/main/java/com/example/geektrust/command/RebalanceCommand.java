package com.example.geektrust.command;

import com.example.geektrust.model.FundType;
import com.example.geektrust.model.Portfolio;
import com.example.geektrust.printer.Printer;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

public class RebalanceCommand implements Command {
    private Portfolio portfolio;
    private Printer printer;

    public RebalanceCommand(Portfolio portfolio, Printer printer) {
        this.portfolio = portfolio;
        this.printer = printer;
    }

    @Override
    public void execute() {
        int fundDataSize = this.portfolio.getFund(FundType.EQUITY).getMonthlyBalances().size();
        boolean isFundDataLessThanSixMonths = fundDataSize < 6;
        if (isFundDataLessThanSixMonths) {
            printer.print("CANNOT_REBALANCE");
            return;
        }
        boolean isRebalancedInDecember = this.portfolio.getFund(FundType.EQUITY).getMonthlyBalances().containsKey(Month.DECEMBER);
        if (isRebalancedInDecember) {
            printBalanceForEachFund(Month.DECEMBER);
        } else {
            printBalanceForEachFund(Month.JUNE);
        }
    }

    private void printBalanceForEachFund(Month month) {
        List<BigDecimal> eachFundBalance = this.portfolio.getEachFundBalance(month);
        printer.print(eachFundBalance.get(0) + " " + eachFundBalance.get(1) + " " + eachFundBalance.get(2));
    }
}
