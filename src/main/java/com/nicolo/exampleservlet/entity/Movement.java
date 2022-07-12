/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nicolo.exampleservlet.entity;

import java.util.Date;

/**
 *
 * @author nicolo
 */
public class Movement {
    
    private Long movementId;
    private float balance;
    private float amount;
    private Date dataMovement;
    private String type;
    private Long accountId;
    
    
    public Movement(Long movementId, float balance, float amount, Date dataMovement, String type, Long accountId){
        this.movementId = movementId;
        this.balance = balance;
        this.amount = amount;
        this.type = type;
        this.accountId = accountId;
        this.dataMovement = dataMovement;
    }
    
    public Long getMovementId(){
        return this.movementId;
    }
    
    public void setMovementId(Long movementId){
        this.movementId = movementId;
    }
    
    public float getBalance(){
        return this.balance;
    }
    
    public void setBalance(float balance){
        this.balance = balance;
    }
    
    public float getAmount(){
        return this.amount;
    }
    
    public void setAmount(float amount){
        this.amount = amount;
    }
    
    public Date getDataMovement(){
        return this.dataMovement;
    }
    
    public void setDataMovement(Date dataMovement){
        this.dataMovement = dataMovement;
    }
    
    public String getType(){
        return this.type;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public Long getAccountId(){
        return this.accountId;
    }
    
    public void setAccountId(Long accountId){
        this.accountId = accountId;
    }
    
    
}
