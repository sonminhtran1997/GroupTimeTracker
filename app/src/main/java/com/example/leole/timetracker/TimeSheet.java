package com.example.leole.timetracker;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TimeSheet implements Serializable{

	private static int counter = 0;
	
	private List<Shift> employeeShifts;
	private Date startDate;
	private Date endDate;
	private int currentHours;
	private int maxHours;
	private int id;
	private boolean checkDate = false;

	public TimeSheet(){
		this.employeeShifts = new ArrayList<Shift>();
	}
	public TimeSheet(String startDate, String endDate, int maxHours) {
		
		this.employeeShifts = new ArrayList<Shift>();
		this.startDate = new Date(startDate);
		this.endDate = new Date(endDate);
		if(this.startDate.compareTo(this.endDate) < 0){
			checkDate = true;
		}
		this.maxHours = maxHours;
		this.id = counter;
		counter++;
	}

	public boolean isCheckDate() {
		return checkDate;
	}

	public void setCheckDate(boolean checkDate) {
		this.checkDate = checkDate;
	}

	public int addShift(Shift s) {
		TimeOfDay begin = new TimeOfDay(s.getStartTime());
		TimeOfDay end = new TimeOfDay(s.getEndTime());
		
		TimeOfDay result = end.subtract(begin);
		
		Date startingDate = new Date(s.getStartDate());
		
		if(result.getHours() + currentHours > maxHours || result.getHours() + 
				currentHours == maxHours && result.getMinutes() > 0) {
			return 1;
		} else if(startingDate.compareTo(this.startDate) < 0 || startingDate.compareTo(this.endDate) >= 0) {
			return 2;
		} else if(!s.checkRep()) {
			return 3;
		} else if(!noConflict(s)){
			return 4;
		} else {
			employeeShifts.add(s);
			return 0;
		}
	}
	
	private boolean noConflict(Shift s1) {
		for(int i = 0; i < this.employeeShifts.size(); i++) {
			Shift s2 = this.employeeShifts.get(i);
			if(s1.hasOverlap(s2) || s1.getID() == s2.getID()) {
				return false;
			}
		}
		return true;
	}
	
	public List<Shift> getEmployeeShifts() {
		// probably should return a defensive copy here...
		return this.employeeShifts;
	}
	public void setEmployeeShifts(List<Shift> newShifts) {
		this.employeeShifts = newShifts;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public int getCurrentHours() {
		return currentHours;
	}
	
	public int getMaxHours() {
		return maxHours;
	}
	
	public int getID(){
		return this.id;
	}
	
	public Shift getShiftByID(int id){
		for(Shift s:employeeShifts){
			if(s.getID()==id){
				return s;
			}
		}
		return null;
	}
	@Override
	public String toString() {
		return "From " + startDate + " to " + endDate;
	}
}
