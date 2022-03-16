package com.opencsv.csvopen;


public class CsvOriginal{
    
    private String date;
    private String amount;
    private String category;

    
    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }


    public String getAmount() {
        return amount;
    }


    public void setAmount(String amount) {
        this.amount = amount;
    }


    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }


    public CsvOriginal(String date, String amount, String category) {
        this.date = date;
        this.amount = amount;
        this.category = category;
    }

    
    
    
}