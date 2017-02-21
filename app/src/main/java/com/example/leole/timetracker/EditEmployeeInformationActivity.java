package com.example.leole.timetracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class EditEmployeeInformationActivity extends AppCompatActivity {

    final static String EMPLOYEE_ID = "employee id";
    private Manager manager = Manager.getManager();
    private int employeeid;
    private Employee employee;
    EditText txtName;
    EditText txtPhone;
    EditText txtEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employee_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        employeeid = intent.getIntExtra(EMPLOYEE_ID, -1);
        if(employeeid == -1){
            finish();
        }
        System.out.println(employeeid);
        employee = manager.getEmployeeByID(employeeid);
        System.out.println(employee);

        printData();

    }

    public void printData(){
        //populate customer information
        txtName = (EditText) findViewById(R.id.txtName);
        txtName.setText(employee.getEmployeeName());
        txtPhone = (EditText) findViewById(R.id.txtPhone);
        txtPhone.setText(employee.getPhoneNumber());
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtEmail.setText(employee.getEmail());
    }

    public void btnAcceptOnClick(View view){
        if(txtPhone.getText().toString().isEmpty() ||
                txtName.getText().toString().isEmpty() ||
                    txtEmail.getText().toString().isEmpty()) {
            Toast.makeText(view.getContext(),
                    "Please fill all required fields!", Toast.LENGTH_SHORT).show();
        } else {

            manager.getEmployeeByID(employeeid).setPhoneNumber(txtPhone.getText().toString());
            manager.getEmployeeByID(employeeid).setName(txtName.getText().toString());
            manager.getEmployeeByID(employeeid).setEmail(txtEmail.getText().toString());
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
