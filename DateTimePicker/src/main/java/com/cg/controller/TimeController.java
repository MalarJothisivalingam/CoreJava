package com.cg.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;



import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TimeController {

	@RequestMapping(value = "/value")
	public ModelAndView Function(@RequestParam("datetimepicker1") String Startdate,@RequestParam("datetimepicker2") String Enddate)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy H:mm"); 
		
		         /*****************  START DATE AND TIME   *********************/
		System.out.println("\n          START DATE TIME     \n");
        LocalDateTime localDateTime1 = LocalDateTime.parse(Startdate, formatter);             
        System.out.println("\nString Format For Start DateTime :  "+formatter.format(localDateTime1));
        
    
        ZonedDateTime zdtAtest1 = localDateTime1.atZone( ZoneId.of("America/New_York"));
        System.out.println("\nZonedDateTime[EST] for Start Date  :  "+zdtAtest1);
        
        LocalDateTime ldt1 = zdtAtest1.toLocalDateTime();
        System.out.println("\nLocalDateTime for Start Date Time in EST "+ldt1);
        System.out.println("\nString Format for Start Date Time in EST "+formatter.format(ldt1));
        
        ZonedDateTime zdtAtgmT1 = zdtAtest1.withZoneSameInstant( ZoneId.of("GMT") );
        System.out.println("\nZonedDateTime[GMT] for Start DateTime  :  "+zdtAtgmT1);
        
        LocalDateTime StartDateTime = zdtAtgmT1.toLocalDateTime();        
        System.out.println("\nLocalDateTime for Start Date Time in GMT  :  "+StartDateTime);
        System.out.println("\nString Format for Start Date Time in GMT  :  "+formatter.format(StartDateTime));
        
        
                /*****************  END DATE AND TIME   *********************/
        System.out.println("\n          END DATE TIME     \n");
        LocalDateTime localDateTime2 = LocalDateTime.parse(Enddate, formatter);             
        System.out.println("\nString Format For End DateTime  :  "+formatter.format(localDateTime2));
        
         
        ZonedDateTime zdtAtest2 = localDateTime2.atZone( ZoneId.of("America/New_York") );
        System.out.println("\nZonedDateTime[EST] for End DateTime  :  "+zdtAtest2);
        
        LocalDateTime ldt2 = zdtAtest2.toLocalDateTime();
        System.out.println("\nLocalDateTime for End Date Time in EST "+ldt2);
        System.out.println("\nString Format for End Date Time in EST "+formatter.format(ldt2));
        
        ZonedDateTime zdtAtgmT2 = zdtAtest2.withZoneSameInstant( ZoneId.of("GMT") );
        System.out.println("\nZonedDateTime[GMT] for End DateTime  :  "+zdtAtgmT2);
        
        LocalDateTime EndDateTime = zdtAtgmT2.toLocalDateTime();        
        System.out.println("\nLocalDateTime for End Date Time in GMT  :  "+EndDateTime);
        System.out.println("\nString Format for End Date Time in GMT  :  "+formatter.format(EndDateTime));
        
             /******************   CURRENT DATE TIME   ************************/
        System.out.println("\n          CURRENT DATE TIME     \n");
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy H:mm");
    	Date localTime = new Date(); 
        String NewDate=dateFormat.format(localTime);
    	
        DateFormat converter = new SimpleDateFormat("MM/dd/yyyy H:mm");

        converter.setTimeZone(TimeZone.getTimeZone("GMT"));
       
        System.out.println("\nZonedDateTime[GMT] for Current Date time  :  " + converter.format(localTime));
        LocalDateTime CurrentDateTime = LocalDateTime.parse(converter.format(localTime), formatter);    
        
        System.out.println("\nLocalDateTime for Current Date Time in GMT  :  "+CurrentDateTime);
        System.out.println("\nString format for Current Date Time in GMT  :  "+formatter.format(CurrentDateTime));
        
            /*******************   Validation **************************/

        if(StartDateTime.isEqual(CurrentDateTime))
        {
        	System.out.println("\nstart DateTime EQUAL TO Current DateTime");
        }
        if(StartDateTime.isAfter(CurrentDateTime))
        {
        	System.out.println("\nstart DateTime GREATER THAN Current DateTime");
        }
        if(StartDateTime.isBefore(CurrentDateTime))
        {
        	System.out.println("\nstart DateTime LESSER THAN Current DateTime");
        }
        if(EndDateTime.isEqual(CurrentDateTime))
        {
        	System.out.println("\nEnd DateTime EQUAL TO Current DateTime");
        }
        if(EndDateTime.isAfter(CurrentDateTime))
        {
        	System.out.println("\nEnd DateTime GREATER THAN Current DateTime");
        }
        if(EndDateTime.isBefore(CurrentDateTime))
        {
        	System.out.println("\nEnd DateTime LESSER THAN Current DateTime");
        }

		return null;
	}

}
