<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="text-align: center">
        <br/>  
        <a href="clientservlet?operation=">View Clients</a>
        <br/> 
        <table style="margin-left: auto; margin-right: auto;">
            <tr>
                <th>Image</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Email</th> 
                <th>Phone</th>
                <th>ClientId</th>
                <th colspan="2">Operations</th>
                
            </tr>
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
                        <button type="submit">Edit</button>
                    </form>
                </td>
                <td> <!--<a href="clientservlet/delete?id=<c:out value='${client.clientId}' />">Delete</a> -->
                    <form action="clientservlet" method="get">
                        <input type="hidden" name="id" value="${client.clientId}"/>
                        <input type="hidden" name="operation" value="delete"/>
                        <button type="submit">Delete</button>
                    </form>
                </td>
              </tr>
            </c:forEach>
        </table>
              
              
        <h1>Add New Client</h1>  
        <form action="clientservlet" method="post">
            <table style="margin-left: auto; margin-right: auto;">  
                <tr><td>Name:</td><td><input type="text" name="name"/></td></tr>  
                <tr><td>Surname:</td><td><input type="text" name="surname"/></td></tr>  
                <tr><td>Email:</td><td><input type="text" name="email"/></td></tr>  
                <tr><td>Tel:</td><td><input type="text" name="tel"/></td></tr> 
                <tr><td>ImageUrl:</td><td><input type="text" name="imageUrl"/></td></tr>
                <input type="hidden" name="operation" value="add"/>
                <tr><td colspan="2"><input type="submit" value="Save Client"/></td></tr>  
            </table>  
            </form>  
    </body>
</html>
