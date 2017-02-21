package com.example.leole.timetracker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by LeoLe on 2/18/17.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //Use the current time as the default values for the time picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        //Create and return a new instance of TimePickerDialog
        return new TimePickerDialog(getActivity(),this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    //onTimeSet() callback method
    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
        //Do something with the user chosen time
        //Get reference of host activity (XML Layout File) TextView widget

        EditText startTime = (EditText) getActivity().findViewById(R.id.txtStartTime);
        EditText endTime = (EditText) getActivity().findViewById(R.id.txtEndTime);
        if(startTime.isFocused()) {
            //Set a message for user
            if (minute >= 10) {
                startTime.setText(String.valueOf(hourOfDay) + ":" + String.valueOf(minute));
            } else {
                startTime.setText(String.valueOf(hourOfDay) + ":0" + String.valueOf(minute));
            }
        } else if (endTime.isFocused()){
            if (minute >= 10) {
                endTime.setText(String.valueOf(hourOfDay) + ":" + String.valueOf(minute));
            } else {
                endTime.setText(String.valueOf(hourOfDay) + ":0" + String.valueOf(minute));
            }

        }
    }
}
