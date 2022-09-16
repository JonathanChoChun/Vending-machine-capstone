package com.techelevator;

import com.techelevator.dao.*;
import com.techelevator.handler.*;
import com.techelevator.model.Product;
import com.techelevator.model.Sales;
import com.techelevator.model.VendingMachine;
import com.techelevator.view.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {



        // create one vending machine class


        // create a product class


        // create a DAO object for getting input file
        LogDao logDao = new LogDaoFile();
        Logger logger = new LogHandler(logDao);
        ProductDaoFile productReader = new ProductDaoFile("vendingmachine.csv",logger);
        List<Product> productList = productReader.load();
//        for (Product item:productList){
//            System.out.println(item.getProductName()+" "+item.getPrice());
//        }
        SalesDaoFile salesReportReader = new SalesDaoFile("salesreportinitial.txt",logger);
        Sales salesReportFromPrevious = new Sales(salesReportReader.load(), productList );
        Sales salesReportFromScratch = new Sales(productList);

//        for (Map.Entry<Product,Integer> sales: salesReport.getSalesTrack().entrySet()){
//            System.out.println(sales.getKey().getProductName()+" "+sales.getValue().toString());
//        }

        VendingMachine machine1 = new VendingMachine(productList,salesReportFromPrevious);
       // VendingMachine machine1 = new VendingMachine(productList,salesReportFromScratch);

//        for (Map.Entry<Product,Integer> item: machine1.getListOfItems().entrySet()){
//            System.out.println(item.getKey().getProductName()+" "+item.getValue().toString());
//        }




        InputStream userInput = System.in;
        PrintStream userOutput = System.out;
        Menu menu = new Menu(userInput, userOutput, true);
        VendingMachineCli vendingMachineCli = new VendingMachineCli(menu,logger,userInput,machine1);
        vendingMachineCli.run();



        // TODO
        // create a sales map that update every time there is a purchase
        // the sales map is initialized through reading a file in DAO or start at zero
        // the report is initialized by the time
        // write sales to a file when select option 4


    }
}
