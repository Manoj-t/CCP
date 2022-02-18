/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccp;

/**
 *
 * @author Manoj
 */
public class Record {
    
    public String name;
    public String ccn;
    public int limit; 
    public int balance;
    public String status;

    public Record(String name, String ccn, int limit, int balance, String status) {
        this.name = name;
        this.ccn = ccn;
        this.limit = limit;
        this.balance = balance;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCcn() {
        return ccn;
    }

    public void setCcn(String ccn) {
        this.ccn = ccn;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Record{" + "name=" + name + ", ccn=" + ccn + ", limit=" + limit + ", balance=" + balance + ", status=" + status + '}';
    }

    
}
