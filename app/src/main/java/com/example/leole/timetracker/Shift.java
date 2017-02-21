package com.example.leole.timetracker;

import java.io.Serializable;

public class Shift implements Serializable{
	
	private static int counter = 0;

	private Date startDate;
	private String startTime;
	private String endTime;
	private String note;
	private int breakTime;
	private int id;

	public Shift(){
		this.id = counter;
		counter++;
	}
	//Constructor
	public Shift(String startDate, String startTime, String endTime, String note, int breakTime) {
		this.startDate = new Date(startDate);
		this.startTime = startTime;
		this.endTime = endTime;
		this.note = note;
		this.breakTime = breakTime;
		this.id = counter;
		counter++;
	}
	
	//check if the endTime is actually after the startTime
	//return false if the endTime is after the startTime
	//return true if the endTime is before the startTime
	public boolean checkRep() {
		boolean returnBool = true;
		TimeOfDay start = new TimeOfDay(startTime);
		TimeOfDay end = new TimeOfDay(endTime);
		if(start.compareTo(end) >= 0) {
			returnBool = false;
		}
		return returnBool;
	}
	
	
	//compare if this time shift has overlap with the other time shift 
	public boolean hasOverlap(Shift other) {
		
		// might not work if the date is not the same, and there is overlap between days.  Will
		// explain if you ask
		if(this.startDate.compareTo(other.startDate) == 0) {
			TimeOfDay thisStart = new TimeOfDay(this.startTime);
			TimeOfDay thisEnd = new TimeOfDay(this.endTime);
			
			TimeOfDay otherStart = new TimeOfDay(other.startTime);
			TimeOfDay otherEnd = new TimeOfDay(other.endTime);
			
			if(thisStart.compareTo(otherStart)>=0 && thisStart.compareTo(otherEnd)<0){
				return true;
			}
			
			if(otherStart.compareTo(thisStart)>=0 && otherStart.compareTo(thisEnd)<0){
				return true;
			}
		}
		return false;
	}
	
	//setter
	
	public void setDate(String startDate) {
		this.startDate = new Date(startDate);
	}
	
	public void setStartTime(String startTime){
		this.startTime = startTime;

	}
	
	public void setEndTime(String endTime){
		this.endTime = endTime;

	}
	
	public void setBreakTime(int breakTime){
		this.breakTime = breakTime;
	}
	
	public void setNote(String note){
		this.note = note;
	}
	
	//getter
	
	public int getID() {
		return this.id;
	}
	public String getStartDate() {
		return startDate.toString();
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	public int getBreakTime() {
		return breakTime;
	}
	
	public String getNote(){
		return note;
	}
	public String toString() {
		return String.format("%s %s-%s %d\n%s",getStartDate(),getStartTime(),getEndTime(),getBreakTime(),getNote());
	}
	
	public static void main(String[] args){
		Shift test = new Shift("02/01/2017","11:00","16:00","this employee sucks",30);
		System.out.println(test);
	}
	
}
