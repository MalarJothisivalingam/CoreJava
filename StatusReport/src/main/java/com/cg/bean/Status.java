package com.cg.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class Status {

	private Date statusReport;
	private String userName;
	private String currentWeekStatus;
	private String nextWeekStatus;
	private String issueNote;
	private Timestamp timeStamp;
	public Date getStatusReport() {
		return statusReport;
	}
	public void setStatusReport(Date statusReport) {
		this.statusReport = statusReport;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCurrentWeekStatus() {
		return currentWeekStatus;
	}
	public void setCurrentWeekStatus(String currentWeekStatus) {
		this.currentWeekStatus = currentWeekStatus;
	}
	public String getNextWeekStatus() {
		return nextWeekStatus;
	}
	public void setNextWeekStatus(String nextWeekStatus) {
		this.nextWeekStatus = nextWeekStatus;
	}
	public String getIssueNote() {
		return issueNote;
	}
	public void setIssueNote(String issueNote) {
		this.issueNote = issueNote;
	}


	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	@Override
	public String toString() {
		return "Status [statusReport=" + statusReport + ", userName="
				+ userName+ ", currentWeekStatus=" + currentWeekStatus
				+ ", nextWeekStatus=" + nextWeekStatus + ", issueNote="
				+ issueNote + ", timeStamp=" + timeStamp + "]";
	}
	

}
