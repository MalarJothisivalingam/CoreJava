package com.capgemini.model;

import java.util.Arrays;

public class DataPump {
	private Connection sourceConnection;
	  private Connection destConnection;
	  private Table[] Tables;
	  
	  public Connection getSourceConnection()
	  {
	    return this.sourceConnection;
	  }
	  
	  public void setSourceConnection(Connection sourceConnection)
	  {
	    this.sourceConnection = sourceConnection;
	  }
	  
	  public Connection getDestConnection()
	  {
	    return this.destConnection;
	  }
	  
	  public void setDestConnection(Connection destConnection)
	  {
	    this.destConnection = destConnection;
	  }

	public Table[] getTables() {
		return Tables;
	}

	public void setTables(Table[] table) {
		Tables = table;
	}

	@Override
	public String toString() {
		return "DataPump [sourceConnection=" + sourceConnection
				+ ", destConnection=" + destConnection + ", Tables="
				+ Arrays.toString(Tables) + "]";
	}

	


	 
}
