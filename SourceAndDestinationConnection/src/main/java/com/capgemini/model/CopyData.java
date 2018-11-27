package com.capgemini.model;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CopyData
{
	DataPump dataPump=new DataPump();
	private List<MappingInfo> srcColumLst = new ArrayList<MappingInfo>();
	public DataPump initSource(Connection source) {
		dataPump.setSourceConnection(source);
		return dataPump;
	}
	public DataPump initDestination(Connection destination) {
		dataPump.setDestConnection(destination);return dataPump;
		
	}
	public DataPump initTable(Table[] table) {

		dataPump.setTables(table);return dataPump;
		
	}  
	public DataPump initColumn(Column[] column,int tableCount) {
		dataPump.getTables()[tableCount].setColumns(column);
		return dataPump;
	}

public List MetaData(DataPump datapump, java.sql.Connection sourceCon, java.sql.Connection destCon, int tableCount) {
	System.out.println("MetaData");
	CopyData data=new CopyData();
	if (datapump.getTables()[tableCount].getForceMatch().equals("true")) {
		System.out.println("inside");
		srcColumLst=data.populateMetaData(datapump,sourceCon,destCon,tableCount);
	    }

	System.out.println("in method"+srcColumLst);
	return srcColumLst;
	
}
private List<MappingInfo> populateMetaData(DataPump datapump, java.sql.Connection sourceCon, java.sql.Connection destCon, int tableCount) {
	 fetchMetaData(sourceCon, datapump.getTables()[tableCount].getSourceSchema(), datapump.getTables()[tableCount].getSourceTable(), false);
	    
	    fetchMetaData(destCon, datapump.getTables()[tableCount].getDestSchema(), datapump.getTables()[tableCount].getDestTable(), true);
		return srcColumLst;
	
}
private void fetchMetaData(java.sql.Connection con, String schema, String table, boolean flag)
{
  try
  {
    DatabaseMetaData md = con.getMetaData();
    ResultSet rs = md.getColumns(null, schema.toUpperCase(), table.toUpperCase(), "%");
    while (rs.next())
    {
      MappingInfo mapping = new MappingInfo();
      if (!flag) {
        mapping.setSrcColumn(rs.getString(4));//set column name       
      } else {
        mapping.setDestColumn(rs.getString(4));
      }
      mapping.setType(rs.getString(6));
      if (rs.getString(18).equals("NO")) {
        mapping.setNull(false);
        
      } else {
        mapping.setNull(true);
      }
      if (!flag) {
        this.srcColumLst.add(mapping);
      } else {
        populateMappingInfo(mapping);
      }
    }
  }
  catch (Exception e)
  {
    System.err.print("Error: " + e.getMessage());
  }
}
private void populateMappingInfo(MappingInfo mappingInfo)
{
  String col = mappingInfo.getDestColumn();
  List<MappingInfo> list = new ArrayList<MappingInfo>();
  for (MappingInfo info : this.srcColumLst) {
    if (info.getSrcColumn().equals(col))
    {
      info.setDestColumn(col);
      info.setMapped(true);
      break;
    }
  }
 // this.srcColumLst.addAll(list);
}
public List<MappingInfo> overrideColumnMatch(boolean usecolumn, DataPump dataPump, List<MappingInfo> srcColumLst, int tableCount)
{
    List list = new ArrayList();
    System.out.println("override");
    if(usecolumn && dataPump.getTables()[tableCount].getForceMatch().equals("true") && dataPump.getTables()[tableCount].getColumns().length > 0)
    {
        for(Iterator iterator = srcColumLst.iterator(); iterator.hasNext();)
        {
        	
            MappingInfo info = (MappingInfo)iterator.next();
            Column acolumn1[];
            int l = (acolumn1 = dataPump.getTables()[tableCount].getColumns()).length;
           
            for(int k = 0; k < l; k++)
            {
                Column column = acolumn1[k];
                if(column.getSourceColumn() != null && !column.getSourceColumn().equals(""))
                {
                    if(!info.getSrcColumn().equals(column.getSourceColumn()))
                        continue;
                    info.setDestColumn(column.getDestColumn());
                    info.setMapped(true);
                    info.setOverride(true);
                    break;
                }
                if(column.getValue() == null || getInfo(column.getDestColumn(), list) != null)
                    continue;
                MappingInfo info2 = new MappingInfo();
                info2.setDestColumn(column.getDestColumn());
                info2.setSrcValue(column.getValue());
                info2.setMapped(true);
                info2.setOverride(true);
                list.add(info2);
                break;
            }

        }

        srcColumLst.addAll(list);
       removeAllDuplicates();
    } else if(usecolumn)
    {
        Column acolumn[];
        int j = (acolumn = dataPump.getTables()[tableCount].getColumns()).length;
        for(int i = 0; i < j; i++)
        {
            Column column = acolumn[i];
            if(column.getSourceColumn() != null && !column.getSourceColumn().equals(""))
            {
                MappingInfo info = new MappingInfo();
                info.setSrcColumn(column.getSourceColumn());
                info.setDestColumn(column.getDestColumn());
                info.setMapped(true);
                info.setOverride(true);
                srcColumLst.add(info);
            } else
            if(column.getValue() != null)
            {
                MappingInfo info2 = new MappingInfo();
                info2.setDestColumn(column.getDestColumn());
                info2.setSrcValue(column.getValue());
                info2.setMapped(true);
                info2.setOverride(true);
                list.add(info2);
            }
        }

        srcColumLst.addAll(list);
    }
    return srcColumLst;
}
private void removeAllDuplicates()
{
  List<MappingInfo> list = new ArrayList();
  for (MappingInfo info : this.srcColumLst) {
    if (info.isOverride()) {
      for (MappingInfo info1 : this.srcColumLst) {
        if (info.getSrcColumn() != null)
        {
          if ((info.getDestColumn().equals(info1.getDestColumn())) && (!info1.isOverride())) {
            list.add(info1);
          } else if ((info.getSrcColumn().equals(info1.getSrcColumn())) && (!info1.isOverride())) {
            list.add(info1);
          }
        }
        else if ((info.getDestColumn().equals(info1.getDestColumn())) && (!info1.isOverride())) {
          list.add(info1);
        }
      }
    }
  }
  this.srcColumLst.removeAll(list);
}
private MappingInfo getInfo(String destColm, List<MappingInfo> lst)
{
  for (MappingInfo info : lst) {
    if (info.getDestColumn().equals(destColm)) {
      return info;
    }
  }
  return null;
}
  }
