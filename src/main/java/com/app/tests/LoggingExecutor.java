package com.app.tests;

import com.app.runnables.LoggingProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class LoggingExecutor {
    private static List<Future<String>> futures;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService service = Executors.newCachedThreadPool();

        List<LoggingProcessor> processor = new ArrayList<>();

        processor.add(new LoggingProcessor());
        processor.add(new LoggingProcessor());
        processor.add(new LoggingProcessor());
        processor.add(new LoggingProcessor());
        processor.add(new LoggingProcessor());
        processor.add(new LoggingProcessor());

        List<Future<String>> futures = service.invokeAll(processor);

        for(Future<String> future : futures){
            System.out.println(future.get());
        }

        service.shutdown();

        System.out.println(service.awaitTermination(30, TimeUnit.SECONDS));
    }

}
