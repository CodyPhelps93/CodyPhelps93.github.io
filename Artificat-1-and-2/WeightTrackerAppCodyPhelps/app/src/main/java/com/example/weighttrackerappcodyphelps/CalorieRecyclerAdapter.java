package com.example.weighttrackerappcodyphelps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalorieRecyclerAdapter extends RecyclerView.Adapter<CalorieRecyclerAdapter.CalorieViewHolder> {
    private final Context context;
    private final ArrayList<String> calorieID;
    private final ArrayList<String> intakeCalorie;
    private final ArrayList<String> burnedCalorie;
    private final ArrayList<String> deficitCalories;
    private final ArrayList<String> dailyCalDate;
    private int selectedItemPosition = RecyclerView.NO_POSITION;

    // Function to get item position
    public void setSelectedItemPosition(int position) {
        selectedItemPosition = position;
        if (position != RecyclerView.NO_POSITION && position < calorieID.size()) {
            int itemId = Integer.parseInt(calorieID.get(position));
            selectedItemPosition = itemId;
        }
    }

    public int getSelectedItemPosition(){
        return selectedItemPosition;
    }

    CalorieRecyclerAdapter(Context context, ArrayList<String> calorieID, ArrayList<String> intakeCalorie,
                    ArrayList<String> burnedCalorie, ArrayList<String> deficitCalories, ArrayList<String> dailyCalDate) {
        this.context = context;
        this.calorieID = calorieID;
        this.intakeCalorie = intakeCalorie;
        this.burnedCalorie = burnedCalorie;
        this.deficitCalories = deficitCalories;
        this.dailyCalDate = dailyCalDate;
    }
    public class CalorieViewHolder extends RecyclerView.ViewHolder{
        TextView calorieIDText, intakeCal, burnCal, calDeficit, dailyCalorieDate;

        public CalorieViewHolder(View itemView) {
            super(itemView);
            calorieIDText = itemView.findViewById(R.id.calorieIDText);
            intakeCal = itemView.findViewById(R.id.recycleIntakeCalories);
            burnCal = itemView.findViewById(R.id.recycleBurnedCalories);
            calDeficit = itemView.findViewById(R.id.recycleCalDeficit);
            dailyCalorieDate = itemView.findViewById(R.id.recycleCalDate);

        }
    }

    @Override
    public CalorieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.calorie_recycle_row, parent, false);
        return new CalorieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CalorieViewHolder CalorieViewHolder, final int position) {
        CalorieViewHolder.calorieIDText.setText(String.valueOf(position + 1)); // sets id to the position in RecyclerView
        CalorieViewHolder.intakeCal.setText(intakeCalorie.get(position));
        CalorieViewHolder.burnCal.setText(burnedCalorie.get(position));
        CalorieViewHolder.calDeficit.setText(deficitCalories.get(position));
        CalorieViewHolder.dailyCalorieDate.setText(dailyCalDate.get(position));

        int itemId = Integer.parseInt(calorieID.get(position)); // Assuming userId holds the ID as a String

        // Highlight the selected item
        if (itemId == selectedItemPosition) {
            CalorieViewHolder.itemView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_blue_light));
            CalorieViewHolder.calorieIDText.setTextColor(ContextCompat.getColor(context, android.R.color.black));
            CalorieViewHolder.intakeCal.setTextColor(ContextCompat.getColor(context, android.R.color.black));
            CalorieViewHolder.burnCal.setTextColor(ContextCompat.getColor(context, android.R.color.black));
            CalorieViewHolder.calDeficit.setTextColor(ContextCompat.getColor(context, android.R.color.black));
            CalorieViewHolder.dailyCalorieDate.setTextColor(ContextCompat.getColor(context, android.R.color.black));
        } else {
            CalorieViewHolder.itemView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
        }

        CalorieViewHolder.itemView.setOnClickListener(v -> {
            setSelectedItemPosition(itemId);
            notifyDataSetChanged(); // Refresh the RecyclerView to apply the new selection
        });
    }

    @Override
    public int getItemCount() {return calorieID.size(); }
}
