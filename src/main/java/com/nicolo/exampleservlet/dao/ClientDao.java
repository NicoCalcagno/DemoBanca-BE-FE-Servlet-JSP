package com.nicolo.exampleservlet.dao;

import com.nicolo.exampleservlet.dto.ClientDto;
import com.nicolo.exampleservlet.entity.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClientDao {


    private static final String ADD_CLIENT_QUERY = "INSERT INTO client VALUES(?,?,?,?,?,?)";
    private static final String ALL_CLIENT_QUERY = "SELECT * FROM client";
    private static final String UPDATE_CLIENT_QUERY = "UPDATE client SET name = ?, surname = ?, email = ?, tel = ?, image_url = ? WHERE client_id = ?";
    private static final String DELETE_CLIENT_QUERY = "DELETE FROM client WHERE client_id = ?";
    private static final String GET_CLIENT_BY_CLIENT_ID_QUERY = "SELECT * FROM client WHERE client_id = ?";
    
    public static List<Client> retrieveAllClient(){
        List<Client> clientList = new ArrayList<>();

        try{
            Connection con = DataBaseConnection.initializeDatabase();
            PreparedStatement st = con.prepareStatement((ALL_CLIENT_QUERY));

            ResultSet resultSet = st.executeQuery();
            while(resultSet.next()){
                Client client = new Client(resultSet.getLong("client_id"),
                                            resultSet.getString("name"),
                                            resultSet.getString("surname"),
                                            resultSet.getString("email"),
                                            resultSet.getString("tel"),
                                            resultSet.getString("image_url"));
                clientList.add(client);
                
            }
            resultSet.close();
            st.close();
            con.close();
        }catch (Exception e){
            System.out.println("error: "+ e.getMessage());
        }
        return clientList;
    }
    
    
    public static int addClient(ClientDto clientDto){
        int result = 0;
        try{
            Connection con = DataBaseConnection.initializeDatabase();
            PreparedStatement st = con.prepareStatement(ADD_CLIENT_QUERY);
            st.setLong(1, new Random().nextLong());
            st.setString(2, clientDto.getEmail());
            st.setString(3, clientDto.getImageUrl());
            st.setString(4, clientDto.getName());
            st.setString(5, clientDto.getSurname());
            st.setString(6, clientDto.getTel());
            

            result = st.executeUpdate();
            st.close();
            con.close();

        }catch (Exception e){
            System.out.println("error: "+ e.getMessage());
        }
        return result;
    }
    
    public static int deleteClient(Long clientId){
        int result = 0;
        try{
            Connection con = DataBaseConnection.initializeDatabase();
            PreparedStatement st = con.prepareStatement(DELETE_CLIENT_QUERY);
            
            st.setLong(1, clientId);
            
            result = st.executeUpdate();
            
            st.close();
            con.close();
        }catch(Exception e){
            System.out.println("error: "+ e.getMessage());
        }
        return result;
    }
    
    public static Client getClientByClientId(Long clientId){
        Client clientFound = null;
        try{
            Connection con = DataBaseConnection.initializeDatabase();
            PreparedStatement st = con.prepareStatement(GET_CLIENT_BY_CLIENT_ID_QUERY);
            System.out.println(clientId);
            st.setLong(1, clientId);
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            clientFound = new Client(resultSet.getLong("client_id"),
                                            resultSet.getString("name"),
                                            resultSet.getString("surname"),
                                            resultSet.getString("email"),
                                            resultSet.getString("tel"),
                                            resultSet.getString("image_url"));
            resultSet.close();
            st.close();
            con.close();
        }catch(Exception e ){
            System.out.println("Error: "+ e.getMessage());
        }
        return clientFound;
    }
    
    public static int updateClient(Client clientToUpdate){
        int result = 0;
        try{
            Connection con = DataBaseConnection.initializeDatabase();
            PreparedStatement st = con.prepareStatement(UPDATE_CLIENT_QUERY);
            
            st.setString(1, clientToUpdate.getName());
            st.setString(2, clientToUpdate.getSurname());
            st.setString(3, clientToUpdate.getEmail());
            st.setString(4, clientToUpdate.getTel());
            st.setString(5, clientToUpdate.getImageUrl());
            st.setLong(6, clientToUpdate.getClientId());
            
            result = st.executeUpdate();
            st.close();
            con.close();
        }catch(Exception e ){
            System.out.println("error: "+e.getMessage());
        }
        return result;
    }
}
