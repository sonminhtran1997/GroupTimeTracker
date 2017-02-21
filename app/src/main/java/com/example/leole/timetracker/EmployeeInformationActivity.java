package com.example.leole.timetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class EmployeeInformationActivity extends AppCompatActivity {

    final static String EMPLOYEE_ID = "employee id";
    private Manager mananger = Manager.getManager();
    private int employeeid;
    private Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setData();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setData();
    }

    public void btnEditOnClick(View view){
        Intent intent = new Intent(this, EditEmployeeInformationActivity.class);
        intent.putExtra(EMPLOYEE_ID, employeeid);
        startActivity(intent);
    }
    public void setData(){
        //Get data
        Intent intent = getIntent();
        employeeid = intent.getIntExtra(EMPLOYEE_ID, -1);
        if(employeeid == -1) {
            finish();
        }
        System.out.println(employeeid);
        employee = mananger.getEmployeeByID(employeeid);

        //populate customer information
        EditText txtName = (EditText) findViewById(R.id.txtName);
        txtName.setText(employee.getEmployeeName());
        EditText txtPhone = (EditText) findViewById(R.id.txtPhone);
        txtPhone.setText(employee.getPhoneNumber());
        EditText txtLastActivity = (EditText) findViewById(R.id.txtEmail);
        txtLastActivity.setText(employee.getEmail());
    }

    //waiting for java class
    public void btnDeleteOnClick(View view){
        //put code

        finish();
    }

    public void btnCancelOnClick(View view){
        finish();
    }

    public void btnTimeSheetOnClick(View view){
        Intent intent = new Intent(this, TimeSheetActivity.class);
        intent.putExtra(EMPLOYEE_ID, employeeid);
        startActivity(intent);
    }

}
