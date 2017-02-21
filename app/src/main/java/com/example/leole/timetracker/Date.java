package com.example.leole.timetracker;
public class Date implements Comparable<Date> {
	private int day,month,year;
	//constructors
	public Date(){
		this(2000,1,1);
	}
	public Date(int mm, int dd, int yy){
		setDaysTo(mm,dd,yy);
	}
	
	public Date(String s){	
		
		String[] dateData = s.split("/");
		
		setDaysTo(Integer.parseInt(dateData[0]), Integer.parseInt(dateData[1]),
				Integer.parseInt(dateData[2]));
	}
	//getters
	public String toString(){
		String result=new String("");
		
		if(month<10) result+="0";
		result += month + "/";
		if(day<10) result+="0";
		result += day + "/";
		int iy = year;
		while(iy<1000){
			result += "0";
			iy*=10;
		}
		result += year;

		return result;
	}
	
	
	
	public boolean isLeapYear(){
		return isLeapYear(year);
	}
	
	public int getDay(){
		return day;
	}
	public int getMonth(){
		return month;
	}
	public int getYear(){
		return year;
	}
	
	//setters
	public void reset(){
		setDaysTo(2000,1,1);
	}
	
	public void setDaysTo(int m,int d,int y){
		if(!exist(m,d,y)){
			throw new IllegalArgumentException();
		}
		year = y;
		month = m;
		day = d; 
	}
	
	public void addDays(int d){
		day += d;
		cleanUp();
	}
	public void addWeeks(int w){
		addDays(7*w);
	}
	
//	public int differencesInDays(Date other){
//		Date temp = new Date(this.getYear(),this.getMonth(),this.getDay());
//		int counter = 0;
//		while(!temp.toString().equals(other.toString())){
//			temp.addDays(1);
//			counter ++;
//		}
//		return counter;
//	}	
	
	public int differencesInDays(Date other){
		int counter = 0;
		int i;
		for(i=this.year;i<other.year;i++){
			counter += 365;
			if(isLeapYear(i)) counter++;
		}
		counter -= new Date(this.getYear(),1,1).sameYearDifferences(this);
		counter += new Date(other.getYear(),1,1).sameYearDifferences(other);
		return counter;
	}
	
	public int sameYearDifferences(Date other){
		int counter = 0;
		int i;
		for(i=this.month;i<other.month;i++){
			counter += Date.numberOfDays(this.year, i);
		}
		counter+= other.day-this.day;
		return counter;
	}
	
	//private stuff
	private void cleanUp(){
		while(!exist(year,month,day)){
			day -= numberOfDays(year,month);
			month++;
			if(month>12){
				month=1;
				year++;
			}
		}
	}
	
	//static methods
	
	
	public int compareTo(Date other){
		if(this.year != other.year){
			return this.year - other.year;
		}else if(this.month != other.month){
			return this.month - other.month;
		}else return this.day - other.day;
	}
	
	public static boolean exist(int mm,int dd,int yy){
		if(yy<1 || mm<1 || dd<1 || mm>12){
			return false;
		}
		if(dd>numberOfDays(yy,mm)){
			return false;
		}
		return true;
	}
	
	public static boolean isLeapYear(int yy){
		boolean flag = false;
		if(yy % 4 == 0){
			flag = true;
			if(yy % 100 == 0){
				flag = false;
				if(yy % 400 == 0){
					flag = true;
				}
			}
		}
		return flag;
	}
	public static int numberOfDays(int yy,int mm){
		int[] m={29,31,28,31,30,31,30,31,31,30,31,30,31};
		if(mm == 2 && isLeapYear(yy)){
			return m[0];
		}else{
			return m[mm];
		}
	}
}