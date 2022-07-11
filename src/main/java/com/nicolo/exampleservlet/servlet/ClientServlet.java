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
            case "update":
                updateClient(request, response);
                break;
        }
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");
        
        
        switch(operation){
            case "delete":
                System.out.println(operation);
                deleteClientByClientId(request, response);
                break;
            default:
                System.out.println(operation);
                getAllClient(request, response);
                break;
        }
        
        
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

    private void updateClient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String clientId = request.getParameter("clientId");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String imageUrl = request.getParameter("imageUrl");
        Client clientToUpdate = new Client(Long.parseLong(clientId), name, surname, email, tel, imageUrl);
        ClientDao.updateClient(clientToUpdate);
        
        getAllClient(request, response);
    }

    
    private void deleteClientByClientId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String clientId = request.getParameter("id");
        System.out.println(clientId);
        System.out.println(ClientDao.deleteClient(Long.parseLong(clientId)));
        request.removeAttribute("id");
        request.setAttribute("operation", "");
        
        getAllClient(request, response);
    }
    
}
