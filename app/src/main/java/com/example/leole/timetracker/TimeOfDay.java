package com.example.leole.timetracker;
public class TimeOfDay implements Comparable<TimeOfDay>{

	private int hours;
	private int minutes;
	
	// input expected to be in hh:mm format
	public TimeOfDay(String hoursAndMinutes) {
		String[] data = hoursAndMinutes.split(":");
		
		if(data.length != 2) {
			throw new IllegalArgumentException();
		}
		
		this.hours = Integer.parseInt(data[0]);
		this.minutes = Integer.parseInt(data[1]);
		checkRep();
	}
	
	public void checkRep() {
		assert (hours >= 0 && hours < 24);
		assert (minutes >= 0 && minutes < 60);
	}
	
	public int getHours() {
		return hours;
	}
	
	public int getMinutes() {
		return minutes;
	}
	
	public String toString() {
		return hours + ":" + minutes;
	}
	public TimeOfDay subtract(TimeOfDay other) {
		if(other.hours > this.hours || (other.hours == this.hours && other.minutes > this.minutes)) {
			throw new IllegalArgumentException("Not subtracting right amounts");
		}
		int minutesDiff = this.minutes - other.minutes;
		int hoursDiff = this.hours - other.hours;
		
		if(minutesDiff < 0) {
			minutesDiff += 60;
			hoursDiff--;
		}
		return new TimeOfDay(hoursDiff + ":" + minutesDiff);
	}
	public static void main(String[] args) {
		TimeOfDay day1 = new TimeOfDay("01:44");
		TimeOfDay day2 = new TimeOfDay("00:45");
		// I think it works!  Test here
		System.out.println(day1.subtract(day2));
		
	}

	@Override
	public int compareTo(TimeOfDay arg0) {
		// TODO Auto-generated method stub
		if(this.hours > arg0.hours) {
			return 1;
		} else if(this.hours < arg0.hours) {
			return -1;
		} else if(this.minutes > arg0.minutes) {
			return 1;
		} else if(this.minutes < arg0.minutes) {
			return -1;
		} else {
			return 0;
		}
	}
	
}