package com.example.geektrust.model;

public class Investor {
    private String name;
    private Portfolio portfolio;

    public Investor(String name, Portfolio portfolio) {
        this.name = name;
        this.portfolio = portfolio;
    }

    public void buyFund(Fund fund) {
        this.portfolio.addFund(fund);
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }
}
