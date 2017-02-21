package com.example.leole.timetracker;
import java.io.Serializable;
import java.util.*;

public class Manager implements Serializable {
	private List<Employee> employeeList;
	private transient static Manager manager;

	public static Manager getManager(){
		if(manager == null){
			manager = new Manager();
		}
		return manager;
	}
	public static Manager setManager(Manager m){
		if(m == null){
			return m;
		} else {
			manager = m;
		}
		return manager;

	}
	public Manager(){
		employeeList = new ArrayList<>();
	}
	
	public void addEmployee(Employee e){
		if(employeeList == null){
			employeeList = new ArrayList<>();
		}
		if(e != null){
			employeeList.add(e);
		}

	}
	public void setEmployeeList(List<Employee> empList) {
		this.employeeList = empList;
	}
	
	public List<Employee> getEmployeeList(){
		return employeeList;
	}
	
	public Employee getEmployeeByID(int ID){
		for(Employee e:employeeList){
			if(e.getEmployeeID() == ID){
				return e;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Manager{" +
				"employeeList=" + employeeList +
				'}';
	}
}
