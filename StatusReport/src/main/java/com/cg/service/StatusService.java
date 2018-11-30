package com.cg.service;

import java.sql.Date;

import com.cg.bean.Status;

public interface StatusService {
	public Exception addDetails(Status status);
	public Exception updateDetails(Status status);
	 public boolean isUsernameRegistered(String username,Date statusReport);
	public Status updateFunction(String userName, Date statusReport1);
}
