package com.example.leole.timetracker;
import java.io.Serializable;
import java.util.*;

public class Employee implements Serializable{

	private static int counter = 0;
	
	private int employeeID;
	private String employeeName;
	private String email;
	private String phoneNumber;
	private List<TimeSheet> timeSheetList;

	public Employee(){
		timeSheetList = new ArrayList<TimeSheet>();
		employeeID = counter;
		counter++;
	}
	
	//Constructor
	public Employee(String employeeName, String email, String phoneNumber) {
		timeSheetList = new ArrayList<TimeSheet>();
		this.employeeName = employeeName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		employeeID = counter;
		counter++;
	}
	
	//setters
	public void addTimeSheet(TimeSheet t){
		if(checkTimeSheet(t)) {
			timeSheetList.add(t);
		}
	}
	
	private boolean checkTimeSheet(TimeSheet t) {
		for(int i = 0; i < timeSheetList.size(); i++) {
			TimeSheet ts = timeSheetList.get(i);
			if(ts.getStartDate() == t.getStartDate() && ts.getEndDate() == t.getEndDate()) {
				return false;
			}
		}
		return true;
	}
	
	public void setName(String employeeName){
		this.employeeName = employeeName;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public void setTimeSheets(List<TimeSheet> ts) {
		this.timeSheetList = ts;
	}
	//getters
	
	//return the ID of employee
	public int getEmployeeID() {
		return employeeID;
	}
	
	//return the name of employee
	public String getEmployeeName() {
		return employeeName;
	}
	
	public String getEmail() {
		return email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	//return the list of TimeSheets
	public List<TimeSheet> getTimeSheetList(){
		return timeSheetList;
	}
	
	//return a specific TimeSheet by matching the id of that TimeSheet
	public TimeSheet getTimeSheetByID(int id){
		for(TimeSheet t : timeSheetList){
			if(t.getID() == id){
				return t;
			}
		}
		return null;
	}

	public String toString() {
		return employeeName;
	}
}
