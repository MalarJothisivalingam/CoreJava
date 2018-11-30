package com.cg.service;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cg.bean.Status;
import com.cg.dao.StatusDao;


@Service
@Component("service")
public class StatusServiceImpl implements StatusService{

	@Autowired
	StatusDao dao;
	
	public StatusDao getDao() {
		return dao;
	}

	public void setDao(StatusDao dao) {
		this.dao = dao;
	}

	@Override
	public Exception addDetails(Status status) {
		// TODO Auto-generated method stub
		return dao.addDetails(status);
	}

	@Override
	public boolean isUsernameRegistered(String userName,Date statusReport) {
		// TODO Auto-generated method stub
		return dao.isUsernameRegistered(userName,statusReport);
	}

	@Override
	public Exception updateDetails(Status status) {
		// TODO Auto-generated method stub
		return dao.updateDetails(status);
	}

	@Override
	public Status updateFunction(String userName, Date statusReport1) {
		// TODO Auto-generated method stub
		return dao.updateFunction(userName,statusReport1);
	}
}