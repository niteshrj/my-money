package com.example.geektrust.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Month;

public class Rebalancer {
    private final Portfolio portfolio;
    private final int DECIMAL_PRECISION = 2;

    public Rebalancer(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public void rebalancePortfolio() {
        if (isFundDataLessThanSixMonths()) {
            return;
        }
        boolean isIt6thMonth = isMultipleOf(6);
        boolean isIt12thMonth = isMultipleOf(12);
        Fund equityFund = portfolio.getFund(FundType.EQUITY);
        Fund debtFund = portfolio.getFund(FundType.DEBT);
        Fund goldFund = portfolio.getFund(FundType.GOLD);
        BigDecimal totalInitialAllocation = getTotalInitialAllocation(equityFund, debtFund, goldFund);
        BigDecimal equityDesiredPercentage = getDesiredInvestmentPercentage(equityFund, totalInitialAllocation);
        BigDecimal debtDesiredPercentage = getDesiredInvestmentPercentage(debtFund, totalInitialAllocation);
        BigDecimal goldDesiredPercentage = getDesiredInvestmentPercentage(goldFund, totalInitialAllocation);
        BigDecimal equityCurrentValue = equityFund.getCurrentValue();
        BigDecimal debtCurrentValue = debtFund.getCurrentValue();
        BigDecimal goldCurrentValue = goldFund.getCurrentValue();
        BigDecimal totalCurrentValue = equityCurrentValue.add(debtCurrentValue).add(goldCurrentValue);
        BigDecimal equityUpdatedValue = getValueOnDesiredWeight(equityDesiredPercentage, totalCurrentValue);
        BigDecimal debtUpdatedValue = getValueOnDesiredWeight(debtDesiredPercentage, totalCurrentValue);
        BigDecimal goldUpdatedValue = getValueOnDesiredWeight(goldDesiredPercentage, totalCurrentValue);
        updateFundValues(isIt12thMonth, equityFund, debtFund, goldFund, equityUpdatedValue, debtUpdatedValue, goldUpdatedValue, Month.DECEMBER);
        updateFundValues(isIt6thMonth, equityFund, debtFund, goldFund, equityUpdatedValue, debtUpdatedValue, goldUpdatedValue, Month.JUNE);
    }

    private BigDecimal getValueOnDesiredWeight(BigDecimal equityDesiredPercentage, BigDecimal totalCurrentValue) {
        return getFloorRoundedValue(equityDesiredPercentage.divide(new BigDecimal("100"), DECIMAL_PRECISION, RoundingMode.HALF_UP).multiply(totalCurrentValue));
    }

    private BigDecimal getDesiredInvestmentPercentage(Fund fund, BigDecimal totalInitialAllocation) {
        return getFloorRoundedValue(fund.getInitialAllocation().divide(totalInitialAllocation, DECIMAL_PRECISION, RoundingMode.HALF_UP).multiply(new BigDecimal("100")));
    }

    private BigDecimal getFloorRoundedValue(BigDecimal value) {
        return value.setScale(0, RoundingMode.FLOOR);
    }


    private void updateFundValues(boolean isIt6thOr12thMonth, Fund equityFund, Fund debtFund, Fund goldFund, BigDecimal equityUpdatedValue, BigDecimal debtUpdatedValue, BigDecimal goldUpdatedValue, Month month) {
        if (isIt6thOr12thMonth) {
            equityFund.updateValue(equityUpdatedValue, month);
            debtFund.updateValue(debtUpdatedValue, month);
            goldFund.updateValue(goldUpdatedValue, month);
        }
    }

    private BigDecimal getTotalInitialAllocation(Fund equityFund, Fund debtFund, Fund goldFund) {
        return equityFund.getInitialAllocation().add(debtFund.getInitialAllocation()).add(goldFund.getInitialAllocation());
    }

    private boolean isMultipleOf(int num) {
        return portfolio.getFund(FundType.EQUITY).getMonthlyBalances().size() % num == 0;
    }

    private boolean isFundDataLessThanSixMonths() {
        int REBALANCE_MONTH_LIMIT = 6;
        if (portfolio.getFund(FundType.EQUITY).getMonthlyBalances().size() < REBALANCE_MONTH_LIMIT) {
            return true;
        }
        return false;
    }
}


