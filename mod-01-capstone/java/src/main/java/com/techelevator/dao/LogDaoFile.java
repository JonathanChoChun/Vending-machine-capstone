package com.techelevator.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LogDaoFile implements LogDao {
    private static final String ERROR_FILE = "logError.txt";
    private static final String AUDIT_FILE = "Log.txt";
    private static final SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss aa");

    @Override
    public void writeAudit(String errorMessage) {

        writeToFile(errorMessage,AUDIT_FILE,"Audit");
    }

    @Override
    public void writeError(String errorMessage) {
        writeToFile(errorMessage,ERROR_FILE,"Error");
    }


    private void writeToFile(String message, String fileName, String logType) {
        File file = new File(fileName);
        try (PrintStream output = new PrintStream(new FileOutputStream(file, true)) ) {
            Date date = new Date(System.currentTimeMillis());
            output.append(MessageFormat.format("{0} {1}\n",formatter.format(date),message));
            output.flush();
            //System.out.println(logType + ":" + message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
