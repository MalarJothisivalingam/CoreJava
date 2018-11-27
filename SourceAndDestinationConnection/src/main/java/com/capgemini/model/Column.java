package com.capgemini.model;

import java.util.List;

public class Column {
	private String sourceColumn;
	  private String destColumn;
	  private String value;
	public String getSourceColumn() {
		return sourceColumn;
	}
	public void setSourceColumn(String sourceColumn) {
		this.sourceColumn = sourceColumn;
	}
	public String getDestColumn() {
		return destColumn;
	}
	public void setDestColumn(String destColumn) {
		this.destColumn = destColumn;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Column [sourceColumn=" + sourceColumn + ", destColumn="
				+ destColumn + ", value=" + value + "]";
	}

	  
	

}
