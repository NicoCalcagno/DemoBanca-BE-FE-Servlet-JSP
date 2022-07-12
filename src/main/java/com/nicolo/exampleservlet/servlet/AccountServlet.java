
package com.nicolo.exampleservlet.servlet;

import com.nicolo.exampleservlet.dao.AccountDao;
import com.nicolo.exampleservlet.dao.ClientDao;
import com.nicolo.exampleservlet.dto.AccountDto;
import com.nicolo.exampleservlet.entity.Account;
import com.nicolo.exampleservlet.entity.Client;

import java.io.IOException;

import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author nicolo
 */

public class AccountServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        getAllAccount(request, response);
 
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        deleteAccountByAccountId(request, response);
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println(body);
        try {
            JSONObject accountJson;
            accountJson = new JSONObject(body);
            String accountId = (String) accountJson.get("accountId");
            String balance = (String) accountJson.get("balance");
            String clientId = (String) accountJson.get("clientId");
            
            Account accountToUpdate = new Account(Long.parseLong(accountId), Float.parseFloat(balance), Long.parseLong(clientId));
            AccountDao.updateAccount(accountToUpdate);
            
        } catch (JSONException ex) {
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        getAllAccount(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        
        switch(operation){
            case "add":
                addAccount(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
        }
    }
    
    
    private void getAllAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Account> accountList = AccountDao.retrieveAllAccount();
        List<Client> clientList = ClientDao.retrieveAllClient();
        request.setAttribute("clientList", clientList);
        request.setAttribute("accountList", accountList);
        request.getRequestDispatcher("/account.jsp").forward(request, response);
    }
    
    private void addAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String balance = request.getParameter("balance");
        String clientId = request.getParameter("clientId");
        
        AccountDto accountDtoToSave = new AccountDto(Float.parseFloat(balance), Long.parseLong(clientId));
        AccountDao.addAccount(accountDtoToSave);

        getAllAccount(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountId = request.getParameter("accountId");
        //System.out.println(accountId);
        Account accountToEdit = AccountDao.getAccountByAccountId(Long.parseLong(accountId));
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit-form-account.jsp");
        request.setAttribute("accountToEdit", accountToEdit);
        dispatcher.forward(request, response);
    }
    
    

    
    private void deleteAccountByAccountId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountId = request.getParameter("accountId");
        System.out.println(accountId);
        System.out.println(AccountDao.removeAccount(Long.parseLong(accountId)));
        //request.removeAttribute("accountId");
        
        
        getAllAccount(request, response);
    }

    

}
