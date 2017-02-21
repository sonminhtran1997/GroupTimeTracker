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
import android.widget.ListView;

import java.util.List;

public class EmployeeListActivity extends AppCompatActivity {
    final static String EMPLOYEE_ID = "employee id";
    private Manager manager = Manager.getManager();
    private List<Employee>
            employeeList;
    private int employeeid;

    public EmployeeListActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        printData();


    }
    public void printData(){
        employeeList = manager.getEmployeeList();
        ArrayAdapter<Employee> listAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, employeeList);
        final ListView lstEmployee = (ListView) findViewById(R.id.lstEmployeeList);
        lstEmployee.setAdapter(listAdapter);
        lstEmployee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(EmployeeListActivity.this,
                        EmployeeInformationActivity.class);

                Employee currentEmployee = (Employee)lstEmployee.getItemAtPosition(position);
                System.out.println(currentEmployee);
                intent.putExtra(EMPLOYEE_ID, currentEmployee.getEmployeeID());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        printData();
    }
}
