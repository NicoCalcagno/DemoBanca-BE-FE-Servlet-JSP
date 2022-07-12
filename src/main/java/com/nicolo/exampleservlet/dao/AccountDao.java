/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nicolo.exampleservlet.dao;

import com.nicolo.exampleservlet.dto.AccountDto;
import com.nicolo.exampleservlet.entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author nicolo
 */
public class AccountDao {
    
    
    private static final String ADD_ACCOUNT_QUERY = "INSERT INTO account VALUES(?,?,?)";
    private static final String ALL_ACCOUNT_QUERY = "SELECT * FROM account";
    private static final String UPDATE_ACCOUNT_QUERY = "UPDATE account SET client = ?, balance = ? WHERE account_id = ?";
    private static final String DELETE_ACCOUNT_QUERY = "DELETE FROM account WHERE account_id = ?";
    private static final String GET_ACCOUNT_BY_ACCOUNT_ID_QUERY = "SELECT * FROM account WHERE account_id = ?";
    
    public static List<Account> retrieveAllAccount(){
        List<Account> accountList = new ArrayList<>();
        try{
            Connection con = DataBaseConnection.initializeDatabase();
            PreparedStatement st = con.prepareStatement((ALL_ACCOUNT_QUERY));

            ResultSet resultSet = st.executeQuery();
            while(resultSet.next()){
                Account account = new Account(resultSet.getLong("account_id"),
                                            resultSet.getFloat("balance"),
                                            resultSet.getLong("client"));
                accountList.add(account);
                
            }
            
            resultSet.close();
            st.close();
            con.close();
        }catch (Exception e){
            System.out.println("error: "+ e.getMessage());
        }
        return accountList;
    }
    
    public static int addAccount(AccountDto accountDto){
        int result = 0;
        try{
            Connection con = DataBaseConnection.initializeDatabase();
            PreparedStatement st = con.prepareStatement(ADD_ACCOUNT_QUERY);
            st.setLong(1, new Random().nextLong());
            st.setFloat(2, accountDto.getBalance());
            st.setLong(3, accountDto.getClientId());
            
            result = st.executeUpdate();
            st.close();
            con.close();
        }catch(Exception e){
            System.out.println("error: "+e.getMessage());
        }
        return result;
    }
    
    public static int removeAccount(Long accountId){
        int result = 0;
        try{
            Connection con = DataBaseConnection.initializeDatabase();
            PreparedStatement st = con.prepareStatement(DELETE_ACCOUNT_QUERY);
            
            st.setLong(1, accountId);
            
            result = st.executeUpdate();
            st.close();
            con.close();
                    
        }catch(Exception e){
            System.out.println("error: "+e.getMessage());
        }
        
        return result;
    }
    
    public static Account getAccountByAccountId(Long accountId){
        Account accountFound = null;
        try{
            Connection con = DataBaseConnection.initializeDatabase();
            PreparedStatement st = con.prepareStatement(GET_ACCOUNT_BY_ACCOUNT_ID_QUERY);
            st.setLong(1, accountId);
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            accountFound = new Account(resultSet.getLong("account_id"),
                                        resultSet.getFloat("balance"),
                                        resultSet.getLong("client"));
            
            resultSet.close();
            st.close();
            con.close();
        }catch(Exception e){
            System.out.println("error: "+e.getMessage());
        }
        
        return accountFound;
    }
    
    public static int updateAccount(Account accountToUpdate){
        int result = 0;
        try{
            Connection con = DataBaseConnection.initializeDatabase();
            PreparedStatement st = con.prepareStatement(UPDATE_ACCOUNT_QUERY);
            st.setLong(1, accountToUpdate.getClientId());
            st.setFloat(2, accountToUpdate.getBalance());
            st.setLong(3, accountToUpdate.getAccountId());
            
            result = st.executeUpdate();
            st.close();
            con.close();
        }catch(Exception e){
            System.out.println("error: "+ e.getMessage());
        }
        
        return result;
    }
}
