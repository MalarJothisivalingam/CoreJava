package com.cg.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cg.bean.Status;
@Repository
@Component("dao") 
public class StatusDaoImpl implements StatusDao {
	Status status=new Status();
	Connection con;
	@Override
	public Exception addDetails(Status status) {
		try
		{
		
		Class.forName("org.h2.Driver");
		con=DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test",
				"sa","");
		PreparedStatement stmt=con.prepareStatement("insert into source_schema.report values(?,?,?,?,?,?)");  
		stmt.setString(1,status.getUserName()); 
		stmt.setDate(2, status.getStatusReport());
		stmt.setString(3, status.getCurrentWeekStatus());
		stmt.setString(4, status.getNextWeekStatus());
		stmt.setString(5,status.getIssueNote());
		stmt.setTimestamp(6, status.getTimeStamp());
		stmt.executeUpdate();
		con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
			return e;
		}
		return null;
	}
	 public boolean isUsernameRegistered(String username,Date statusReport) {
		 
		 boolean usernameExists = false;

		    try
		    {
		    	Class.forName("org.h2.Driver");
				con=DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test",
						"sa","");
		        PreparedStatement st = con.prepareStatement("select username,statusreport from source_schema.report where username = ? and statusreport= ?");
		        st.setString(1,username);
		        st.setDate(2, statusReport);
		        ResultSet r1=st.executeQuery();
		        String usernameCounter;
		        Date date;
		         if(r1.next()) 
		         {
		           usernameCounter =  r1.getString("username");
		           date=r1.getDate("statusReport");
		           if(usernameCounter.equals(username) && (date.equals(statusReport))) //this part does not happen even if it should
		           {
		               System.out.println("It already exists");
		              usernameExists = true;
		           }
		         }
		         con.close();
		         }
		     catch (SQLException e) 
		     {
		        System.out.println("SQL Exception: "+ e.toString());
		     } 
		     catch (ClassNotFoundException cE) 
		     {
		        System.out.println("Class Not Found Exception: "+ cE.toString());
		     }
            
		 return usernameExists;
		
		 }
	@Override
	public Exception updateDetails(Status status) {
		String sql = "UPDATE source_schema.report SET currentWeekStatus=?, nextWeekStatus=?, issueNote=?, timeStamp=? WHERE username=? and statusreport=?"; 
		try {
			Class.forName("org.h2.Driver");
			con=DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test",
					"sa","");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,status.getCurrentWeekStatus()); 
			st.setString(2, status.getNextWeekStatus());
			st.setString(3, status.getIssueNote());
			st.setTimestamp(4,status.getTimeStamp());
			st.setString(5, status.getUserName());
			st.setDate(6, status.getStatusReport());
			st.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
			return e;
		}
		return null;
	}
	@Override
	public Status updateFunction(String userName, Date statusReport1) {
		  try
		    {
		    	Class.forName("org.h2.Driver");
				con=DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test",
						"sa","");
		        PreparedStatement st = con.prepareStatement("select username,statusreport,currentweekstatus,nextweekstatus,issuenote from source_schema.report where username = ? and statusreport= ?");
		        st.setString(1,userName);
		        st.setDate(2, statusReport1);
		        ResultSet r1=st.executeQuery();
		        status.setUserName(userName);		        
		        if(r1.next())
		        {
		        	status.setUserName(r1.getString("username"));
		        	status.setStatusReport(r1.getDate("statusreport"));
		        	status.setCurrentWeekStatus(r1.getString("currentWeekStatus"));
		        	status.setNextWeekStatus(r1.getString("nextWeekStatus"));
		        	status.setIssueNote(r1.getString("issueNote"));
		        }
		        con.close();
		    }
		  catch(Exception e)
		  {
			  System.out.println(e);
		  }
		        return status;
	}
}
