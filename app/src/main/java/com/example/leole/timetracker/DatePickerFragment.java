package com.example.leole.timetracker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by LeoLe on 2/18/17.
 */

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener  {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //Use the current time as the default values for the time picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        //Create and return a new instance of TimePickerDialog
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    //onTimeSet() callback method
    public void onDateSet(DatePicker view, int year, int month, int day){
        //Do something with the user chosen time
        //Get reference of host activity (XML Layout File) TextView widget
        EditText tv = (EditText) getActivity().findViewById(R.id.txtDate);
        EditText txtStartDate = (EditText) getActivity().findViewById(R.id.txtStartDate);
        EditText txtEndDate = (EditText) getActivity().findViewById(R.id.txtEndDate);
        //Set a message for user
        if(tv != null){
            tv.setText(String.valueOf(month + 1) + "/" + String.valueOf(day) + "/" + String.valueOf(year));
        }else if (txtStartDate.isFocused()){
            txtStartDate.setText(String.valueOf(month + 1) + "/" + String.valueOf(day) + "/" + String.valueOf(year));
        } else if (txtEndDate.isFocused()) {
            txtEndDate.setText(String.valueOf(month+ 1) + "/" + String.valueOf(day) + "/" + String.valueOf(year));
        }
    }

}

