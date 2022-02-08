package com.app.tests;

import com.app.dao.UserDao;
import com.app.runnables.UserProcessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//"C:\\Users\\rdubey\\OneDrive - MODEL N, INC\\Desktop\\ConcurrencyProcessing\\src\\main\\java\\com\\app\\sample.txt"
public class TestExecutors {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<String> users = getUserFromFile("C:\\Users\\rdubey\\OneDrive - MODEL N, INC\\Desktop\\ConcurrencyProcessing\\src\\main\\java\\com\\app\\sample.txt");
        UserDao dao = new UserDao();

        for (String user : users) {
            Future<Integer> future = executor.submit(new UserProcessor(user, dao));
            System.out.println(future.get());
        }
        executor.shutdown();
        System.out.println("main method completed");
    }

    public static List<String> getUserFromFile(String FileName) {
        List<String> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(FileName)))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                users.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}

