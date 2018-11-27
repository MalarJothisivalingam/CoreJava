<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="container">
<h1 align="center">Successfully Completed</h1>
<table border="1" align="center">
<tr>
<th>Source Table</th>
<th>Destination Table</th>
<th>No Of Records</th>
</tr>
<c:forEach items = "${totalRecords}"  var = "list">
<tr><td>
${list.srcTable}</td>
<td>${list.destTable}</td>
<td>${list.records}</td></tr>
</c:forEach>
</table>
</div>
</body>
</html>