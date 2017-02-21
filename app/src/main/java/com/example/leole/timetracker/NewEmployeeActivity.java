package com.example.leole.timetracker;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.JsonWriter;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class NewEmployeeActivity extends AppCompatActivity {

    //fields
    private Manager manager = Manager.getManager();
    private String name;
    private String phone;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_employee);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void btnAcceptOnClick(View view) throws IOException, JSONException {
        EditText txtName = (EditText) findViewById(R.id.txtName);
        EditText txtPhone = (EditText) findViewById(R.id.txtPhone);
        EditText txtEmail = (EditText) findViewById(R.id.txtEmail);

        name = txtName.getText().toString();
        phone = txtPhone.getText().toString();
        email = txtEmail.getText().toString();

        if (name.trim().isEmpty() || phone.trim().isEmpty() || email.trim().isEmpty()){
            Toast.makeText(view.getContext(), "Please fill all required fields!", Toast.LENGTH_SHORT).show();
        }else {
            Employee e = new Employee();
            e.setEmail(email);
            e.setName(name);
            e.setPhoneNumber(phone);
            manager.addEmployee(e);
//        JSonWriter writer = new JSonWriter(manager);
//        writer.writeToJSon();
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
        }

    }


    public void btnCancelOnClick(View view){
        finish();
    }

}
