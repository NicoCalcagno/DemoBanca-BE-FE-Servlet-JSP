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
                    <th>Image</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Email</th> 
                    <th>Phone</th>
                    <th>ClientId</th>
                    <th colspan="2">Operations</th>
                </tr>
            </thead
            <tbody>
                <c:forEach items="${clientList}" var="client">
                  <tr>
                    <td><img src="${client.imageUrl}"  height="20px" width="20px" alt="alt"/></td>
                    <td><c:out value="${client.name}" /></td>
                    <td><c:out value="${client.surname}" /></td>
                    <td><c:out value="${client.email}" /></td>
                    <td><c:out value="${client.tel}" /></td>
                    <td><c:out value="${client.clientId}" /></td>
                    <td>
                        <form action="clientservlet" method="post">
                            <input type="hidden" name="clientId" value="${client.clientId}"/>
                            <input type="hidden" name="operation" value="edit"/>
                            <button type="submit" class="btn btn-warning">Edit</button>
                        </form>
                    </td>
                    <td> 
                        <form onsubmit="deleteClient(event)">
                            <input type="hidden" name="clientId" value="${client.clientId}"/>
                            
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </td>
                  </tr>
                </c:forEach>
            </tbody>
        </table>
              
              
        <h2>Add New Client</h2>  
        <form action="clientservlet" method="post">
            <table style="margin-left: auto; margin-right: auto;">  
                
                <tr><div class="form-group"><td>Name:</td><td><input type="text" name="name"/></td></div></tr>  
                <tr><div class="form-group"><td>Surname:</td><td><input type="text" name="surname"/></td></div></tr>  
                <tr><div class="form-group"><td>Email:</td><td><input type="text" name="email"/></td></div></tr>  
                <tr><div class="form-group"><td>Tel:</td><td><input type="text" name="tel"/></td></div></tr> 
                <tr><div class="form-group"><td>ImageUrl:</td><td><input type="text" name="imageUrl"/></td></div></tr>
                <input type="hidden" name="operation" value="add"/>
                <tr><div class="form-group"><td colspan="2"><input type="submit" class="btn btn-primary" value="Save Client"/></td></div></tr>  
            </table>  
            </form>  
        <script>
            function deleteClient(event){
                event.preventDefault();
                var clientId = event.target.clientId.value;
                var xhr = new XMLHttpRequest();
                xhr.open("DELETE", "/exampleservlet/clientservlet?clientId="+clientId, true);
                xhr.setRequestHeader("Authorization", "");
                xhr.send();
            }
        </script>
    </body>
</html>
