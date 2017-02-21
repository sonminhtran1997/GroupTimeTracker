package com.example.leole.timetracker;

import android.content.res.AssetManager;

import java.io.Serializable;
import java.lang.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.json.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JSonWriter implements Serializable{

    Manager m;

    public JSonWriter(Manager m) {
        this.m = m;

    }

    public JSONObject writeToJSon() throws JSONException, IOException {
        JSONObject managerObj = new JSONObject();
        JSONArray employees = new JSONArray();

        for (int i = 0; i < m.getEmployeeList().size(); i++) {

            JSONObject employeeObj = new JSONObject();
            Employee e = m.getEmployeeList().get(i);
            employeeObj.put("Employee ID", e.getEmployeeID());
            employeeObj.put("Employee Name", e.getEmployeeName());
            employeeObj.put("Email", e.getEmail());
            employeeObj.put("Phone Number", e.getPhoneNumber());
            JSONArray timeSheets = new JSONArray();

            for (int j = 0; j < e.getTimeSheetList().size(); j++) {

                JSONObject timeSheetObj = new JSONObject();
                TimeSheet t = e.getTimeSheetList().get(j);
                timeSheetObj.put("TimeSheet Start Date", t.getStartDate().toString());
                timeSheetObj.put("TimeSheet End Date", t.getEndDate().toString());
                timeSheetObj.put("Max Hours", String.valueOf(t.getMaxHours()));
                timeSheetObj.put("Current Hours", String.valueOf(t.getCurrentHours()));
                JSONArray employeeShifts = new JSONArray();

                for (int k = 0; k < t.getEmployeeShifts().size(); k++) {

                    JSONObject shiftObj = new JSONObject();
                    Shift s = t.getEmployeeShifts().get(k);
                    shiftObj.put("Shift Start Date", s.getStartDate());
                    shiftObj.put("Shift Start Time", s.getStartTime());
                    shiftObj.put("Shift End Time", s.getEndTime());
                    shiftObj.put("Note", s.getNote());
                    shiftObj.put("Break Time", s.getBreakTime());
                    shiftObj.put("Shift ID", s.getID());
                    employeeShifts.put(shiftObj);

                }

                timeSheetObj.put("Employee Shifts", employeeShifts);
                timeSheets.put(timeSheetObj);

            }

            employeeObj.put("Employee Time Sheets", timeSheets);
            employees.put(employeeObj);
        }
        managerObj.put("Employees", employees);
        System.out.println("WE DONE");
        return managerObj;
//        for (int i = 0; i < m.getEmployeeList().size(); i++) {
//            fw
//        }


    }

//	public void recursiveWrite(JSONObject obj, FileWriter fw, 
//			String[] values, int depth) {
//		if(depth == 0) {
//			JSONArray js;
//			try {
//				js = (JSONArray) obj.get(values[0]);
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//			fw.write(obj.toString());
//			for(int i = 0; i < js.length(); i++) {
//				
//			}
//			obj.get(values[0]);
//		}
//	}
    
}
