package com.example.leole.timetracker;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class SetTimeActivity extends AppCompatActivity {

    private int breakTime;
    private EditText txtDate;
    private EditText txtEndTime;
    private EditText txtNote;
    private EditText txtStartTime;
    private Spinner spnBreakTime;
    private String strBreakTime;
    private String startTime;
    private String endTime;
    private String date;
    private String note;
    private int intCheck;

    final static String PARSE_ID = "parse id";
    private int shiftId;
    private String  getParseId;
    private List<Employee> employeeList;
    private int employeId;
    private int timeSheetId;
    private Manager manager = Manager.getManager();
    private Employee employee;
    private Spinner spnName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        getParseId = intent.getStringExtra(PARSE_ID);
        System.out.println(getParseId);
        employeId = Integer.valueOf(getParseId.split(",")[0]);
        timeSheetId = Integer.valueOf(getParseId.split(",")[1]);
        employeeList  = manager.getEmployeeList();
        employee = manager.getEmployeeByID(employeId);
        TextView lblName = (TextView) findViewById(R.id.lblName);
        lblName.setText(employee.getEmployeeName());

        txtStartTime = (EditText) findViewById(R.id.txtStartTime);
        txtStartTime.setInputType(InputType.TYPE_NULL);
        txtStartTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    DialogFragment newFragment = new TimePickerFragment();
                    newFragment.show(getFragmentManager(), "Time Picker");
                }
            }
        });

        txtEndTime = (EditText) findViewById(R.id.txtEndTime);
        txtEndTime.setInputType(InputType.TYPE_NULL);
        txtEndTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    DialogFragment newFragment = new TimePickerFragment();
                    newFragment.show(getFragmentManager(), "Time Picker");
                }
            }
        });
        txtDate = (EditText) findViewById(R.id.txtDate);
        txtDate.setInputType(InputType.TYPE_NULL);
        txtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    DialogFragment newFragment = new DatePickerFragment();
                    newFragment.show(getFragmentManager(), "Date Picker");
                }
            }
        });


    }

    public void btnAddTime(View view) {
        startTime = txtStartTime.getText().toString();
        endTime = txtEndTime.getText().toString();
        date = txtDate.getText().toString();
        spnBreakTime = (Spinner) findViewById(R.id.spnBreakTime);
        strBreakTime = spnBreakTime.getSelectedItem().toString();
        setBreakTime(strBreakTime);
        txtNote = (EditText) findViewById(R.id.txtNote);
        note = txtNote.getText().toString();

        Shift shift = new Shift();
        shift.setDate(date);
        shift.setStartTime(startTime);
        shift.setEndTime(endTime);
        shift.setBreakTime(breakTime);
        shift.setNote(note);
        intCheck = employee.getTimeSheetByID(timeSheetId).addShift(shift);
        //Check for the error
        switch (intCheck){
            case 0: {
                Toast.makeText(view.getContext(), "Add Time successfully", Toast.LENGTH_SHORT).show();
                try {
                    FileOutputStream fos = openFileOutput("out.txt", Context.MODE_PRIVATE);
                    ObjectOutputStream os = new ObjectOutputStream(fos);
                    JSonWriter jSonWriter = new JSonWriter(manager);
                    os.writeObject(manager);
                    os.close();
                    finish();
                }catch(IOException exp){
                    exp.printStackTrace();

                }
                finish();
                break;
            }
            case 1: {
                Toast.makeText(view.getContext(), "Entry time is over maximum hours",
                        Toast.LENGTH_SHORT).show();
                break;
            }
            case 2: {
                Toast.makeText(view.getContext(), "Entry Date isn't in the Pay period",
                        Toast.LENGTH_SHORT).show();
                break;
            }
            case 3: {
                Toast.makeText(view.getContext(), "Entry time isn't valid",
                        Toast.LENGTH_SHORT).show();
                break;
            }
            case 4: {
                Toast.makeText(view.getContext(), "Duplicate date entry",
                        Toast.LENGTH_SHORT).show();
                break;
            }
        }

    }
    public void btnCancelOnClick(View view){
        finish();
    }
    private void setBreakTime(String strBreakTime) {
        switch (strBreakTime) {
            case "15 minutes":
                breakTime = 15;
                break;
            case "30 minutes":
                breakTime = 30;
                break;
            case "45 minutes":
                breakTime = 45;
                break;
            case "60 minutes":
                breakTime = 60;
                break;
        }
    }

}
