package com.example.leole.timetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

public class ShiftInfoActivity extends AppCompatActivity {
    final static String SHIFT_ID = "shift id";
    final static String TIME_SHEET_ID = "timesheet id";
    final static String PARSE_ID = "parse id";
    private int shiftId;
    private String  getParseId;
    private List<Shift> shiftList;
    private int employeId;
    private int timeSheetId;
    private Manager manager = Manager.getManager();
    private Employee employee;
    private Shift shift;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        getParseId = intent.getStringExtra(PARSE_ID);
        employeId = Integer.valueOf(getParseId.split(",")[0]);
        timeSheetId = Integer.valueOf(getParseId.split(",")[1]);
        shiftId = Integer.valueOf(getParseId.split(",")[2]);

        employee = manager.getEmployeeByID(employeId);
        shift = employee.getTimeSheetByID(timeSheetId).getShiftByID(shiftId);
        printData();



    }

    public void printData(){
        EditText txtName = (EditText) findViewById(R.id.txtName);
        EditText txtDate = (EditText) findViewById(R.id.txtDate);
        EditText txtStartTime = (EditText) findViewById(R.id.txtStartTime);
        EditText txtEndTime = (EditText) findViewById(R.id.txtEndTime);
        EditText txtNote = (EditText) findViewById(R.id.txtNote);

        txtDate.setText(shift.getStartDate());
        txtStartTime.setText(shift.getStartTime());
        txtEndTime.setText(shift.getEndTime());
        txtName.setText(employee.getEmployeeName());
        txtNote.setText(shift.getNote());

        int position = 0;
        switch (shift.getBreakTime()){
            case 15:
                position = 0;
                break;
            case 30:
                position = 1;
                break;
            case 45:
                position = 2;
                break;
            case 60:
                position = 3;
                break;
        }
        Spinner spnBreakTime = (Spinner) findViewById(R.id.spnBreakTime);
        spnBreakTime.setSelection(position);
    }

    public void btnCancelOnClick(View view){
        finish();

    }

}
