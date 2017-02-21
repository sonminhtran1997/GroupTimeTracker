package com.example.leole.timetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

public class TimeSheetActivity extends AppCompatActivity {

    final static String PARSE_ID = "PARSE id";
    final static String EMPLOYEE_ID = "employee id";
    private int timeSheetID;
    private int employeeid;
    protected Employee employee;
    private List<TimeSheet> timeSheetList;
    private Manager manager = Manager.getManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_sheet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        employeeid = intent.getIntExtra(EMPLOYEE_ID, -1);
        if(employeeid == -1)
            finish();
        employee = manager.getEmployeeByID(employeeid);
        printData();

    }
    public void printData(){
        timeSheetList = employee.getTimeSheetList();
        ArrayAdapter<TimeSheet> listAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, timeSheetList);


        final ListView lstTimeSheet = (ListView) findViewById(R.id.lstTimeSheet);
        lstTimeSheet.setAdapter(listAdapter);
        lstTimeSheet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TimeSheetActivity.this, ShiftList.class);
                TimeSheet currentTimeSheet = (TimeSheet)lstTimeSheet.getItemAtPosition(position);
                timeSheetID = currentTimeSheet.getID();
                String parseString = employeeid + "," + timeSheetID;
                intent.putExtra(PARSE_ID, parseString);
                startActivity(intent);
            }
        });

    }

    public void btnAddTimeOnClick(View view){
        Intent intent = new Intent(this, NewTimeSheet.class);
        intent.putExtra(EMPLOYEE_ID, employeeid);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        printData();
    }
}
