<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<title>Insert title here</title>
<style type="text/css">
#status1 {
	border: 4px solid black;
	width: 800px;
	padding:10px;
}

</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script> 
	  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.15.1/moment.min.js"></script>
<script type="text/javascript">
var t;
	$(document).ready(function() {
		startTime();
	function startTime() {
	    var today = new Date();
	    var today1 = moment().format('DD/MM/YYYY');
	    var h = today.getHours();
	    var m = today.getMinutes();
	    var s = today.getSeconds();
	    m = checkTime(m);
	    s = checkTime(s);
	    document.getElementById('timeStamp').value =today1+" " + h + ":" + m + ":" + s;
	    t = setTimeout(startTime, 500);
	}
	function checkTime(i) {
	    if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
	    return i;
	}
    $('#status1').submit(function(e) {
    	e.preventDefault();
    	clearTimeout(t);
    	updateFunction();
	});
	});
	function updateFunction() {

		$.ajax({
			type : 'post',
			url : 'update',
			data : $('form[name="update"]').serialize(),
			success : function(response) {
				if (response.validated) {
					$('#resultContainer').show();
				}
				else
					{
					alert("error in sql");
					}
			}
		});

	};
</script>
</head>
<body>
<div id="resultContainer" style="display: none;">

				<h4 align="center" style="color: green;">Successfully Updated</h4>
				<!-- <pre style="color: green;">
				
    <code></code>
   </pre> -->
			</div>
<div class="span6" align="center">
<form:form id="status1" name="update" class="form-signin" method="post">
	<h1 align="center">Update Weekly Status Report</h1>
		<table id="statusTable" >
				<tr><td><Strong>User Name</Strong></td>
				<td><input type="text" name="userName" class="form-group" id="userName" value="${userName}" readonly/></td></tr>
				<tr><td><Strong>Status Report</Strong></td>
				<td><input type="text" name="statusReport" class="form-group" id="statusReport" value="${statusReport}" readonly/></td></tr>
				<tr><td><Strong>Current Week Status</Strong></td> 
				<td><textarea name="currentWeekStatus" class="form-group" id="currentWeekStatus" rows="5" cols="60" required>${CurrentWeekStatus}</textarea></td></tr>
				<tr><td><Strong>Next Week Status</Strong></td>
				<td><textarea name="nextWeekStatus" class="form-group" id="nextWeekStatus" rows="5" cols="60" required>${NextWeekStatus}</textarea></td></tr>
                <tr><td><Strong>Issue Note</Strong></td>
				<td><textarea name="issueNote" class="form-group" id="issueNote" rows="5" cols="60" required>${IssueNote}</textarea></td></tr>
		         <tr><td><Strong>Updated TimeStamp</Strong></td>
				<td><input type="datetime" name="timeStamp" class="form_datetime" id="timeStamp" readonly/></td></tr>
		        <tr><td></td><td><input type="submit" id="statusBtn" class="btn btn-success" value="submit"></td></tr>
</table>
			</form:form>
			</div>
			<a  id="home" href="index.jsp">Home</a>
</body>
</html>