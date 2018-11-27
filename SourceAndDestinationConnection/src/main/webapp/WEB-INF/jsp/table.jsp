<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Table</title>
</head>
<style type="text/css">
#Table {
	float: left;
	border: 1px solid black;
}
#myProgress {
  width: 100%;
  background-color: #ddd;
}

#prog {
text-align: center;
  width: 100%;
  height: 30px;
} 
</style>
<script type="text/javascript">
$(document).ready(function() {
	  $('#button').click(function(e) {
		  e.preventDefault();
		  columnFunction();
		//  $('#column').show();
	  });
});

function columnFunction() {
	$.ajax({
		type : 'post',
		url : 'column',
		data : $('form[name="Table"]').serialize(),
		success : function(response) {  
			if (response.validated) {
				var columnname=new Array();
				 columnname=response.colName;
				 var arr=columnname.length;
					if(arr<1)
					{
					alert("No Addition column in destination table");
					var row='<tr><td><input type="text" name="destColumn" id="columnname" value="" readonly="true"/></td></tr>';
					 $("#colAdd").append(row);
					 $('#colAdd').hide();
					var row1='<tr><td><input type="text" name="value" id="value"/></td></tr>';
					 $('#colValue').append(row1);
					 $('#colValue').hide();
					
					}
					else
						{
						 $('#column').show();
				for(var i=0;i<arr;i++)
				 {
					/* var row=' <input type="text" name="destColumn" id="'+columnname[i]+'" value="" />';
					 $("#colAdd").append(row);
					 document.getElementById(columnname[i]).value=columnname[i];
					 var row1='<input type="text" name="value" id="value"/>';
					 $('#colValue').append(row1); */
					 var row='<tr><td><input type="text" name="destColumn" id="'+columnname[i]+'" value="" readonly="true"/></td></tr>';
					 $("#colAdd").append(row);
					 document.getElementById(columnname[i]).value=columnname[i];
					 var row1='<tr><td><input type="text" name="value" id="value"/></td></tr>';
					 $('#colValue').append(row1);
				// alert(arr);
				 }}
				
			}}
	
	});

};
	var currProgress = 0;
	var done = false;
	var total = 100;
	function startProgress() {

	var prBar = document.getElementById("prog");
	var startButt = document.getElementById("startBtn");
	var val = document.getElementById("numValue");
	startButt.disabled=true;
	prBar.value = currProgress;
	val.innerHTML = Math.round((currProgress/total)*100)+"%";
	currProgress++;
	if(currProgress>100) done=true;
	if(!done)
		setTimeout("startProgress()", 100);
	else
	{
		document.getElementById("startBtn").disabled = false;
		done = false;
		currProgress = 0;
		alert("Transferring records is Completed")
		document.getElementById("Table").submit();
	}
	}
</script>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<body>
<form:form id="Table" name="Table" action="alltable" modelAttribute="table" commandName="table">
<div class="container">
<h1 >Table</h1>
			<div class="form-group">
	<label for="sourceTable">Source Table</label> <input type="text" class="form-control" name="sourceTable" id="sourceTable"  placeholder="Enter Source Table"/>
			</div>
<div class="form-group">
	<label for="destTable">Destination Table</label> <input type="text" class="form-control" name="destTable" placeholder="Enter Destination Table" id="destTable" />
			</div>
	<div class="form-group">
		<label for="forceMatch">Force Match</label> 
		<input type="text" class="form-control" name="forceMatch" placeholder="Enter Force Match" id="forceMatch" />
			</div>
			<div class="form-group">
				<label for="">Source Schema</label> <input type="text" class="form-control" name="sourceSchema" placeholder="Enter Source schema" id="sourceSchema" />
			</div>
			<div class="form-group">
				<label for="destSchema">Destination Schema</label> <input type="text" class="form-control"
					name="destSchema" placeholder="Enter Destination Schema" id="destSchema" />
			</div>
				<button id="button" name="button" class="btn btn-primary" onclick="this.disabled = 'disabled';">To Add Column</button>
			
			<div id="column" style="display: none;">
			<table border="1">
			<tr><th>DestinationColumn</th><th>Value</th></tr>
			<tr><td id="colAdd"></td><td id="colValue"></td></tr>
			</table>
			</div>
			<div class="form-group">
				<label for="clause">clause</label> <input type="text" class="form-control"
					name="clause" placeholder="Enter Clause" id="clause" />
			</div>
			<input id="startBtn" type="button" value="TRANSFER RECORDS" onclick="startProgress()" class="btn btn-success"/>			
			<input type="submit" value="CLOSE" formaction="closeConnection" class="btn btn-success"/>
				</div>
                <progress id="prog" value="0" max="100"></progress>					
				<div id="numValue"align="center">0%</div>
				</form:form>
</body>
</html>