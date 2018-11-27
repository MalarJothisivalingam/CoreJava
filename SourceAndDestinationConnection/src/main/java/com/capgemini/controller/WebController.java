package com.capgemini.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.capgemini.model.Column;
import com.capgemini.model.Connection;
import com.capgemini.model.CopyData;
import com.capgemini.model.DataPump;
import com.capgemini.model.JsonResponse;
import com.capgemini.model.MappingInfo;
import com.capgemini.model.RecordDetails;
import com.capgemini.model.Table;
import com.capgemini.service.DatapumpService;

@Controller
public class WebController {
	@Autowired
	DatapumpService service;
	CopyData copydata=new CopyData();
	private static DataPump dataPump = null;  
	private static java.sql.Connection sourceCon = null;
	private static java.sql.Connection destCon = null;
	private static Connection SrcConnect=new Connection();
	private static Connection DestConnect=new Connection();
	private static int tableCount=0;
	private static int totalRecords;
	private List<RecordDetails> details= new ArrayList<RecordDetails>();
	@RequestMapping("/database")
	public String databaseConnect(@RequestParam("selectSource")String selectSource,@RequestParam("selectDestination")String selectDestination)
	{
		String other="";
		
		if(selectSource.equals("other")&&selectDestination.equals("other"))
		{
			return "connection";
		}
		else
		{
		if(selectSource.equals("H2"))
		{		
		 SrcConnect.setDriverName("org.h2.Driver");
		 SrcConnect.setConnectionURL("jdbc:h2:tcp://localhost/~/test");
		}
		else if(selectSource.equals("Oracle"))
		{
			SrcConnect.setDriverName("");
			 SrcConnect.setConnectionURL("");
		}
		else if(selectSource.equals("MYSQL"))
		{
			SrcConnect.setDriverName("");
			 SrcConnect.setConnectionURL("");
		}
		else if(selectSource.equals("DB2"))
		{
			SrcConnect.setDriverName("");
			 SrcConnect.setConnectionURL("");
		}
		else
		{
			other="source";
		}
		if(selectDestination.equals("H2"))
		{
			
			 DestConnect.setDriverName("org.h2.Driver");
			 DestConnect.setConnectionURL("jdbc:h2:tcp://localhost/~/test");
	     }
		else if(selectDestination.equals("Oracle"))
		{
			 DestConnect.setDriverName("");
			 DestConnect.setConnectionURL("");
		}
		else if(selectDestination.equals("MYSQL"))
		{
			 DestConnect.setDriverName("");
			 DestConnect.setConnectionURL("");
		}
		else if(selectDestination.equals("DB2"))
		{
			 DestConnect.setDriverName("");
			 DestConnect.setConnectionURL("");
		}
		else
		{
			other="destination";
		}
		if(other.equals("source"))
			return "SourceConnection";
		else if(other.equals("destination"))
			return "DestinationConnection";
		else
			return "Login";
		}
		
	}
    @RequestMapping("/SourceLogin")
    public @ResponseBody JsonResponse SourceLogin(@RequestParam("userId")String userId,@RequestParam("password")String password)
    {
    	JsonResponse response = new JsonResponse();
    	SrcConnect.setUserId(userId);
		SrcConnect.setPassword(password);
		 dataPump=copydata.initSource(SrcConnect);
		 Exception error=service.sourceConnect(dataPump);
		 if(error==null)
			{
				sourceCon=service.sourceFunction();
			System.out.println("Source Connection Success");
			response.setValidated(true);
			response.setSource(dataPump.getSourceConnection());
			return response;
			}
			else
			{
				response.setValidated(false);
				response.setError(error.getMessage());
				return response;
			}
    }
    @RequestMapping("/DestinationLogin")
    public @ResponseBody JsonResponse DestinationLogin(@RequestParam("DuserId")String DuserId,@RequestParam("Dpassword")String Dpassword)
    {
    	JsonResponse response = new JsonResponse();
    	DestConnect.setUserId(DuserId);
    	DestConnect.setPassword(Dpassword);
		 dataPump=copydata.initDestination(DestConnect);
		 Exception error=service.destinationConnect(dataPump);
		 System.out.println(("msg"+error));
		 if(error==null)
			{
				destCon=service.destinationFunction();
			response.setValidated(true);
			response.setDestination(dataPump.getDestConnection());		
			System.out.println("Destination Connection Success");
			return response;
			}
			else
			{
				response.setValidated(false);
				response.setError(error.getMessage());
				return response;
			}
    }
	@RequestMapping(value = "/SourceConn")
	public @ResponseBody JsonResponse validateConnection(
			@ModelAttribute("source") Connection source, BindingResult result) {
		JsonResponse response = new JsonResponse();
		System.out.println(source);
		dataPump=copydata.initSource(source);
		Exception error=service.sourceConnect(dataPump);
		//System.out.println(error);
		if(error==null)
		{
			sourceCon=service.sourceFunction();
		System.out.println("Source Connection Success");
		response.setValidated(true);
		response.setSource(source);
		return response;
		}
		else
		{
			response.setValidated(false);
			response.setError(error.getMessage());
			return response;
		}
	}

	@RequestMapping(value = "/DestinationConn")
	public @ResponseBody JsonResponse loginDestination(
			@ModelAttribute Connection destination, BindingResult result) {
		JsonResponse response = new JsonResponse();
		System.out.println(destination);
		dataPump=copydata.initDestination(destination);
		Exception error=service.destinationConnect(dataPump);
		if(error==null)
		{
			destCon=service.destinationFunction();
		response.setValidated(true);
		response.setDestination(destination);		
		System.out.println("Destination Connection Success");
		return response;
		}
		else
		{
			response.setValidated(false);
			response.setError(error.getMessage());
			return response;
		}
	}

	@RequestMapping("/table")
	public ModelAndView table() {
  
		return new ModelAndView("table");

	}

	@RequestMapping("/alltable")
	public @ResponseBody JsonResponse table(@ModelAttribute("table") Table table,@RequestParam("value")String value[],
			@RequestParam("destColumn")String destColumn[],Model model) {
		JsonResponse response=new JsonResponse();
		response.setValidated(true);
		List<MappingInfo> srcColumLst = new ArrayList<MappingInfo>();
		MappingInfo info=new MappingInfo();
		Table[] table1=new Table[tableCount+1];
		table1[tableCount]=new Table();
		table1[tableCount].setSourceTable(table.getSourceTable());
		table1[tableCount].setDestTable(table.getDestTable());
		table1[tableCount].setForceMatch(table.getForceMatch());
		table1[tableCount].setSourceSchema(table.getSourceSchema());
		table1[tableCount].setDestSchema(table.getDestSchema());
		table1[tableCount].setClause(table.getClause());
		dataPump=copydata.initTable(table1);
		System.out.println("connection done");
		srcColumLst=copydata.MetaData(dataPump,sourceCon,destCon,tableCount);
		System.out.println("Table connection");
		Column[] column = new Column[destColumn.length];
		for(int i=0;i<destColumn.length;i++)
		{
		column[i]=new Column();
		column[i].setDestColumn(destColumn[i]);
		column[i].setValue(value[i]);
		}		
		dataPump=copydata.initColumn(column,tableCount);	
		srcColumLst=copydata.overrideColumnMatch(true,dataPump,srcColumLst,tableCount);
		srcColumLst=service.constructQueries(dataPump,srcColumLst,tableCount);
		totalRecords=service.transferData(srcColumLst);
		System.out.println("total records in cntrl"+totalRecords);
		RecordDetails rec=new RecordDetails(table.getSourceTable(),table.getDestTable(),totalRecords);
		details.add(rec);
		System.out.println("trtgr"+details);
		System.out.println("END ");
		System.out.println("Total Tables"+dataPump.getTables().length);
		tableCount++;
		//return new ModelAndView("table");
		return response;
	}
	@RequestMapping("/closeConnection")
	public ModelAndView closeFunction()
	{
		ModelAndView model=new ModelAndView("success");
		model.addObject("totalRecords", details);
		System.out.println(details);
		service.closeConnection();
		tableCount=0;
		return model;
	}
	@RequestMapping("/column")
	public @ResponseBody JsonResponse column(@ModelAttribute("table") Table table) {
		JsonResponse response=new JsonResponse();
		response.setValidated(true);
		List<String> list= new ArrayList<String>();
		String sourceTable=table.getSourceTable();
		String destTable=table.getDestTable();
		String sourceSchema=table.getSourceSchema();
		String destSchema=table.getDestSchema();
		System.out.println("enter");
		list=service.columnSize(sourceTable,destTable,sourceSchema,destSchema);
		System.out.println("size of column"+list.size());
		response.setColName(list);
		return response;
	}
}
