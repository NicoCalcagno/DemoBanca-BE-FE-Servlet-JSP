/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.nicolo.exampleservlet.servlet;

import com.nicolo.exampleservlet.dao.ClientDao;
import com.nicolo.exampleservlet.dto.ClientDto;
import com.nicolo.exampleservlet.entity.Client;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author nicolo
 */

public class ClientServlet extends HttpServlet {

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        System.out.println(operation);
        switch(operation){
            case "add":
                addClient(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
        }
        
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        deleteClientByClientId(request, response);
    }
    
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println(body);
        try {
            JSONObject clientJson;
            clientJson = new JSONObject(body);
            String clientId = (String) clientJson.get("clientId");
            String name = (String) clientJson.get("name");
            String surname = (String) clientJson.get("surname");
            String email = (String) clientJson.get("email");
            String tel = (String) clientJson.get("tel");
            String imageUrl = (String) clientJson.get("imageUrl");
            
            Client clientToUpdate = new Client(Long.parseLong(clientId) ,name ,surname ,email ,tel , imageUrl);
            ClientDao.updateClient(clientToUpdate);
            
        } catch (JSONException ex) {
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        doGet(request, response);
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getAllClient(request, response);
    }
    
    private void getAllClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Client> clientList = ClientDao.retrieveAllClient();
        request.setAttribute("clientList", clientList);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
    
    private void addClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String imageUrl = request.getParameter("imageUrl");
        ClientDto clientDtoToSave = new ClientDto(name, surname, email, tel, imageUrl);
        ClientDao.addClient(clientDtoToSave);

        getAllClient(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientId = request.getParameter("clientId");
        System.out.println(clientId);
        Client clientToEdit = ClientDao.getClientByClientId(Long.parseLong(clientId));
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit-form.jsp");
        request.setAttribute("clientToEdit", clientToEdit);
        dispatcher.forward(request, response);
    }
    
    private void deleteClientByClientId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String clientId = request.getParameter("clientId");
        System.out.println(clientId);
        System.out.println(ClientDao.deleteClient(Long.parseLong(clientId)));
        
        
        doGet(request, response);
    }
    
}
