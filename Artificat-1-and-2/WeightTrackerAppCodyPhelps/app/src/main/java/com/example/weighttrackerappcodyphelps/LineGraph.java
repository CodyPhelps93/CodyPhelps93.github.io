package com.example.weighttrackerappcodyphelps;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class LineGraph extends AppCompatActivity {

    LineChart lineChart;
    DBHelper myDB;
    ArrayList<String> currWeight;
    ArrayList<LocalDate> currDate;
    RadioGroup predictionRadioGroup;
    LinearRegression linearRegression;
    TextView predictedTV;
    private double predictedWeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_view);
        lineChart = findViewById(R.id.lineChart);
        predictionRadioGroup = findViewById(R.id.predictionRadioGroup);
        predictedTV = findViewById(R.id.predictionTV);
        myDB = new DBHelper(LineGraph.this);
        currWeight = new ArrayList<>();
        currDate = new ArrayList<>();


        // Populate data
        fillCurrWeight();
        fillCurrDate();
        if (currWeight.size() != currDate.size()) {
            Toast.makeText(this, "Weight and date lists misaligned", Toast.LENGTH_SHORT).show();
            return;
        }
        linearRegression = new LinearRegression(currWeight, currDate);

        // Setup chart and radio button listener
        setupLineChart(0); // Default: no prediction
        setupRadioGroup();
    }

    private void setupLineChart(int predictionDays) {
        ArrayList<Entry> weightEntries = new ArrayList<>();
        ArrayList<Entry> predictionEntries = new ArrayList<>();

        // Add actual weight data
        for (int i = 0; i < currWeight.size(); i++) {
            try {
                float weight = Float.parseFloat(currWeight.get(i));
                weightEntries.add(new Entry(i, weight));
            } catch (NumberFormatException e) {
                Log.e("LineGraph", "Invalid weight format at index " + i + ": " + currWeight.get(i));
            }
        }

        // Create dataset for actual weights
        LineDataSet weightDataSet = new LineDataSet(weightEntries, "Weight Data");
        weightDataSet.setColor(Color.BLUE);
        weightDataSet.setLineWidth(2f);
        weightDataSet.setValueTextSize(12f);
        weightDataSet.setCircleColor(Color.BLUE);
        weightDataSet.setValueTextColor(Color.WHITE);
        weightDataSet.setCircleRadius(4f);


        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(weightDataSet);

        // Add prediction point if enabled and 2 or more data points
        if (predictionDays > 0 && currWeight.size() >= 2) {
            try {

                int lastIndex = currWeight.size() - 1;
                float lastWeight = Float.parseFloat(currWeight.get(lastIndex));
                predictionEntries.add(new Entry(lastIndex, lastWeight)); // Start of prediction line


                int finalIndex = lastIndex + predictionDays;
                 predictedWeight = linearRegression.predictedWeight(finalIndex);
                if (predictedWeight < 0) {
                    predictedWeight = 0; // Prevent negative weights
                }
                predictionEntries.add(new Entry(finalIndex, (float) predictedWeight));

                LineDataSet predictionDataSet = new LineDataSet(predictionEntries, "Predicted Weight");
                predictionDataSet.setColor(Color.RED);
                predictionDataSet.setLineWidth(2f);
                predictionDataSet.setValueTextSize(12f);
                predictionDataSet.setCircleColor(Color.RED);
                predictionDataSet.setCircleRadius(4f);
                predictionDataSet.setDrawCircles(true);
                predictionDataSet.setValueTextColor(Color.WHITE);
                dataSets.add(predictionDataSet);
            } catch (IllegalArgumentException | ArithmeticException e) {
                Toast.makeText(this, "Error calculating prediction: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        // Create LineData
        LineData lineData = new LineData(dataSets);
        lineChart.setData(lineData);

        // Format x-axis with dates
        if (!currDate.isEmpty()) {
            XAxis xAxis = lineChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setValueFormatter(new DateValueFormatter(currDate, predictionDays));
            int maxLabels = currDate.size();
            xAxis.setLabelCount(maxLabels , true);
            xAxis.setGranularityEnabled(true);
            xAxis.setGranularity(1.5f);
            xAxis.setTextColor(Color.WHITE);
        } else {
            lineChart.getXAxis().setValueFormatter(null); // Fallback to indices
        }

        //Change y-axis color for dark background
        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setTextColor(Color.WHITE);
        YAxis rAxis = lineChart.getAxisRight();
        rAxis.setTextColor(Color.WHITE);

        //Change legend text white
        Legend legend = lineChart.getLegend();
        legend.setTextColor(Color.WHITE);


        lineChart.getDescription().setEnabled(false);
        lineChart.getLegend().setEnabled(true);
        lineChart.invalidate(); // Refresh chart
    }

    // Add radio functionality
    private void setupRadioGroup() {
        predictionRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioOff) {
                setupLineChart(0);
                predictedTV.setText("");
            } else if (checkedId == R.id.radio7Days && currWeight.size() >= 2) {
                setupLineChart(7);
                predictedTV.setText("Predicted weight after 7 days: " + String.format("%.2f", predictedWeight));
            } else if (checkedId == R.id.radio30Days && currWeight.size() >= 2) {
                setupLineChart(30);
                predictedTV.setText("Predicted weight after 30 days: " + String.format("%.2f",predictedWeight));
            } else {
                Toast.makeText(this, "Need more than 1 weight to predict weight", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void fillCurrWeight() {
        Cursor cursor = myDB.readWeightData();
        if (cursor == null) {
            Toast.makeText(this, "Error reading data", Toast.LENGTH_SHORT).show();
            return;
        }
        while (cursor.moveToNext()) {
            String weightValue = cursor.getString(1);
            if (weightValue != null) {
                currWeight.add(weightValue);
            }
        }
        cursor.close();
        Log.d("DB_DEBUG", "fillCurrWeight: " + currWeight.toString());
    }

    void fillCurrDate() {
        Cursor cursor = myDB.readWeightData();
        if (cursor == null) {
            Toast.makeText(this, "Error reading data", Toast.LENGTH_SHORT).show();
            return;
        }
        while (cursor.moveToNext()) {
            String dateValue = cursor.getString(3);
            if (dateValue != null) {
                LocalDate convertedDate = DateFormatter.convertStringtoDate(dateValue);
                if (convertedDate != null) {
                    currDate.add(convertedDate);
                } else {
                    Log.e("LineGraph", "Failed to parse date: " + dateValue);
                }
            }
        }
        cursor.close();
        Log.d("DB_DEBUG", "fillCurrDate: " + currDate.toString());
    }

    // Class for getting date formatted values for x axis
    // Overrides getFormattedValue from MPCharts ValueFormatter class
    private static class DateValueFormatter extends ValueFormatter {
        private final ArrayList<LocalDate> dates;
        private final int predictionDays;
        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");

        public DateValueFormatter(ArrayList<LocalDate> dates, int predictionDays) {
            this.dates = dates;
            this.predictionDays = predictionDays;
        }

        @Override
        public String getFormattedValue(float value) {
            int datePosition = (int) value;
            if (datePosition < 0 || dates.isEmpty()) {
                return "";
            }
            if (datePosition < dates.size()) {
                return dates.get(datePosition).format(formatter);
            }
            if (predictionDays > 0 && datePosition == dates.size() - 1 + predictionDays) {
                LocalDate lastDate = dates.get(dates.size() - 1);
                return lastDate.plusDays(predictionDays).format(formatter);
            }
            return "";
        }
    }
}