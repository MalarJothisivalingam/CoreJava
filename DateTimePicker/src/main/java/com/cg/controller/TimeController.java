package com.cg.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TimeController {
	@RequestMapping(value = "/value")
	public ModelAndView time(@RequestParam("datetimepicker1") String date)
	{
		
		try
		{
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy H:mm"); 
	        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);             
	        System.out.println(localDateTime);
	        
	        ZoneId zoneId = ZoneId.of("America/New_York");         //EST ZONED DATETIME
	        ZonedDateTime zdtAtest = localDateTime.atZone( zoneId );
	        System.out.println("\nZonedDateTime for America/New_York "+zdtAtest);
	        
	        
	        
	        /**********converting from EST ZonedDateTime To GMT ZonedDateTime**************/
	        
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
	}
}
