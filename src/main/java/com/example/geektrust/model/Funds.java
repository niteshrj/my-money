package com.example.geektrust.model;

import java.util.ArrayList;
import java.util.List;

public class Funds {
    private final List<Fund> funds = new ArrayList<>();

    public void add(Fund fund) {
        funds.add(fund);
    }

    public Fund getFund(FundType fundType) {
        return funds.stream().filter(fund -> fund.getFundType() == fundType).findFirst().orElse(null);
    }
}
