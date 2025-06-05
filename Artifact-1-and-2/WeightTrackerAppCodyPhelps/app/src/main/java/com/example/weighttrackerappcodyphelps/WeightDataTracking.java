package com.example.weighttrackerappcodyphelps;


import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import static com.example.weighttrackerappcodyphelps.DBHelper.TABLE_USER_INFO;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class WeightDataTracking extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayout linearLayout;
    DBHelper myDB;
    RecyclerAdapter recyclerAdapter;
    EditText currWeightET, dateTime;
    Button addWeight, deleteWeight;
    ArrayList<String> userID, currWeight, prevWeight, date, goal;
    String firstName;
    Integer userAge, userGoal, userCurrWeight;
    SMSPermission smsPermission;

    private GestureDetectorCompat gestureDetectorCompat;

    // First 3 functions for creating navigation menu at top of screen
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_sms_permission) {
            Intent sms = new Intent(this, SMSPermission.class);
            startActivity(sms);
            return true;
        }
        if (item.getItemId() == R.id.logout) {
            Intent LogOut = new Intent(this, MainActivity.class);
            startActivity(LogOut);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight_data_tracking);

        // variables initialization
        recyclerView = findViewById(R.id.weightRecyclerView);
        currWeightET = findViewById(R.id.currentWeightEditText);
        dateTime = findViewById(R.id.dateAndTimeEditText);
        myDB = new DBHelper(WeightDataTracking.this);
        addWeight = findViewById(R.id.btnAddWeight);
        deleteWeight = findViewById(R.id.btnRemoveWeight);
        firstName = myDB.getUserName();
        userAge = myDB.getUserAge();
        userGoal = myDB.getUserGoal();
        userCurrWeight = myDB.getCurrentWeight();
        Log.d(TAG, "userCurrWeight: " + userCurrWeight);
        smsPermission = new SMSPermission();
        ValidationCheckers validator = new ValidationCheckers(this);


        // Gestures
        linearLayout = findViewById(R.id.linearLayout);
        SwipeGestureListener swipeGestureListener = new SwipeGestureListener(this);
        gestureDetectorCompat = new GestureDetectorCompat(this, swipeGestureListener);


        linearLayout.setOnTouchListener((v, event) -> {
            Log.d("Touch", "onTouch: " + event);
            return gestureDetectorCompat.onTouchEvent(event);

        });

        // Add weight button logic
        addWeight.setOnClickListener(v -> {
            String editCurrWeight = currWeightET.getText().toString().trim();
            String editCurrDate = dateTime.getText().toString().trim();
            if (!editCurrWeight.isEmpty() && !editCurrDate.isEmpty()) {
                if (validator.isValidInteger(editCurrWeight) && validator.isValidDateFormat(editCurrDate)) {
                    long result = myDB.updateUserInfo(firstName, userAge, Integer.parseInt(currWeightET.getText().toString().trim()),
                            dateTime.getText().toString().trim(), userGoal);
                    if (result != -1) {
                        userID.clear();
                        currWeight.clear();
                        prevWeight.clear();
                        date.clear();
                        goal.clear();
                        displayData();
                        recyclerAdapter.notifyDataSetChanged();
                        // checks goal weight to send the sms
                        if (userCurrWeight <= userGoal) {

                            smsPermission.sendSMS();
                        }

                        currWeightET.getText().clear();
                        dateTime.getText().clear();


                    }
                }
            }
        });

        // Delete Button
        deleteWeight.setOnClickListener(v -> {
            int position = recyclerAdapter.getSelectedItemPosition();
            if (position != RecyclerView.NO_POSITION) {
                long result = myDB.deleteWeightItem(position); // Delete item from database
                if (result != -1) {
                    userID.clear();
                    currWeight.clear();
                    prevWeight.clear();
                    date.clear();
                    goal.clear();
                    displayData();
                    recyclerAdapter.notifyDataSetChanged();
                    recyclerAdapter.setSelectedItemPosition(RecyclerView.NO_POSITION);
                    Toast.makeText(WeightDataTracking.this, "Item deleted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(WeightDataTracking.this, "Failed to delete item", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(WeightDataTracking.this, "Please select an item to delete", Toast.LENGTH_SHORT).show();
            }
        });

        // Array for recycler view and line chart
        userID = new ArrayList<>();
        currWeight = new ArrayList<>();
        prevWeight = new ArrayList<>();
        date = new ArrayList<>();
        goal = new ArrayList<>();

        displayData();


        // Initialize and set up the RecyclerView after populating the data
        recyclerAdapter = new RecyclerAdapter(WeightDataTracking.this, userID, currWeight, prevWeight, date, goal);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    void displayData() {
        Cursor cursor = myDB.readWeightData();
        if (cursor == null) {
            Toast.makeText(WeightDataTracking.this, "Error reading data", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cursor.getCount() == 0) {
            Toast.makeText(WeightDataTracking.this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                userID.add(cursor.getString(0));
                currWeight.add(cursor.getString(1));
                prevWeight.add(cursor.getString(2));
                date.add(cursor.getString(3));
                goal.add(cursor.getString(4));
            }
        }
        cursor.close();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean gestureDetected = gestureDetectorCompat.onTouchEvent(event);
        Log.d("SwipeGesture", "onTouchEvent: Gesture detected - " + gestureDetected);
        return super.onTouchEvent(event);
    }


}
