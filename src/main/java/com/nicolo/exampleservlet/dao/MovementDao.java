/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nicolo.exampleservlet.dao;

import com.nicolo.exampleservlet.dto.MovementDto;
import com.nicolo.exampleservlet.entity.Account;
import com.nicolo.exampleservlet.entity.Movement;
import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author nicolo
 */
public class MovementDao {
    
    
    private static final String ADD_MOVEMENT_QUERY = "INSERT INTO movement VALUES(?,?,?,?,?,?)";
    private static final String ALL_MOVEMENT_QUERY = "SELECT * FROM movement"; 
    private static final String DELETE_MOVEMENT_QUERY = "DELETE FROM movement WHERE movement_id = ?";
    
    public static List<Movement> retrieveAllMovement(){
        List<Movement> movementList = new ArrayList<>();
        try{
            Connection con = DataBaseConnection.initializeDatabase();
            PreparedStatement st = con.prepareStatement((ALL_MOVEMENT_QUERY));

            ResultSet resultSet = st.executeQuery();
            while(resultSet.next()){
                Movement movement = new Movement(resultSet.getLong("movement_id"),
                                            resultSet.getFloat("balance"),
                                            resultSet.getFloat("amount"),
                                            resultSet.getDate("data_movement"),
                                            resultSet.getString("type"),
                                            resultSet.getLong("account"));
                movementList.add(movement);
                
            }
            
            resultSet.close();
            st.close();
            con.close();
        }catch (Exception e){
            System.out.println("error: "+ e.getMessage());
        }
        return movementList;
    }
    
    public static int addMovement(MovementDto movementDto){
        int result = 0;
        try{
            Connection con = DataBaseConnection.initializeDatabase();
            PreparedStatement st = con.prepareStatement(ADD_MOVEMENT_QUERY);
            Account accountToUpdate = AccountDao.getAccountByAccountId(movementDto.getAccountId());
            
            st.setLong(1, new Random().nextLong());
            st.setFloat(2, movementDto.getAmount());
            st.setFloat(3, accountToUpdate.getBalance());
            st.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));
            st.setString(5, movementDto.getType());
            st.setLong(6, movementDto.getAccountId());
            
            if(movementDto.getType().equals("Prelievo") && accountToUpdate.getBalance() >= (accountToUpdate.getBalance()-movementDto.getAmount())){
                accountToUpdate.setBalance(accountToUpdate.getBalance()-movementDto.getAmount());
            }else if(movementDto.getType().equals("Deposito")){
                accountToUpdate.setBalance(accountToUpdate.getBalance()+movementDto.getAmount());
            }
            
            AccountDao.updateAccount(accountToUpdate);
            
            
            result = st.executeUpdate();
            st.close();
            con.close();
        }catch(Exception e){
            System.out.println("error: "+e.getMessage());
        }
        return result;
    }
    
    public static int removeMovement(Long movementId){
        int result = 0;
        try{
            Connection con = DataBaseConnection.initializeDatabase();
            PreparedStatement st = con.prepareStatement(DELETE_MOVEMENT_QUERY);
            
            st.setLong(1, movementId);
            
            result = st.executeUpdate();
            st.close();
            con.close();
                    
        }catch(Exception e){
            System.out.println("error: "+e.getMessage());
        }
        
        return result;
    }
   
}
