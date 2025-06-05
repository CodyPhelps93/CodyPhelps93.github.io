package com.example.weighttrackerappcodyphelps;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatter {

    /*
    * Used to convert dates from a string that comes from DB
    */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yy");

    public static LocalDate convertStringtoDate(String dateStr) {
        return LocalDate.parse(dateStr, formatter);
    }
}
