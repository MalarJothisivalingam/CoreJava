package com.cg.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cg.bean.JsonResponse;
import com.cg.bean.Status;
import com.cg.service.StatusService;

@Controller
public class StatusCtrl {
	Status status = new Status();
	@Autowired
	StatusService service;

	@RequestMapping(value = "/value", method = RequestMethod.POST)
	public ModelAndView table(@RequestParam("userName") String userName,
			@RequestParam("statusReport") String statusReport)
			throws ParseException {
		ModelAndView model = new ModelAndView("source");
		model.addObject("userName", userName);
		model.addObject("statusReport", statusReport);
		                      /***Converting string to date***/
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date date = sdf1.parse(statusReport);
		java.sql.Date statusReport1 = new java.sql.Date(date.getTime());
		boolean user = service.isUsernameRegistered(userName, statusReport1);
		if (user == true) {
			status = service.updateFunction(userName, statusReport1);
			ModelAndView model1 = new ModelAndView("update");
			model1.addObject("userName", userName);
			model1.addObject("statusReport", statusReport);
			model1.addObject("CurrentWeekStatus", status.getCurrentWeekStatus());
			model1.addObject("NextWeekStatus", status.getNextWeekStatus());
			model1.addObject("IssueNote", status.getIssueNote());
			return model1;
		} else {
			//System.out.println(user);
			return model;
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody JsonResponse addDetails(
			@RequestParam("userName") String userName,
			@RequestParam("statusReport") String statusReport,
			@RequestParam("currentWeekStatus") String currentWeekStatus,
			@RequestParam("nextWeekStatus") String nextWeekStatus,
			@RequestParam("issueNote") String issueNote,
			@RequestParam("timeStamp") String timeStamp) throws ParseException {

		JsonResponse response = new JsonResponse();
		Status status = new Status();
		status.setUserName(userName);
		                 /***converting String to Date***/
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date date = sdf1.parse(statusReport);
		java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
	
		status.setStatusReport(sqlStartDate);
		status.setCurrentWeekStatus(currentWeekStatus);
		status.setIssueNote(issueNote);
		status.setNextWeekStatus(nextWeekStatus);
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		java.util.Date dat = formatter.parse(timeStamp);
		java.sql.Timestamp timestamp = new java.sql.Timestamp(dat.getTime());
		status.setTimeStamp(timestamp);
		
		Exception error = service.addDetails(status);
		if (error == null) {
			response.setValidated(true);
			return response;
		} else {
			response.setValidated(false);
			return response;
		}
	}

	@RequestMapping(value = "/update")
	public @ResponseBody JsonResponse updateDetails(
			@RequestParam("userName") String userName,
			@RequestParam("statusReport") String statusReport,
			@RequestParam("currentWeekStatus") String currentWeekStatus,
			@RequestParam("nextWeekStatus") String nextWeekStatus,
			@RequestParam("issueNote") String issueNote,
			@RequestParam("timeStamp") String timeStamp) throws ParseException {
	
		JsonResponse response = new JsonResponse();
		Status status = new Status();
		status.setUserName(userName);
		                          /***converting String to Date***/
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date date = sdf1.parse(statusReport);
		java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
	
		status.setStatusReport(sqlStartDate);
		status.setCurrentWeekStatus(currentWeekStatus);
		status.setIssueNote(issueNote);
		status.setNextWeekStatus(nextWeekStatus);
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		java.util.Date dat = formatter.parse(timeStamp);
		java.sql.Timestamp timestamp = new java.sql.Timestamp(dat.getTime());
		status.setTimeStamp(timestamp);
		
		Exception error = service.updateDetails(status);
		if (error == null) {
			response.setValidated(true);
			return response;
		} else {
			response.setValidated(false);
			return response;
		}

	}
}
