package com.example.geektrust.model;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class Portfolio {
    private final Funds funds = new Funds();

    public void addFund(Fund fund) {
        funds.add(fund);
    }

    public Fund getFund(FundType fundType) {
        return this.funds.getFund(fundType);
    }

    public void updateFundValue(FundType fundType, String changePercentage, Month month) {
        BigDecimal percentageValue;
        Fund fund = funds.getFund(fundType);
        if (changePercentage.charAt(0) == '-') {
            percentageValue = new BigDecimal(changePercentage.substring(1, changePercentage.length() - 1));
            fund.decreaseValueByPercentage(percentageValue, month);
        } else {
            percentageValue = new BigDecimal(changePercentage.substring(0, changePercentage.length() - 1));
            fund.addValueByPercentage(percentageValue, month);
        }
    }

    public List<BigDecimal> getEachFundBalance(Month month) {
        return Arrays.asList(
                this.funds.getFund(FundType.EQUITY).getBalanceInMonth(month),
                this.funds.getFund(FundType.DEBT).getBalanceInMonth(month),
                this.funds.getFund(FundType.GOLD).getBalanceInMonth(month)
        );
    }

    public void addValueToFund(FundType fundType, BigDecimal valueToAdd, Month month) {
        funds.getFund(fundType).addValue(valueToAdd, month);
    }
}
