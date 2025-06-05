package com.example.weighttrackerappcodyphelps;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalorieDataTracking extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView totalWeightLoss;
    DBHelper myDB;
    CalorieRecyclerAdapter recyclerAdapter;
    EditText intakeCalEt, burnedCalEt, calDateEt;
    Button addDailyCals, deleteData;
    ArrayList<String> calorieID, intakeCaloriesArray,burnedCalorieArray, calorieDeficitArray, calDateArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calorie_data_tracking);
        myDB = new DBHelper(this);
        ValidationCheckers validator = new ValidationCheckers(this);

        //initialize variables used in view
        recyclerView = findViewById(R.id.calorieRecyclerView);
        intakeCalEt = findViewById(R.id.calorieIntakeEditText);
        burnedCalEt = findViewById(R.id.caloriesBurnedEditText);
        calDateEt = findViewById(R.id.calDateEditText);
        addDailyCals = findViewById(R.id.btnAddIntakeCalories);
        deleteData = findViewById(R.id.btnRemoveCalories);
        totalWeightLoss = findViewById(R.id.totalDeficitTextView);

        //Arrays for recyclerView
        calorieID = new ArrayList<>();
        intakeCaloriesArray = new ArrayList<>();
        burnedCalorieArray = new ArrayList<>();
        calorieDeficitArray = new ArrayList<>();
        calDateArray = new ArrayList<>();


        displayCalData();
        displayWeightLoss();

        // setup recycler adapter and view
        recyclerAdapter = new CalorieRecyclerAdapter(CalorieDataTracking.this, calorieID, intakeCaloriesArray,
                burnedCalorieArray,calorieDeficitArray,calDateArray);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(true);

    //add Daily Calories button
        addDailyCals.setOnClickListener(v -> {

            String etIntakeCal = intakeCalEt.getText().toString();
            String etBurnedCal = burnedCalEt.getText().toString();
            String date = calDateEt.getText().toString().trim();
            int intakeCalInt = 0;
            int burnedCalInt = 0;
            //convert from string to int
            try {
                intakeCalInt = Integer.parseInt(etIntakeCal);
                burnedCalInt = Integer.parseInt((etBurnedCal));
            } catch (NumberFormatException e) {
                Toast.makeText(CalorieDataTracking.this, "Failed to convert Strings", Toast.LENGTH_SHORT).show();
            }
            int calDeficit = calculateDeficit(intakeCalInt, burnedCalInt);

            if (!etIntakeCal.isEmpty() && !etBurnedCal.isEmpty() && !date.isEmpty()) {
                if (validator.isValidDateFormat(date) && validator.isValidInteger(etIntakeCal) && validator.isValidInteger(etBurnedCal))
                    {
                        long result = myDB.insertCalorieInfo(intakeCalInt, burnedCalInt, calDeficit, date);
                        if (result != -1) {
                            calorieID.clear();
                            intakeCaloriesArray.clear();
                            burnedCalorieArray.clear();
                            calorieDeficitArray.clear();
                            calDateArray.clear();
                            displayCalData();
                            displayWeightLoss();
                            recyclerAdapter.notifyDataSetChanged();

                        }
                    }
            } else {
                Toast.makeText(CalorieDataTracking.this, "Make Sure all fields are filled in", Toast.LENGTH_SHORT).show();
            }
        });
        deleteData.setOnClickListener(v -> {
            int position = recyclerAdapter.getSelectedItemPosition();
            if(position != RecyclerView.NO_POSITION) {
                try {
                    long result = myDB.deleteCalorieData(position);
                    if (result != -1) {
                        calorieID.clear();
                        intakeCaloriesArray.clear();
                        burnedCalorieArray.clear();
                        calorieDeficitArray.clear();
                        calDateArray.clear();
                        displayCalData();
                        displayWeightLoss();
                        recyclerAdapter.notifyItemRemoved(position);
                    }
                } catch (SQLiteException e) {
                    Log.e("DB Error" , "SQLiteException: " + e.getMessage());
                }
            }
        });



    }

    // used to fill arrays from DB information
    void displayCalData() {
        Cursor cursor = myDB.readCalorieData();
        try {
            if (cursor == null) {
                Toast.makeText(this, "Error reading calorie data", Toast.LENGTH_SHORT).show();
            } else {
                // aligns with column number
                while (cursor.moveToNext()) {
                    calorieID.add(cursor.getString(0));
                    intakeCaloriesArray.add(cursor.getString(1));
                    burnedCalorieArray.add(cursor.getString(2));
                    calorieDeficitArray.add(cursor.getString(3));
                    calDateArray.add(cursor.getString(4));
                }
            }
            cursor.close();

        }catch(SQLiteException e) {
            Log.e("Error reading from DB: ", e.getMessage());
        }
    }

    public int calculateDeficit(int intakeCal, int burnedCal) {
        return burnedCal - intakeCal;
    }

    // Calculate weight in lbs  and total deficit and display
    public void displayWeightLoss() {
        int totalDeficit = 0;
        float calsInLbs = 3500;
        float weightLossLbs = 0;
        try {
            for (int i = 0; i < calorieDeficitArray.size(); i++) {
               totalDeficit += Integer.parseInt(calorieDeficitArray.get(i));
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Failed to convert string to int", Toast.LENGTH_SHORT).show();
        }
        if (totalDeficit != 0) { // makes sure we do not divide with 0
             weightLossLbs = totalDeficit / calsInLbs;
        }

        String text = String.format("Total Deficit: %d kcal (%.2f lbs)", totalDeficit, weightLossLbs);
        totalWeightLoss.setText(text);
    }
}
