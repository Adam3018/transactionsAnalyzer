package com.opencsv.csvopen;

import com.opencsv.bean.CsvBindByName;

public class Transaction {

    @CsvBindByName
    private int amount;
    @CsvBindByName
    private String category;
    public Transaction(int amount, String category) {
        this.amount = amount;
        this.category = category;
    }
    
}
