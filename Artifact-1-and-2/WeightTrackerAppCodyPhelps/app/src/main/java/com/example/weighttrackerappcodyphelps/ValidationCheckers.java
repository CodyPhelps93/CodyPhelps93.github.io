package com.example.weighttrackerappcodyphelps;

import android.content.Context;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ValidationCheckers {
    private Context context;
    public ValidationCheckers(Context context){
        this.context = context;
    }
    public boolean isValidDateFormat(String date) {
        if (!date.matches("^[0-9]{2}-[0-9]{2}-[0-9]{2}$")){
            Toast.makeText(context, "Invalid Date entered: " + date, Toast.LENGTH_SHORT).show();
            return false;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy");
        dateFormat.setLenient(false);

        try{
            dateFormat.parse(date);
            return true;
        }catch (ParseException e) {
            Toast.makeText(context, "Invalid Date entered: " + date, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean isValidInteger(String input) {
         if (!input.matches("^[0-9]+$")) {
             Toast.makeText(context, "Input must only contain numbers", Toast.LENGTH_SHORT).show();
             return false;
         }
         return true;
    }
}
