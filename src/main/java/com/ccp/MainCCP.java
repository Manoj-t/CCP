/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccp;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Manoj
 */
public class MainCCP {

    // creating transactions Log
    public static ArrayList<Record> records = new ArrayList<>();

    public static void main(String... args) {
        String inputFile;
        inputFile = Helper.checkInputFile(args);
        if (inputFile == null) {
            System.exit(0);
        }
        //read file        
        Helper.readFile(inputFile);

        //print Log
        printAccountDetails();
    }

    public static void printAccountDetails() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < MainCCP.records.size(); i++) {
            list.add(MainCCP.records.get(i).getName());
        }
        Collections.sort(list);
        System.out.println("---------------Accounts Status-----------------------");
        for (int i = 0; i < list.size(); i++) {
            printAccount(list.get(i));
        }
    }

    public static void printLog() {
        System.out.println("---------------Logs-----------------------");
        for (int i = 0; i < MainCCP.records.size(); i++) {
            System.out.println(MainCCP.records.get(i).toString());
        }
    }

    public static void printAccount(String name) {
        for (int i = 0; i < MainCCP.records.size(); i++) {
            if (MainCCP.records.get(i).getName().equals(name)) {
                if (MainCCP.records.get(i).getStatus().equals("error")) {
                    System.out.println(MainCCP.records.get(i).getName() + ": " + MainCCP.records.get(i).getStatus());
                } else {
                    System.out.println(MainCCP.records.get(i).getName() + ": $" + MainCCP.records.get(i).getBalance());
                }
                break;
            }
        }
    }

}
