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
              
        <h1>Edit Client</h1>  
        <form action="clientservlet" method="post">
            <table style="margin-left: auto; margin-right: auto;">  
                <tr><td>Name:</td><td><input type="text" name="name" value="${clientToEdit.name}"/></td></tr>  
                <tr><td>Surname:</td><td><input type="text" name="surname" value="${clientToEdit.surname}"/></td></tr>  
                <tr><td>Email:</td><td><input type="text" name="email" value="${clientToEdit.email}"/></td></tr>  
                <tr><td>Tel:</td><td><input type="text" name="tel" value="${clientToEdit.tel}"/></td></tr> 
                <tr><td>ImageUrl:</td><td><input type="text" name="imageUrl" value="${clientToEdit.imageUrl}"/></td></tr>
                <input type="hidden" name="operation" value="update"/>
                <input type="hidden" name="clientId" value="${clientToEdit.clientId}"/>
                <tr><td colspan="2"><input type="submit" value="Update Client"/></td></tr>  
            </table>  
            </form>  
    </body>
</html>
