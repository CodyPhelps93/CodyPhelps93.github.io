package com.example.weighttrackerappcodyphelps;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class LinearRegression {
    private final ArrayList<String> weightList;
    private final ArrayList<LocalDate> dateList;

    public LinearRegression(ArrayList<String> weightList, ArrayList<LocalDate> dateList) {
        this.weightList = weightList;
        this.dateList = dateList;
    }

    public double predictedWeight(int daysInFuture) {
        if (weightList.size() < 2 || dateList.size() < 2) {
            throw new IllegalArgumentException("Not enough data for prediction");
        }
        if (weightList.size() != dateList.size()) {
            throw new IllegalArgumentException("weightList and dateList must have the same size");
        }
        double firstWeight, lastWeight;
        try {
            firstWeight = Double.parseDouble(weightList.get(0));
            lastWeight = Double.parseDouble(weightList.get(weightList.size() - 1));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid weight format in weightList");
        }
        LocalDate firstDate = dateList.get(0);
        LocalDate lastDate = dateList.get(dateList.size() - 1);
        long daysBetween = ChronoUnit.DAYS.between(firstDate, lastDate);
        double slope = calculateSlope(firstWeight, lastWeight, daysBetween);
        double yIntercept = firstWeight;
        return (slope * daysInFuture) + yIntercept;
    }

    private double calculateSlope(double firstWeight, double lastWeight, long daysBetween) {
        if (daysBetween == 0) {
            throw new ArithmeticException("Cannot calculate slope: 0 days between weights");
        }
        return (lastWeight - firstWeight) / daysBetween;
    }

}