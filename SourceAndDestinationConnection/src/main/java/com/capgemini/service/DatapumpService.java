package com.capgemini.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.model.Column;
import com.capgemini.model.Connection;
import com.capgemini.model.DataPump;
import com.capgemini.model.MappingInfo;
import com.capgemini.model.Table;
@Service
public interface DatapumpService {

	public List<MappingInfo> constructQueries(DataPump datapump, List<MappingInfo> srcColumLst, int tableCount);

	public int transferData(List<MappingInfo> srcColumLst);

	public Exception sourceConnect(DataPump datapump);
	
	public Exception destinationConnect(DataPump datapump);

	public String closeConnection();

	public List columnSize(String sourceTable, String destTable, String sourceSchema, String destSchema);
	
	public java.sql.Connection sourceFunction();

	public java.sql.Connection destinationFunction();

}
