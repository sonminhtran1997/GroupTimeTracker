package com.example.leole.timetracker;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    private static Manager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            File readFile = new File(getApplicationContext().getFilesDir(), "out.txt");
            FileInputStream fis = openFileInput(readFile.getName());
            ObjectInputStream is = new ObjectInputStream(fis);
            Manager m = (Manager) is.readObject();
            Manager.setManager(m);
            is.close();
        } catch(ClassNotFoundException exp){
            exp.printStackTrace();
        } catch (IOException exp){
            exp.printStackTrace();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void btnNewEmployeeOnClick(View view){
        Intent intent = new Intent(this, NewEmployeeActivity.class);
        startActivity(intent);
    }

    public void btnEmployeeListOnClick(View view){
        Intent intent = new Intent(this,EmployeeListActivity.class);
        startActivity(intent);
    }
}
