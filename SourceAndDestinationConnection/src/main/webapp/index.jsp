<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Table</title>
</head>
<style type="text/css">
#form {
	width: 450px;
	padding: 10px;
}
</style>
<body>
	<h1 align="center">WELCOME TO DATAPUMP</h1>
	<div align="center" class="form-group">
		<form id="form" action="database">
			<div class="form-group">
				<label for="selectSource">Select Database For Source(select one):</label> <select
					class="form-control" name="selectSource" id="selectSource">
					<option value="Oracle">Oracle</option>
					<option value="H2">H2</option>
					<option value="MYSQL">MYSQL</option>
					<option value="DB2">DB2</option>
					<option value="other">other</option>
				</select>
			</div>
			<div class="form-group">
				<label for="selectDestination">Select Database For Destination(select one):</label> <select
					class="form-control" name="selectDestination" id="selectDestination">
					<option value="Oracle">Oracle</option>
					<option value="H2">H2</option>
					<option value="MYSQL">MYSQL</option>
					<option value="DB2">DB2</option>
					<option value="other">other</option>
				</select>
			</div>
			<input type="submit" value="CONNECT" class="btn btn-success" />
		</form>
	</div>
</body>
</html>
<!-- <a href="home">CLICK</a> -->