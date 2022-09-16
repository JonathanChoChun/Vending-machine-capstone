package com.techelevator.model;

import java.math.BigDecimal;

public class Product {


    private String slotLocation;
    private String productName;
    private BigDecimal price;
    private String type;
    private int quantity;

    public Product(String slotLocation, String productName, BigDecimal price, String type) {
        this.slotLocation = slotLocation;
        this.productName = productName;
        this.price = price;
        this.type = type;
        this.quantity= 5;

    }

    public String getSlotLocation() {
        return slotLocation;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSlotLocation(String slotLocation) {
        this.slotLocation = slotLocation;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void subtractQuantityByOne(){
        if(this.quantity>0){
            this.quantity-=1;
        }

    }
}
