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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class ShiftList extends AppCompatActivity {
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
    private ListView lstShift;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        getParseId = intent.getStringExtra(TimeSheetActivity.PARSE_ID);
        System.out.println(getParseId);
        employeId = Integer.valueOf(getParseId.split(",")[0]);
        timeSheetId = Integer.valueOf(getParseId.split(",")[1]);

        employee = manager.getEmployeeByID(employeId);
        printData();

    }

    public void printData(){
        TimeSheet t = employee.getTimeSheetByID(timeSheetId);
        TextView lblDatePeriod = (TextView) findViewById(R.id.lblDatePeriod);
        lblDatePeriod.setText(t.getStartDate() + " to " + t.getEndDate());
        shiftList = employee.getTimeSheetByID(timeSheetId).getEmployeeShifts();
        ArrayAdapter<Shift> listAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, shiftList);
        lstShift = (ListView) findViewById(R.id.lstShift);
        lstShift.setAdapter(listAdapter);
        lstShift.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShiftList.this, ShiftInfoActivity.class);
                Shift currentShift = (Shift) lstShift.getItemAtPosition(position);
                shiftId = currentShift.getID();
                getParseId = employeId + "," + timeSheetId + "," + shiftId;
                System.out.println(getParseId);
                intent.putExtra(PARSE_ID, getParseId);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        printData();
    }
    public void btnAddOnClick(View view){
        Intent intent = new Intent(this, SetTimeActivity.class);
        intent.putExtra(PARSE_ID, getParseId);
        startActivity(intent);
    }
}
