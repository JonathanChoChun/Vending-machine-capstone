package com.techelevator.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {

    // vending machine stores a list of items and their count
    private List<Product> listOfItems;
    private BigDecimal balance;
    private Sales salesReport;

//    public VendingMachine(List<Product> productList){
//        this.listOfItems=storeProductListAsMap(productList);
//        this.balance=BigDecimal.valueOf(0);
//
//    }

    public VendingMachine(List<Product> productList,Sales salesReport){
        this.listOfItems=productList;
        this.balance=BigDecimal.valueOf(0);
        this.salesReport=salesReport;

    }

//    private Map<Product,Integer> storeProductListAsMap(List<Product> productList){
//
//        if (this.listOfItems==null){
//            this.listOfItems=new HashMap<>();
//        }
//        for (Product product:productList){
//            this.listOfItems.put(product,5);
//        }
//        return this.listOfItems;
//    }


    public List<Product> getListOfItems() {
        return listOfItems;
    }

    public void setListOfItems(List<Product> listOfItems) {
        this.listOfItems = listOfItems;
    }

    public Sales getSalesReport() {
        return salesReport;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


}
