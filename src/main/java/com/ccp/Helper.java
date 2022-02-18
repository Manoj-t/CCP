/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author Manoj
 */
public class Helper {

    public static String checkInputFile(String[] args)  {
        String inputFile = null;
        // checking for No input argument and seeking from STDIN
        if (args.length == 0) {
            System.err.println("Please Provide Input File");
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            inputFile = myObj.nextLine();
            //inputFile = "input.txt";
        }
        // checking for 0ne input argument and seeking from commandline
        if (args.length == 1) {
            inputFile = args[0];
        }

        //checking if the input file exists
        File f = new File(inputFile);
        if (f.exists()) {
            return inputFile;
        } else {
            System.err.println("No Such File");
            return null;
        }
    }

    public static void readFile(String inputFile) {
        File f = new File(inputFile);
        if (f.exists()) {
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(f));
                String line = reader.readLine();
                while (line != null) {
                    //System.out.println(line);
                    processEachTransaction(line);
                    // read next line
                    line = reader.readLine();
                    //break;
                }
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void processEachTransaction(String line) {
        //split String by space
        String[] stringArray = line.split("\\s");
        //iterate over new Accounts          
        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i].trim().equals("Add")) {
                processNewAccounts(line);
            }
        }

        //iterate over to charge Accounts
        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i].trim().equals("Charge")) {
                processAccountCharge(line);
            }
        }

        //iterate over to crediting Accounts
        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i].trim().equals("Credit")) {
                processAccountCredits(line);
            }
        }
    }

    public static void processNewAccounts(String line) {

        //split String by space
        String[] stringArray = line.split("\\s");
        //iterate over string array  
        for (int i = 0; i < stringArray.length; i++) {

            String ccn = stringArray[2].trim();
            if (luhnValidation(ccn)) {
                //System.out.println("Valid Card");
                String limit = stringArray[3];
                //remove $ from number
                limit = limit.replace("$", "");
                //adding valid cards with new accounts with zero balance to records                
                Record r = new Record(stringArray[1], ccn, Integer.parseInt(limit), Integer.parseInt("0"), "valid");
                MainCCP.records.add(r);
                break;
            } else {
                //System.out.println("Invalid Card");
                Record r = new Record(stringArray[1], ccn, Integer.parseInt("0"), Integer.parseInt("0"), "error");
                MainCCP.records.add(r);
                break;
            }
        }
    }

    public static boolean luhnValidation(String ccn) {
        int totalSum = 0;
        int totDigits = ccn.length();

        boolean isSecond = false;
        for (int i = totDigits - 1; i >= 0; i--) {

            int d = Integer.parseInt("" + ccn.charAt(i)) - 0;

            if (isSecond == true) {
                d = d * 2;
            }

            // Adding double digits
            totalSum += d / 10;
            totalSum += d % 10;

            isSecond = !isSecond;
        }
        return (totalSum % 10 == 0);
    }

    public static void processAccountCharge(String line) {
        //splitt String by space
        String[] stringarray = line.split("\\s");
        String name = stringarray[1].trim();
        String chargeAmount = stringarray[2].trim();
        //remove $ from number
        chargeAmount = chargeAmount.replace("$", "");
        //iterate over transactions log  
        for (int i = 0; i < MainCCP.records.size(); i++) {
            if (MainCCP.records.get(i).getName().equals(name)) {
                int limit = MainCCP.records.get(i).getLimit();
                int currBalance = MainCCP.records.get(i).getBalance();

                // - Charges that would raise the balance over the limit are ignored as if they were declined
                if (currBalance + Integer.parseInt(chargeAmount) > limit) {
                    //decline the transaction
                } else {
                    currBalance = currBalance + Integer.parseInt(chargeAmount);

                    //Charges against Luhn 10 invalid cards are ignored
                    if (MainCCP.records.get(i).getStatus().equals("error")) {
                        //ignore luhn10 invalids
                    } else {
                        //"Charge" will increase the balance of the card associated with the provided name by the amount specified
                        MainCCP.records.get(i).setBalance(currBalance);
                    }
                }
                break;
            }
        }
    }

    public static void processAccountCredits(String line) {
        //splitt String by space
        String[] stringarray = line.split("\\s");
        String name = stringarray[1].trim();
        String creditAmount = stringarray[2].trim();
        //remove $ from number
        creditAmount = creditAmount.replace("$", "");
        //iterate over transactions log  
        for (int i = 0; i < MainCCP.records.size(); i++) {
            if (MainCCP.records.get(i).getName().equals(name)) {
                int limit = MainCCP.records.get(i).getLimit();
                int currBalance = MainCCP.records.get(i).getBalance();

                // "Credit" will decrease the balance of the card associated with the provided name by the amount specified
                //- Credits that would drop the balance below $0 will create a negative balance
                currBalance = currBalance - Integer.parseInt(creditAmount);

                //Charges against Luhn 10 invalid cards are ignored
                if (MainCCP.records.get(i).getStatus().equals("error")) {
                    //ignore luhn10 invalids
                } else {                    
                    MainCCP.records.get(i).setBalance(currBalance);
                }

                break;
            }
        }
    }
}
