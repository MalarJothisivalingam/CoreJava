package com.capgemini.dao;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.capgemini.model.Connection;
import com.capgemini.model.DataPump;
import com.capgemini.model.MappingInfo;
import com.capgemini.model.Table;

@Repository
@Component("dao")
public class DatapumpDaoImpl implements DatapumpDao {
	private static String srcQuery = "";
	private static String destQuery = "";
	private int count = 0;
	private List<MappingInfo> srcColumLst = new ArrayList<MappingInfo>();
	private static java.sql.Connection sourceCon = null;
	private static java.sql.Connection destCon = null;

	@Override
	public Exception sourceConnect(DataPump datapump) {

		Connection srcCon = datapump.getSourceConnection();
		System.out.println(srcCon);
		try {
			Class.forName(srcCon.getDriverName());
			sourceCon = DriverManager.getConnection(srcCon.getConnectionURL(),
					srcCon.getUserId(), srcCon.getPassword());
		} catch (Exception e) {

			System.err.print("Error: " + e.getMessage());
			return e;
		}
		return null;
	}
	@Override
	public Exception destinationConnect(DataPump datapump) {
		Connection detCon = datapump.getDestConnection();
		try {
			Class.forName(detCon.getDriverName());
			destCon = DriverManager.getConnection(detCon.getConnectionURL(),
					detCon.getUserId(), detCon.getPassword());
		} catch (Exception e) {

			System.err.print("Error: " + e.getMessage());
			return e;
		}
		return null;
	}
	@Override
	public java.sql.Connection sourceFunction() {
		return sourceCon;
	}

	@Override
	public java.sql.Connection destinationFunction() {
		return destCon;
	}
	@Override
	public List<MappingInfo> constructQueries(DataPump datapump,
			List<MappingInfo> srcColumLst,int tableCount) {
		count=0;
		System.out.println(srcColumLst);
		StringBuffer srcBuffer = new StringBuffer("Select ");
		StringBuffer dstBuffer = new StringBuffer("Insert into "
				+ datapump.getTables()[tableCount].getDestSchema() + "."
				+ datapump.getTables()[tableCount].getDestTable() + " ( ");
		StringBuffer tmpHolder = new StringBuffer();
		List<MappingInfo> tmpLst = new ArrayList<MappingInfo>();
		for (MappingInfo info : srcColumLst) {
			if (info.isMapped()) {
				if (this.count != 0) {
					if (info.getSrcColumn() != null) {
						srcBuffer.append(" ,");

					}
					if (!info.isCombineInserts()) {
						dstBuffer.append(" ,");
						tmpHolder.append(" ,");
					}
				}
				if (info.getSrcColumn() != null) {
					srcBuffer.append(info.getSrcColumn());
				}
				if (!info.isCombineInserts()) {
					dstBuffer.append(info.getDestColumn());
					tmpHolder.append(" ? ");
				}
				this.count += 1;
			} else {
				tmpLst.add(info);
			}
		}
		this.srcColumLst.remove(tmpLst);

		srcBuffer.append(" from ")
				.append(datapump.getTables()[tableCount].getSourceSchema()).append(".")
				.append(datapump.getTables()[tableCount].getSourceTable()).append(" ");
		if (datapump.getTables()[tableCount].getClause() != null) {
			srcBuffer.append(datapump.getTables()[tableCount].getClause());
		}

		dstBuffer.append(" ) values ( ").append(tmpHolder).append(" )");
		srcQuery = srcBuffer.toString();
		destQuery = dstBuffer.toString();
		System.out.println(srcQuery);
		System.out.println(destQuery);
		return srcColumLst;
	}

	@Override
	public int transferData(List<MappingInfo> srcColumLst) {
		int flushCount = 0;
		int totalCount = 0;
		try {
			List<MappingInfo> tmpLst = null;
			MappingInfo mappingInfo = null;
			PreparedStatement srcPstmt = sourceCon.prepareStatement(srcQuery);
			PreparedStatement destPstmt = destCon.prepareStatement(destQuery);
			
			ResultSet srcRS = srcPstmt.executeQuery();
			while (srcRS.next()) {
				tmpLst = new ArrayList<MappingInfo>();

				for (MappingInfo info : srcColumLst) {
					if (info.getSrcColumn() != null) {

						if (info.isCombineInserts()) {
							mappingInfo = getInfo(info.getDestColumn(), tmpLst);
							if (mappingInfo == null) {
								mappingInfo = new MappingInfo();
								mappingInfo.setDestColumn(info.getDestColumn());
								mappingInfo.setSrcValue(srcRS.getObject(info
										.getSrcColumn()));
								tmpLst.add(mappingInfo);
							} else {
								mappingInfo.setSrcValue((new StringBuilder())
										.append(mappingInfo.getSrcValue())
										.append(srcRS.getObject(info
												.getSrcColumn())).toString());
							}
						} else {
							mappingInfo = new MappingInfo();
							mappingInfo.setDestColumn(info.getDestColumn());
							mappingInfo.setSrcValue(srcRS.getObject(info
									.getSrcColumn()));
							tmpLst.add(mappingInfo);
						}
					} else {
						tmpLst.add(info);
					}
				}
				for (int i = 1; i <= tmpLst.size(); i++) {
					destPstmt.setObject(i,
							((MappingInfo) tmpLst.get(i - 1)).getSrcValue());
				}
				try {
					destPstmt.execute();
					flushCount++;
					totalCount++;
				} catch (Exception e) {
					System.err.print("Warn: " + e.getMessage());
				}
				if (flushCount > 2000) {
					// flushConnection();
					destPstmt = destCon.prepareStatement(destQuery);
					flushCount = 0;
				}
			}
			System.out.println("\nTotal Inserted Records: " + totalCount);
		} catch (Exception e) {
			System.err.print("Error: " + e.getMessage());
		}
		return totalCount;
	}

	private MappingInfo getInfo(String destColm, List<MappingInfo> lst) {
		for (MappingInfo info : lst) {
			if (info.getDestColumn().equals(destColm)) {
				return info;
			}
		}
		return null;
	}
	@Override
	public String closeConnection() {
		 try
		    {
		      sourceCon.close();
		      destCon.close();
		    }
		    catch (Exception e)
		    {
		      System.err.print("Error: " + e.getMessage());
		    }
		return null;
	}

	@Override
	public List columnSize(String sourceTable, String destTable,
			String sourceSchema, String destSchema) {
		List<String>srclist=new ArrayList<String>();
		List<String>destlist=new ArrayList<String>();
		try
		{
		
		DatabaseMetaData md = sourceCon.getMetaData();
	
	    ResultSet rs = md.getColumns(null, sourceSchema.toUpperCase(), sourceTable.toUpperCase(), "%");
	    while (rs.next())
	    {
	    	srclist.add(rs.getString(4));
	        
	    	
	    }System.out.println(srclist);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		try
		{
		
		DatabaseMetaData md = destCon.getMetaData();
	
	    ResultSet rs = md.getColumns(null, destSchema.toUpperCase(), destTable.toUpperCase(), "%");
	    while (rs.next())
	    {
	    	destlist.add(rs.getString(4));
	    	
	    }
	    System.out.println(destlist);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		 ArrayList<String> list= new ArrayList<String>();
         for (String temp : destlist)
         {
        	 if(srclist.contains(temp))
        		 continue;
        	 else
        		 list.add(temp);
         }
         System.out.println(list);
		return list;
	}
}
