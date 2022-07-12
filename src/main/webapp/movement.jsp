<%-- 
    Document   : movement
    Created on : 12-lug-2022, 11.46.37
    Author     : nicolo
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body style="text-align: center">
        <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    
                    <ul class="navbar-nav">
                        <li><a href="clientservlet" class="nav-link">View Clients</a></li>
                        <li><a href="accountservlet" class="nav-link">View Accounts</a></li>
                        <li><a href="movementservlet" class="nav-link">View Movements</a></li>
                    </ul>
                </nav>
            </header>
            <br>
        <br/>  
        
        <br/> 
        <table style="margin-left: auto; margin-right: auto;" class="table table-bordered">
            <thead>
                <tr>
                    <th>MovementId</th>
                    <th>Amount</th>
                    <th>Type</th>
                    <th>AccountId</th> 
                    <th>Balance</th>
                    <th>Date</th>
                    
                    <th colspan="2">Operation</th>
                </tr>
            </thead
            <tbody>
                <c:forEach items="${movementList}" var="movement">
                  <tr>
                    <td><c:out value="${movement.movementId}" /></td>
                    <td><c:out value="${movement.amount}" /> EUR</td>
                    <td><c:out value="${movement.type}" /></td>
                    <td><c:out value="${movement.accountId}" /></td>
                    <td><c:out value="${movement.balance}" /> EUR</td>
                    <td><c:out value="${movement.dataMovement}" /></td>
                    
                    <td> 
                        <form onsubmit="deleteMovement(event)">
                            <input type="hidden" name="movementId" value="${movement.movementId}"/>
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </td>
                  </tr>
                </c:forEach>
            </tbody>
        </table>
              
              
        <h2>Add New Movement</h2>  
        <form action="movementservlet" method="post">
            <table style="margin-left: auto; margin-right: auto;">  
                
                
                <tr>
                    <div class="form-group"><td>Type:</td>
                        <td>
                            <select class="form-select" name="type">   
                                <option value="Prelievo">Prelievo</option>
                                <option value="Deposito">Deposito</option>
                            </select>
                        </td>
                    </div>
                </tr>
                <tr><div class="form-group"><td>Amount:</td><td><input type="text" name="amount"/></td></div></tr>  
               <tr>
                    <div class="form-group"><td>Account:</td>
                        <td>
                            <select class="form-select" name="accountId">
                                <c:forEach items="${accountList}" var="account">
                                    <option value="<c:out value="${account.accountId}" />"><c:out value="${account.accountId}" /></option>
                                </c:forEach>
                            </select>
                        </td>
                    </div>
                </tr>
                
                <input type="hidden" name="operation" value="add"/>
                <tr><div class="form-group"><td colspan="2"><input type="submit" class="btn btn-primary" value="Save Client"/></td></div></tr>  
            </table>  
            </form>  
        <script>
            function deleteMovement(event){
                event.preventDefault();
                var movementId = event.target.movementId.value;
                var xhr = new XMLHttpRequest();
                xhr.open("DELETE", "/exampleservlet/movementservlet?movementId="+movementId, true);
                xhr.setRequestHeader("Authorization", "");
                xhr.send();
            }
        </script>
    </body>
</html>
