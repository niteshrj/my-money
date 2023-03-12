package com.example.geektrust.model;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {
    private List<Fund> funds = new ArrayList<>();

    public void addFund(Fund fund) {
        funds.add(fund);
    }

    public List<Fund> getAllFunds() {
        return this.funds;
    }
}
