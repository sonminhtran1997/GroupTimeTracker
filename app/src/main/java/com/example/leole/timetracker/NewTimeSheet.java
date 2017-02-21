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
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class NewTimeSheet extends AppCompatActivity{

        final static String TIME_SHEET_ID = "timesheet id";
        final static String EMPLOYEE_ID = "employee id";
        private int timeSheetID;
        private int employeeid;
        private Manager manager = Manager.getManager();
        private Employee employee;

    private EditText txtEndDate;
    private EditText txtStartDate;
    private EditText txtMaxHour;

    private String endDate;
    private String startDate;
    private int maxHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_time_sheet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        employeeid = intent.getIntExtra(EMPLOYEE_ID, -1);
        if(employeeid == -1){
            finish();
        }

        txtStartDate = (EditText) findViewById(R.id.txtStartDate);
        txtStartDate.setInputType(InputType.TYPE_NULL);
        txtStartDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    DialogFragment newFragment = new DatePickerFragment();
                    newFragment.show(getFragmentManager(), "Date Picker");
                }
            }
        });

        txtEndDate = (EditText) findViewById(R.id.txtEndDate);
        txtEndDate.setInputType(InputType.TYPE_NULL);
        txtEndDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    DialogFragment newFragment = new DatePickerFragment();
                    newFragment.show(getFragmentManager(), "Date Picker");
                }
            }
        });
    }

    public void btnAcceptOnClick(View view){

        txtMaxHour = (EditText) findViewById(R.id.txtMaxHour);
        endDate = txtEndDate.getText().toString();
        startDate = txtStartDate.getText().toString();
        maxHour = Integer.valueOf(txtMaxHour.getText().toString());
        employee = manager.getEmployeeByID(employeeid);
        TimeSheet t = new TimeSheet(startDate, endDate,maxHour);
        if(!t.isCheckDate()){
            Toast.makeText(view.getContext(), "Invalid Entry", Toast.LENGTH_SHORT).show();
        } else {
            employee.addTimeSheet(t);
            try {
                FileOutputStream fos = openFileOutput("out.txt", Context.MODE_PRIVATE);
                ObjectOutputStream os = new ObjectOutputStream(fos);
                os.writeObject(manager);
                os.close();
                finish();
            }catch(IOException exp){
                exp.printStackTrace();

            }
            finish();
        }
    }
    public void btnCancelOnClick(View view){
        finish();

    }

}
