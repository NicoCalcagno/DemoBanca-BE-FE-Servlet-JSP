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
        <h1>Edit Client</h1>  
        <form onsubmit="updateClient(event)">
            <table style="margin-left: auto; margin-right: auto;">  
                <tr><div class="form-group"><td>Name:</td><td><input type="text" name="name" value="${clientToEdit.name}"/></td></div></tr>  
                <tr><div class="form-group"><td>Surname:</td><td><input type="text" name="surname" value="${clientToEdit.surname}"/></td></div></tr>  
                <tr><div class="form-group"><td>Email:</td><td><input type="text" name="email" value="${clientToEdit.email}"/></td></div></tr>  
                <tr><div class="form-group"><td>Tel:</td><td><input type="text" name="tel" value="${clientToEdit.tel}"/></td></div></tr> 
                <tr><div class="form-group"><td>ImageUrl:</td><td><input type="text" name="imageUrl" value="${clientToEdit.imageUrl}"/></td></div></tr>
                <input type="hidden" name="clientId" value="${clientToEdit.clientId}"/>
                <tr><div class="form-group"><td colspan="2"><input type="submit" value="Update Client"/></td></div></tr>  
            </table>  
            </form>  
                <script>
                    function updateClient(event){
                        event.preventDefault();
                        var clientId = event.target.clientId.value;
                        var name = event.target.name.value;
                        var surname = event.target.surname.value;
                        var email = event.target.email.value;
                        var tel = event.target.tel.value;
                        var imageUrl = event.target.imageUrl.value;
                        
                        var jsonParams = {
                            clientId : clientId,
                            name : name,
                            surname : surname,
                            email : email,
                            tel : tel,
                            imageUrl : imageUrl
                        };
                        
                        
                        var json = JSON.stringify(jsonParams);
                        var xhr = new XMLHttpRequest();
                        xhr.open('PUT', '/exampleservlet/clientservlet', true);
                        xhr.setRequestHeader("Authorization", "");
                        xhr.setRequestHeader('Content-Type',  'application/json');
                       
                        xhr.send(json);
                    }
                </script>
    </body>
</html>
