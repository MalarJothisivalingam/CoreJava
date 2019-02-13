package com.capgemini.model;

import java.util.List;


public class JsonResponse {
	private Connection source;
	private Connection destination;
	private Table table;
	private boolean validated;
	private List<String> colName;
	private String error;
	
	public boolean isValidated() {
		return validated;
	}
	public void setValidated(boolean validated) {
		this.validated = validated;
	}
	public Connection getSource() {
		return source;
	}
	public void setSource(Connection source) {
		this.source = source;
	}
	public Connection getDestination() {
		return destination;
	}
	public void setDestination(Connection destination) {
		this.destination = destination;
	}
	public Table getTable() {
		return table;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	public List<String> getColName() {
		return colName;
	}
	public void setColName(List<String> colName) {
		this.colName = colName;
	}
	public String getError() {
		return error;
	}
	public void setError(String string) {
		this.error = string;
	}
	
	
	
	
}
