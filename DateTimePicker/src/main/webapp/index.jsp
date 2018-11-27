<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">

</style>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />


<script>
	$(function() {
		
		$("#datetimepicker1").daterangepicker({
			singleDatePicker : true,
			showDropdowns : true,
			timePicker : true,
			timePicker24Hour : true,
			autoUpdateInput: false,
			locale : {
				format : 'MM/DD/YYYY H:mm',
				cancelLabel: 'Clear'
			}
		});
		$("#datetimepicker2").daterangepicker({
			singleDatePicker : true,
			showDropdowns : true,
			timePicker : true,
			timePicker24Hour : true,
			autoUpdateInput: false,
			locale : {
				format : 'MM/DD/YYYY H:mm',
					cancelLabel: 'Clear'
			}
		});
		 $("#datetimepicker1").on('apply.daterangepicker', function(ev, picker) {
		      $(this).val(picker.endDate.format('MM/DD/YYYY H:mm'));
		  });
		 $("#datetimepicker2").on('apply.daterangepicker', function(ev, picker) {
		      $(this).val(picker.endDate.format('MM/DD/YYYY H:mm'));
		  });
		 $("#datetimepicker1").on('cancel.daterangepicker', function(ev, picker) {
		      $(this).val('');
		  });
		 $("#datetimepicker2").on('cancel.daterangepicker', function(ev, picker) {
		      $(this).val('');
		  });

	});
	$(document).ready(function() {
		$("#datetimepicker1").val("");
	    $("#datetimepicker2").val(""); 
        $('#date').submit(function(e){
        	
		e.preventDefault();
		var count=0;
		 var datetimepicker1 = $('#datetimepicker1').val();
		 var datetimepicker2 =$('#datetimepicker2').val();
		 if(datetimepicker1.length < 1 && datetimepicker2.length < 1)
		 {
		 alert("Both field should not be empty");
		 count=1;
		 }
		 else if (datetimepicker1.length < 1) 
		 {
		     alert("Start Date and Time should not be empty");
		     count=1;
		   }
		 else if(datetimepicker2.length < 1)
			 {
			 alert("End Date and Time should not be empty");
		     count=1;
			 }
		  
	  if(count==0)
	  {			
        var startdate= $("#datetimepicker1").val();
        var enddate= $("#datetimepicker2").val();
        startdate=new Date(startdate);
        enddate=new Date(enddate);
        var fromDate=moment(startdate,'DD/MM/YYYY');
        var toDate=moment(enddate,'DD/MM/YYYY');
        var daysDiff=toDate.diff(fromDate,'days');
       
       /************Finding Month***********/
        var startmonth=startdate.getMonth()+1;
        var endmonth=enddate.getMonth()+1;
 
        if(daysDiff==1 || daysDiff==0)
         {

           var diff=enddate-startdate;           
           var diffSeconds = diff/1000;
           var HH = Math.floor(diffSeconds/3600);
           var MM = Math.floor(diffSeconds%3600)/60;
           
           if(HH<4 && HH>=0)
           	{
           	alert("success");
           	document.getElementById("date").submit()
           	}
           else if(HH==4 && MM==0)
           	{
           	alert("success");                	
           	document.getElementById("date").submit()
           	}
           else if(HH<0)
           	{
           	alert("End time Should be higher than Start time");
           	$('#datetimepicker2').val("");
           	}
           else              
           	{   	
              alert("Hour Limit should be 4 hours");
              $('#datetimepicker2').val("");
           	}
        
       }
       
       else if(daysDiff>1)
       	{
       	alert("Difference between Start Date and end date is more than one day");
       	$('#datetimepicker2').val("");
       	}
       	
       else
       	{
           alert("End Date is less than start Date");	
           $('#datetimepicker2').val("");
       	}
		}
        });
	});
</script>
<body>
   <form id="date">
		Start Date:<br> <input type="text" id="datetimepicker1" class="field left" readonly ><br>
		End Date: <br> <input type="text" id="datetimepicker2" class="field left" readonly ><br>
	   <br><input type="submit" value="Submit">
	 </form>
</body>
</html>


