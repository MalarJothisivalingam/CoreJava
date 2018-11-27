package com.capgemini.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.capgemini.dao.DatapumpDao;
import com.capgemini.model.Column;
import com.capgemini.model.Connection;
import com.capgemini.model.DataPump;
import com.capgemini.model.MappingInfo;
import com.capgemini.model.Table;
@Service
@Component("service")
public class DatapumpServiceImpl implements DatapumpService{

	@Autowired
	DatapumpDao dao;

	@Override
	public List<MappingInfo> constructQueries(DataPump datapump,
			List<MappingInfo> srcColumLst,int tableCount) {
				return dao.constructQueries(datapump, srcColumLst,tableCount);

	}

	
	@Override
	public int transferData(List<MappingInfo> srcColumLst) {
		return dao.transferData(srcColumLst);
		
	}


	@Override
	public String closeConnection() {
		return dao.closeConnection();
		
	}


	@Override
	public List columnSize(String sourceTable, String destTable,
			String sourceSchema, String destSchema) {
		// TODO Auto-generated method stub
		return dao.columnSize(sourceTable, destTable, sourceSchema, destSchema);
	}


	@Override
	public Exception sourceConnect(DataPump datapump) {
		return dao.sourceConnect(datapump);
	}


	@Override
	public Exception destinationConnect(DataPump datapump) {
		return dao.destinationConnect(datapump);
	}


	@Override
	public java.sql.Connection sourceFunction() {
		return dao.sourceFunction();
	}


	@Override
	public java.sql.Connection destinationFunction() {
		return dao.destinationFunction();
	}


	

}
