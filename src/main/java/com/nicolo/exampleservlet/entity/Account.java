/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nicolo.exampleservlet.entity;

/**
 *
 * @author nicolo
 */
public class Account {
    
    private Long accountId;
    private float balance;
    private Long clientId;
    
    public Account(Long accountId, float balance, Long clientId){
        this.accountId = accountId;
        this.balance = balance;
        this.clientId = clientId;
    }
    
    
    public Long getAccountId(){
        return this.accountId;
    }
    
    public void setAccountId(Long accountId){
        this.accountId = accountId;
    }
    
    public float getBalance(){
        return this.balance;
    }
    
    public void setBalance(float balance){
        this.balance = balance;
    }
    
    public Long getClientId(){
        return this.clientId;
    }
    
    public void setClientId(Long clientId){
        this.clientId = clientId;
    }
}
