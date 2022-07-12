<%-- 
    Document   : account
    Created on : 11-lug-2022, 15.19.16
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
                    <th>AccountId</th>
                    <th>Balance</th>
                    <th>ClientId</th>
                    <th colspan="2">Operations</th>
                </tr>
            </thead
            <tbody>
                <c:forEach items="${accountList}" var="account">
                  <tr>
                    <td><c:out value="${account.accountId}" /></td>
                    <td><c:out value="${account.balance}" /> EUR</td>
                    <td><c:out value="${account.clientId}" /></td>
                    <td>
                        <form action="accountservlet" method="post">
                            <input type="hidden" name="accountId" value="${account.accountId}"/>
                            <input type="hidden" name="operation" value="edit"/>
                            <button type="submit" class="btn btn-warning">Edit</button>
                        </form>
                    </td>
                    <td>
                        <form onsubmit="deleteAccount(event)">
                            <input type="hidden" name="accountId" value="${account.accountId}"/>
                            
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </td>
                  </tr>
                </c:forEach>
            </tbody>
        </table>
              
              
        <h2>Add New Account</h2>  
        <form action="accountservlet" method="post">
            <table style="margin-left: auto; margin-right: auto;">      
                <tr><div class="form-group"><td>Balance:</td><td><input type="text" name="balance"/></td></div></tr>  
                <tr>
                    <div class="form-group"><td>Client:</td>
                        <td>
                            <select class="form-select" name="clientId">
                                <c:forEach items="${clientList}" var="client">
                                    <option value="<c:out value="${client.clientId}" />"><c:out value="${client.clientId}" />-<c:out value="${client.name}" /> <c:out value="${client.surname}" /></option>
                                </c:forEach>
                            </select>
                        </td>
                    </div>
                </tr>
                <input type="hidden" name="operation" value="add"/>
                <tr><div class="form-group"><td colspan="2"><input type="submit" class="btn btn-primary" value="Save Account"/></td></div></tr>  
            </table>  
        </form>  
        <script> 
            function deleteAccount(event){
                event.preventDefault();
                var accountId = event.target.accountId.value;
                var xhr = new XMLHttpRequest();
                xhr.open("DELETE", "/exampleservlet/accountservlet?accountId="+accountId, true);
                xhr.setRequestHeader("Authorization", "");
                xhr.send();
            }
        </script>
    </body>
</html>
