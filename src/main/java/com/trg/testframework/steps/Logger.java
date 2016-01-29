package com.trg.testframework.steps;

import java.util.List;

/**
 * Created by Calcey on 1/19/2016.
 */
public class Logger {

    public static void log(String logMessage) {
        System.out.println(logMessage);
    }

    public  static void log(List<String> logMessages) {
        String message = "";
        for(String logMessage : logMessages) {
            message += logMessage;
        }

        log(message);
    }

}
