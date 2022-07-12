<%-- 
    Document   : edit-form-account
    Created on : 11-lug-2022, 15.19.33
    Author     : nicolo
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
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
        <h1>Edit Account</h1>  
        <form onsubmit="updateAccount(event)">
            <table style="margin-left: auto; margin-right: auto;">  
                <tr><div class="form-group"><td>Balance:</td><td><input type="text" name="balance" value="${accountToEdit.balance}"/></td></div></tr>  

                <input type="hidden" name="accountId" value="${accountToEdit.accountId}"/>
                <input type="hidden" name="clientId" value="${accountToEdit.clientId}"/>
                <tr><div class="form-group"><td colspan="2"><button type="submit" class="btn btn-warning">Update Account</button></td></div></tr>  
            </table>  
            </form>
                <script>
                    function updateAccount(event){
                        event.preventDefault();
                        var accountId = event.target.accountId.value;
                        var balance = event.target.balance.value;
                        var clientId = event.target.clientId.value;
                        var jsonParams = {
                            accountId : accountId,
                            balance : balance,
                            clientId : clientId
                        };
                        
                        //var params = "accountId="+accountId+"&balance="+balance+"&clientId="+clientId;
                        //console.log(jsonParams);
                        var json = JSON.stringify(jsonParams);
                        var xhr = new XMLHttpRequest();
                        xhr.open('PUT', '/exampleservlet/accountservlet', true);
                        xhr.setRequestHeader("Authorization", "");
                        xhr.setRequestHeader('Content-Type',  'application/json');
                       
                        xhr.send(json);
                        console.log(json);
                    }
                </script>
    </body>
</html>

