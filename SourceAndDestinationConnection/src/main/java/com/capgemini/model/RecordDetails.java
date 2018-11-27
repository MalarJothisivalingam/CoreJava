package com.capgemini.model;

public class RecordDetails {
	String srcTable;
	String destTable;
	int records;
	
	public String getSrcTable() {
		return srcTable;
	}
	public void setSrcTable(String srcTable) {
		this.srcTable = srcTable;
	}
	public String getDestTable() {
		return destTable;
	}
	public void setDestTable(String destTable) {
		this.destTable = destTable;
	}
	public int getRecords() {
		return records;
	}
	public void setRecords(int records) {
		this.records = records;
	}
	public RecordDetails(String srcTable, String destTable, int records) {
		super();
		this.srcTable = srcTable;
		this.destTable = destTable;
		this.records = records;
	}
	@Override
	public String toString() {
		return "RecordDetails [srcTable=" + srcTable + ", destTable="
				+ destTable + ", records=" + records + "]";
	}
	
}
