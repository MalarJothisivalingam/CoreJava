package com.cg.dao;

import java.sql.Date;

import com.cg.bean.Status;

public interface StatusDao {

	public Exception addDetails(Status status);
	public Exception updateDetails(Status status);
	public boolean isUsernameRegistered(String userName,Date statusReport);
	public Status updateFunction(String userName, Date statusReport1);
}
