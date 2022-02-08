package com.app.runnables;

import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingProcessor implements Callable<String> {
    int counter=0;
    @Override
    public String call() throws Exception {
        counter++;
        Logger.getLogger(LoggingProcessor.class.getName()).log(Level.INFO,"logging the process task "+counter);
        return "logging the process task "+Thread.currentThread().getId();
    }
}
