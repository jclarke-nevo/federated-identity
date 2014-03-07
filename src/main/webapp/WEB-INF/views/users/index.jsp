<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Users</title>
<style type="text/css">
	table {
		border-collapse: collapse;
	}
	th, td {
		border: 1px solid #98bf21;
		padding: 3px;
	}
	th {
		background-color: #A7C942;
		color: white;
	}
</style>
</head>
<body>
	<c:if test="${IdToken != null}">
		<h1>Received token:</h1>
		<textarea rows="4" cols="80">${IdToken}</textarea>
	</c:if>
	<h1>All Users List</h1>
	<table>
	<tr>
		<th style="width: 200px;">ID</th>
		<th>Name</th>
	</tr>
	<c:forEach var="user" items="${UserList}">
		<tr>
			<td><c:out value="${user.id}" /></td>
			<td><c:out value="${user.username}" /></td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>