package com.techelevator.dao;

import com.techelevator.handler.Logger;
import com.techelevator.model.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

public class SalesDaoFile{
    private String filePath;
    private Logger logger;
    public SalesDaoFile(String filePath, Logger logger){
        this.filePath=filePath;
        this.logger=logger;
    }

    // potentially can be inherited
    public Map<String,Integer> load(){
        File inputFile = new File(this.filePath);
        if(!inputFile.exists()){
            throw new RuntimeException("File not exists:" + filePath);
        }

        Map<String,Integer> listOfProducts = new HashMap<>();
        try(Scanner fileInput = new Scanner(inputFile)){
            while(fileInput.hasNextLine()){
                String[] salesInfo= fileInput.nextLine().split("\\|");
                listOfProducts.put(salesInfo[0],Integer.parseInt(salesInfo[1]));
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listOfProducts;

    }
}
