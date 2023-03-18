package com.example.geektrust.command;

import com.example.geektrust.model.Fund;
import com.example.geektrust.model.FundType;
import com.example.geektrust.model.Portfolio;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class TestUtils {

    public static Portfolio getPortfolioWith6MonthsFundMockData() {
        Portfolio portfolio = new Portfolio();
        List<Fund> fundsWith6MonthsData = getFundsWith6MonthsData();
        Fund equityFund = fundsWith6MonthsData.get(0);
        Fund debtFund = fundsWith6MonthsData.get(1);
        Fund goldFund = fundsWith6MonthsData.get(2);
        portfolio.addFund(equityFund);
        portfolio.addFund(debtFund);
        portfolio.addFund(goldFund);
        return portfolio;
    }

    public static Portfolio getPortfolioWith12MonthsFundMockData() {
        Portfolio portfolio = new Portfolio();
        List<Fund> fundsWith6MonthsData = getFundsWith12MonthsData();
        Fund equityFund = fundsWith6MonthsData.get(0);
        Fund debtFund = fundsWith6MonthsData.get(1);
        Fund goldFund = fundsWith6MonthsData.get(2);
        portfolio.addFund(equityFund);
        portfolio.addFund(debtFund);
        portfolio.addFund(goldFund);
        return portfolio;
    }

    private static List<Fund> getFundsWith6MonthsData() {
        Fund equityFund = new Fund(FundType.EQUITY, new BigDecimal("6000"), Month.JANUARY);
        Fund debtFund = new Fund(FundType.DEBT, new BigDecimal("3000"), Month.JANUARY);
        Fund goldFund = new Fund(FundType.GOLD, new BigDecimal("1000"), Month.JANUARY);
        equityFund.updateValue(new BigDecimal("7416"), Month.FEBRUARY);
        debtFund.updateValue(new BigDecimal("6020"), Month.FEBRUARY);
        goldFund.updateValue(new BigDecimal("1520"), Month.FEBRUARY);
        equityFund.updateValue(new BigDecimal("10593"), Month.MARCH);
        debtFund.updateValue(new BigDecimal("7897"), Month.MARCH);
        goldFund.updateValue(new BigDecimal("2272"), Month.MARCH);
        equityFund.updateValue(new BigDecimal("13600"), Month.APRIL);
        debtFund.updateValue(new BigDecimal("8630"), Month.APRIL);
        goldFund.updateValue(new BigDecimal("2966"), Month.APRIL);
        equityFund.updateValue(new BigDecimal("17628"), Month.MAY);
        debtFund.updateValue(new BigDecimal("11652"), Month.MAY);
        goldFund.updateValue(new BigDecimal("3829"), Month.MAY);
        equityFund.updateValue(new BigDecimal("19628"), Month.JUNE);
        debtFund.updateValue(new BigDecimal("12652"), Month.JUNE);
        goldFund.updateValue(new BigDecimal("4329"), Month.JUNE);
        return Arrays.asList(equityFund, debtFund, goldFund);
    }

    private static List<Fund> getFundsWith12MonthsData() {
        List<Fund> fundsWith6MonthsData = getFundsWith6MonthsData();
        Fund equityFund = fundsWith6MonthsData.get(0);
        Fund debtFund = fundsWith6MonthsData.get(1);
        Fund goldFund = fundsWith6MonthsData.get(2);
        equityFund.updateValue(new BigDecimal("7416"), Month.JULY);
        debtFund.updateValue(new BigDecimal("6020"), Month.JULY);
        goldFund.updateValue(new BigDecimal("1520"), Month.JULY);
        equityFund.updateValue(new BigDecimal("7416"), Month.AUGUST);
        debtFund.updateValue(new BigDecimal("6020"), Month.AUGUST);
        goldFund.updateValue(new BigDecimal("1520"), Month.AUGUST);
        equityFund.updateValue(new BigDecimal("10593"), Month.SEPTEMBER);
        debtFund.updateValue(new BigDecimal("7897"), Month.SEPTEMBER);
        goldFund.updateValue(new BigDecimal("2272"), Month.SEPTEMBER);
        equityFund.updateValue(new BigDecimal("13600"), Month.OCTOBER);
        debtFund.updateValue(new BigDecimal("8630"), Month.OCTOBER);
        goldFund.updateValue(new BigDecimal("2966"), Month.OCTOBER);
        equityFund.updateValue(new BigDecimal("17628"), Month.NOVEMBER);
        debtFund.updateValue(new BigDecimal("11652"), Month.NOVEMBER);
        goldFund.updateValue(new BigDecimal("3829"), Month.NOVEMBER);
        equityFund.updateValue(new BigDecimal("21590"), Month.DECEMBER);
        debtFund.updateValue(new BigDecimal("13664"), Month.DECEMBER);
        goldFund.updateValue(new BigDecimal("4112"), Month.DECEMBER);
        return Arrays.asList(equityFund, debtFund, goldFund);
    }
}
