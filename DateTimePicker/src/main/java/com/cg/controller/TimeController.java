package com.cg.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;



import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TimeController {
	/*@RequestMapping(value = "/value")
	public ModelAndView time(@RequestParam("datetimepicker1") String date,@RequestParam("datetimepicker3") String date1)
	{
		System.out.println(date1);
		try
		{
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy H:mm"); 
	        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);             
	        System.out.println(localDateTime);
	        
	        ZoneId zoneId = ZoneId.of("America/New_York");         //EST ZONED DATETIME
	        ZonedDateTime zdtAtest = localDateTime.atZone( zoneId );
	        System.out.println("\nZonedDateTime for America/New_York "+zdtAtest);
	        
	        
	        
	        *//**********converting from EST ZonedDateTime To GMT ZonedDateTime**************//*
	        
	        ZonedDateTime zdtAtgmT = zdtAtest.withZoneSameInstant( ZoneId.of("GMT") );
	        System.out.println("\nZonedDateTime for GMT "+zdtAtgmT);
	        
	        LocalDateTime ldt1 = zdtAtgmT.toLocalDateTime();        
	        System.out.println("\nLocalDateTime for GMT "+ldt1);
	        System.out.println("\nString Format for GMT "+formatter.format(ldt1));
	        
	        LocalDateTime ldt = zdtAtest.toLocalDateTime();
	        System.out.println("\nLocalDateTime for EST "+ldt);
	        System.out.println("\nString Format for EST "+formatter.format(ldt));
	        
		}catch(Exception e)
		{
		System.out.println(e);
		}
	  
		return null;
	}*/
	@RequestMapping(value = "/value")
	public ModelAndView Function(@RequestParam("datetimepicker1") String Startdate,@RequestParam("datetimepicker2") String Enddate)
	{
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MM/dd/yyyy H:mm"); 
        LocalDateTime localDateTime1 = LocalDateTime.parse(Startdate, formatter1);             
       // System.out.println(localDateTime1);
        String startDate=formatter1.format(localDateTime1);
        System.out.println("\nString Format For Start DateTime "+formatter1.format(localDateTime1));
        
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM/dd/yyyy H:mm"); 
        LocalDateTime localDateTime2 = LocalDateTime.parse(Enddate, formatter2);             
       // System.out.println(localDateTime2);
        String endDate=formatter2.format(localDateTime2);
        System.out.println("\nString Format For End DateTime "+formatter2.format(localDateTime2));
        
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy H:mm");
    	Date date = new Date();
    	System.out.println(dateFormat.format(date));
    	String CDate=dateFormat.format(date);
    	
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy H:mm");  
        LocalDateTime currentTime = LocalDateTime.parse(CDate, dtf) ;
      
        System.out.println("\nString Format For Current DateTime " +dtf.format(currentTime));
        
       /* if(startDate.equals(CDate))
        {
        	System.out.println("\nstart DateTime EQUAL TO Current DateTime");
        }
        else 
        {
        if(startDate.compareTo(CDate) > 0)
        	
               	System.out.println("\nstart DateTime GREATER THAN Current DateTime");
       
        else 
               	System.out.println("\nstart DateTime LESSER THAN Current DateTime");
        
        }
        
        if(endDate.equals(CDate))
        {
        	System.out.println("\nEnd DateTime EQUAL TO Current DateTime");
        }
        else
        {
        if(endDate.compareTo(CDate) > 0)
        	
               	System.out.println("\nEnd DateTime GREATER THAN Current DateTime");
        
        else 
             	System.out.println("\nEnd DateTime LESSER THAN Current DateTime");
       
        }*/
        System.out.println("\ndifferent time and date");
        if(localDateTime1.isEqual(currentTime))
        {
        	System.out.println("\nstart DateTime EQUAL TO Current DateTime");
        }
        if(localDateTime1.isAfter(currentTime))
        {
        	System.out.println("\nstart DateTime GREATER THAN Current DateTime");
        }
        if(localDateTime1.isBefore(currentTime))
        {
        	System.out.println("\nstart DateTime LESSER THAN Current DateTime");
        }
        if(localDateTime2.isEqual(currentTime))
        {
        	System.out.println("\nEnd DateTime EQUAL TO Current DateTime");
        }
        if(localDateTime2.isAfter(currentTime))
        {
        	System.out.println("\nEnd DateTime GREATER THAN Current DateTime");
        }
        if(localDateTime2.isBefore(currentTime))
        {
        	System.out.println("\nEnd DateTime LESSER THAN Current DateTime");
        }
		return null;
	}

}
