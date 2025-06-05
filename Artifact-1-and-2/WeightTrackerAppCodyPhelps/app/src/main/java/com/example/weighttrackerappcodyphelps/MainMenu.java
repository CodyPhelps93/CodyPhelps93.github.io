package com.example.weighttrackerappcodyphelps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {
    //set variables
    ImageButton weightTracker, calorieTracker, graphChart, smsPermission;
    TextView loggedIn;
    private SharedPreferences prefs;
    private String loggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        //Initialize the variable for the image buttons
        weightTracker = (ImageButton) findViewById(R.id.ibWeightTracker);
        calorieTracker = (ImageButton) findViewById(R.id.ibCalorieTracker);
        graphChart = (ImageButton) findViewById(R.id.ibChart);
        smsPermission = (ImageButton) findViewById(R.id.ibSMSPermissions);
        loggedIn = (TextView) findViewById(R.id.loggedInAs);

        weightTracker.setOnClickListener(this);
        calorieTracker.setOnClickListener(this);
        graphChart.setOnClickListener(this);
        smsPermission.setOnClickListener(this);

        prefs = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        loggedInUser = prefs.getString("loggedInUser", "Guest");

        loggedIn.setText("Logged in as: " + loggedInUser);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ibWeightTracker) {
            Intent intent = new Intent(MainMenu.this, WeightDataTracking.class);
            startActivity(intent);

        } else if (v.getId() == R.id.ibCalorieTracker) {
            Intent intent = new Intent(MainMenu.this, CalorieDataTracking.class);
            startActivity(intent);
        }

        else if (v.getId() == R.id.ibChart) {
            Intent intent = new Intent(MainMenu.this, LineGraph.class);
            startActivity(intent);
        }

        else if (v.getId() == R.id.ibSMSPermissions) {
            Intent intent = new Intent(MainMenu.this, SMSPermission.class);
            startActivity(intent);
        }
    }

}
