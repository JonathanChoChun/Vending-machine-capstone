package com.techelevator.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sales {

    private Map<Product,Integer> salesTrack;

    // constructor takes in product list and the sales report and generate salesTrack

    public Map<Product, Integer> getSalesTrack() {
        return salesTrack;
    }

    public void setSalesTrack(Map<Product, Integer> salesTrack) {
        this.salesTrack = salesTrack;
    }

    public Sales(List<Product>productList){
        salesTrack = new HashMap<>();
        for (Product product :productList){
                salesTrack.put(product, 0);


        }
    }

    public Sales(Map<String, Integer>previousSalesReport, List<Product>productList){

        // store previousSalesReport as product items in SalesTrack
        salesTrack = new HashMap<>();
        for (Map.Entry<String, Integer>item: previousSalesReport.entrySet()){
            for (Product product :productList){
                if (product.getProductName().equalsIgnoreCase(item.getKey())){
                    salesTrack.put(product, item.getValue());
                    break;
                }
            }
        }

    }
}
