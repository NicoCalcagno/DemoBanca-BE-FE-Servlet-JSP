
package com.nicolo.exampleservlet.servlet;

import com.nicolo.exampleservlet.dao.AccountDao;

import com.nicolo.exampleservlet.dao.MovementDao;
import com.nicolo.exampleservlet.dto.MovementDto;
import com.nicolo.exampleservlet.entity.Account;
import com.nicolo.exampleservlet.entity.Movement;
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
public class MovementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        getAllMovement(request, response);
 
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        deleteMovementByMovementId(request, response);
    }
    
    

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
                addMovement(request, response);
                
        
    }
    
    
    private void getAllMovement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Movement> movementList = MovementDao.retrieveAllMovement();
        List<Account> accountList = AccountDao.retrieveAllAccount();
        request.setAttribute("movementList", movementList);
        request.setAttribute("accountList", accountList);
        request.getRequestDispatcher("/movement.jsp").forward(request, response);
    }
    
    private void addMovement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String amount = request.getParameter("amount");
        String type = request.getParameter("type");
        String accountId = request.getParameter("accountId");
        System.out.println(amount+" "+type+" "+accountId);
        MovementDto movementDtoToSave = new MovementDto(Float.parseFloat(amount), type, Long.parseLong(accountId));
        MovementDao.addMovement(movementDtoToSave);

        getAllMovement(request, response);
    }
    
    
    private void deleteMovementByMovementId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String movementId = request.getParameter("movementId");
        System.out.println(movementId);
        System.out.println(MovementDao.removeMovement(Long.parseLong(movementId)));
        //request.removeAttribute("accountId");
        
        
        getAllMovement(request, response);
    }
}
