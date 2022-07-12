/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nicolo.exampleservlet.dto;

/**
 *
 * @author nicolo
 */
public class AccountDto {

    private float balance;
    private Long clientId;
    
    public AccountDto(float balance, Long clientId){
        this.balance = balance;
        this.clientId = clientId;
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
