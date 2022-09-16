package com.techelevator.view;

import com.techelevator.handler.Logger;
import com.techelevator.model.Product;
import com.techelevator.model.VendingMachine;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.*;

public class VendingMachineCli {

    private Menu menu;
    private Logger logger;
    private VendingMachine vendingMachine;
    private Scanner in;

    public VendingMachineCli(Menu menu, Logger logger, InputStream input, VendingMachine vendingMachine) {
        this.menu = menu;
        this.logger = logger;
        this.vendingMachine = vendingMachine;
        this.in = new Scanner(input);
    }

    public void run() {

        // Display Vending Machine Items
        // Purchase
        // Exit

        boolean isLeaving = false;
        while (!isLeaving) {
            String choice = (String) menu.getChoiceFromOptions(new String[]{
                    "Display Vending Machine Items",
                    "Purchase",
                    "Exit",
                    "(hide)Produce sales report"}, "Current Money Provided: $" + this.vendingMachine.getBalance());

            switch (choice) {
                case "Display Vending Machine Items":
                    displayProducts(false);
                    break;
                case "Purchase":
                    boolean isLeavingSub = false;
                    while (!isLeavingSub) {
                        choice = (String) menu.getChoiceFromOptions(new String[]{
                                "Insert Money",
                                "Select Product",
                                "Finish Transaction"
                        }, "Current Money Provided: $" + this.vendingMachine.getBalance());
                        //System.out.println("Current Money Provided: $" + this.vendingMachine.getBalance());
                        switch (choice) {
                            case "Insert Money":
                                boolean isLeavingFeedingMoney = false;
                                while (!isLeavingFeedingMoney) {
                                    choice = (String) menu.getChoiceFromOptions(new String[]{
                                            "$1",
                                            "$2",
                                            "$5",
                                            "$10",
                                            "$20",
                                            "$50",
                                            "Exit"
                                    }, "Current Money Provided: $" + this.vendingMachine.getBalance());
                                    switch (choice) {
                                        case "$1":
                                            this.vendingMachine.setBalance(this.vendingMachine.getBalance().add(BigDecimal.valueOf(1)));
                                            this.logger.writeAudit("INSERT MONEY: "+" $1.00 "+" $"+this.vendingMachine.getBalance().setScale(2));
                                            break;
                                        case "$2":
                                            this.vendingMachine.setBalance(this.vendingMachine.getBalance().add(BigDecimal.valueOf(2)));
                                            this.logger.writeAudit("INSERT MONEY: "+" $2.00 "+" $"+this.vendingMachine.getBalance().setScale(2));
                                            break;
                                        case "$5":
                                            this.vendingMachine.setBalance(this.vendingMachine.getBalance().add(BigDecimal.valueOf(5)));
                                            this.logger.writeAudit("INSERT MONEY: "+" $5.00 "+" $"+this.vendingMachine.getBalance().setScale(2));
                                            break;
                                        case "$10":
                                            this.vendingMachine.setBalance(this.vendingMachine.getBalance().add(BigDecimal.valueOf(10)));
                                            this.logger.writeAudit("INSERT MONEY: "+" $10.00 "+" $"+this.vendingMachine.getBalance().setScale(2));
                                            break;
                                        case "$20":
                                            this.vendingMachine.setBalance(this.vendingMachine.getBalance().add(BigDecimal.valueOf(20)));
                                            this.logger.writeAudit("INSERT MONEY: "+" $20.00 "+" $"+this.vendingMachine.getBalance().setScale(2));
                                            break;
                                        case "$50":
                                            this.vendingMachine.setBalance(this.vendingMachine.getBalance().add(BigDecimal.valueOf(50)));
                                            this.logger.writeAudit("INSERT MONEY: "+" $50.00 "+" $"+this.vendingMachine.getBalance().setScale(2));
                                            break;
                                        case "Exit":
                                            isLeavingFeedingMoney = true;
                                            break;
                                    }
                                    //System.out.println("Current Money Provided: $" + this.vendingMachine.getBalance());
                                }

                                break;
                            case "Select Product":
                                displayProducts(true);
                                boolean isLeavingSelectingProduct=false;
                                while (!isLeavingSelectingProduct){
                                    isLeavingSelectingProduct=selectProducts();
                                }
                                break;
                            case "Finish Transaction":
                                finishTransaction();
                                isLeavingSub = true;
                                break;
                        }
                    }
                    break;
                case "Exit":
                    isLeaving = true;
                    break;
                case "Produce sales report":


                    // salesReportLatest.txt
                    File destinationFile = new File("salesReportLatest.txt");
                    try {

                        // True, if file does not exist, false if it does, and it should overwrite
                        if(!destinationFile.createNewFile()){
                            System.out.println("file overwritten");
                        }
                    } catch (IOException e) {
                        System.err.println("Cannot create output file");
                        System.exit(2);
                    }
                    try (PrintWriter dataOutput= new PrintWriter(destinationFile)){

                        for (Map.Entry<Product,Integer>Sales :this.vendingMachine.getSalesReport().getSalesTrack().entrySet()){
                            dataOutput.println(Sales.getKey().getProductName()+"|"+Sales.getValue());
                        }

                    } catch (FileNotFoundException e) {
                        System.err.println("file not found");
                        System.exit(2);
                    }


                    isLeaving = true;
                    break;



            }
        }
    }

    private void displayProducts(boolean displayAvailableOnly) {
        System.out.printf("%-4s | %-19s %-6s %-6s %-2s %n", "Slot", "Item", "Price", "Type", "Quantity");
        for (Product item: this.vendingMachine.getListOfItems()){
            String formattedName = item.getProductName();
            while (formattedName.length() <= 19) {
                formattedName += " ";
            }
            String formattedType = item.getType();
            while (formattedType.length() <= 5) {
                formattedType += " ";
            }
            if(displayAvailableOnly){
                if(item.getQuantity()!=0){
                    printLineImproved(item.getSlotLocation(), item.getProductName(), item.getPrice().toString(), item.getType(), item.getQuantity());
//                    System.out.println(item.getSlotLocation()+" | "
//                    + formattedName
//                    + "$" + item.getPrice()+" "
//                    + formattedType
//                    + item.getQuantity());
                }
            } else {
                if(item.getQuantity()==0){
                    System.out.println(item.getProductName()+" SOLDOUT");
                }else{
                    printLineImproved(item.getSlotLocation(), item.getProductName(), item.getPrice().toString(), item.getType(), item.getQuantity());
//                    System.out.println(item.getSlotLocation()+" | "
//                            + formattedName
//                            + "$" + item.getPrice()+" "
//                            + formattedType
//                            + item.getQuantity());
                }
            }
        }
    }

    private boolean selectProducts(){
        BigDecimal moneyAvailable=this.vendingMachine.getBalance();
        System.out.print("Please enter the product code for purchase: ");
        String inputProductCode=this.in.nextLine();
        for (Product item: this.vendingMachine.getListOfItems()){
            if (item.getSlotLocation().equalsIgnoreCase(inputProductCode)&& item.getQuantity()==0){
                System.err.println("Unfortunately, this product is sold out.");
                return true;
            }
            if (item.getSlotLocation().equalsIgnoreCase(inputProductCode)&& item.getPrice().compareTo(moneyAvailable)>0){
                System.err.println("Insufficient balance. Please insert money to continue purchase");
                return true;
            }
            if(item.getSlotLocation().equalsIgnoreCase(inputProductCode)){
                BigDecimal balanceBeforePurchase = this.vendingMachine.getBalance();
                this.vendingMachine.setBalance(this.vendingMachine.getBalance().subtract(item.getPrice()));
                item.subtractQuantityByOne();
                System.out.println("Product name: "+item.getProductName()+" Price: "+item.getPrice());
                System.out.println("Balance remaining: "+this.vendingMachine.getBalance());
                this.logger.writeAudit(item.getProductName()+" "+item.getSlotLocation()+" $"+balanceBeforePurchase.setScale(2)+" $"+this.vendingMachine.getBalance().setScale(2));

                // SalesReport
                this.vendingMachine.getSalesReport().getSalesTrack().put(item,this.vendingMachine.getSalesReport().getSalesTrack().get(item)+1);

                switch(item.getType()){
                    case "Chip":
                        System.out.println("Crunch Crunch, Yum!");
                        break;
                    case "Candy":
                        System.out.println("Munch Munch, Yum!");
                        break;
                    case "Drink":
                        System.out.println("Glug Glug, Yum!");
                        break;
                    case "Gum":
                        System.out.println("Chew Chew, Yum!");
                        break;
                }
                return true;
            }

        }
        System.err.println("Product does not exist.");
        return true;
    }


    private void finishTransaction(){
        BigDecimal nickel = BigDecimal.valueOf(0.05);
        BigDecimal dime = BigDecimal.valueOf(0.1);
        BigDecimal quarter = BigDecimal.valueOf(0.25);

        BigDecimal balanceBefore = this.vendingMachine.getBalance();

        int amountOfQuarter=this.vendingMachine.getBalance().divide(quarter, RoundingMode.FLOOR).intValue();
        this.vendingMachine.setBalance(this.vendingMachine.getBalance().subtract(quarter.multiply(BigDecimal.valueOf(amountOfQuarter))));

        int amountOfDime=this.vendingMachine.getBalance().divide(dime, RoundingMode.FLOOR).intValue();
        this.vendingMachine.setBalance(this.vendingMachine.getBalance().subtract(dime.multiply(BigDecimal.valueOf(amountOfDime))));

        int amountOfNickel=this.vendingMachine.getBalance().divide(nickel, RoundingMode.FLOOR).intValue();
        this.vendingMachine.setBalance(this.vendingMachine.getBalance().subtract(nickel.multiply(BigDecimal.valueOf(amountOfNickel))));

        this.vendingMachine.setBalance(BigDecimal.valueOf(0));
        this.logger.writeAudit("GIVE CHANGE: $"+balanceBefore.setScale(2)+" $"+this.vendingMachine.getBalance().setScale(2));
        System.out.println("Returning "+amountOfQuarter+" Quarters,"+amountOfDime+" Dime,"+amountOfNickel+" Nickels");
    }


    public void printLineImproved (String slot, String item, String price, String type, int quantity) {
        System.out.printf("%-4s | %-19s $%-5s %-6s %-2s %n", slot, item, price, type, quantity);
    }

}
