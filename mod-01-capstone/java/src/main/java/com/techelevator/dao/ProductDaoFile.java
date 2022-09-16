package com.techelevator.dao;

import com.techelevator.handler.Logger;
import com.techelevator.model.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDaoFile {

    private String filePath;
    private Logger logger;
    public ProductDaoFile(String filePath, Logger logger){
        this.filePath=filePath;
        this.logger=logger;
    }

    public List<Product> load(){
        File inputFile = new File(this.filePath);
        if(!inputFile.exists()){
            throw new RuntimeException("File not exists:" + filePath);
        }

        List<Product> listOfProducts = new ArrayList<>();
        try(Scanner fileInput = new Scanner(inputFile)){
            while(fileInput.hasNextLine()){
                String[] productInfo= fileInput.nextLine().split("\\|");
                Product product = new Product(productInfo[0],productInfo[1],new BigDecimal(productInfo[2]),productInfo[3]);
                listOfProducts.add(product);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listOfProducts;

    }

}
