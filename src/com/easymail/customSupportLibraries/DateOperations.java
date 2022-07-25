package com.easymail.customSupportLibraries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateOperations {

    // get a string from date object
    public static String dateToString(Date date) {
        var dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(date);
    }

    // get a date object from a string
    public static Date dateFromString(String dateString) {
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            date = dateFormat.parse(dateString);
        }
        catch (ParseException ex) {
            System.out.println("Error occurred while reading the record!");
        }
        return date;
    }

    // Gives a string which contain time
    public static String dateToTimeString(Date date) {
        var dateFormat = new SimpleDateFormat("hh.mm.ss aa");
        return dateFormat.format(date);
    }

    // check whether a today is birthday
    public static boolean isTodayBirthday(Date date) {
        var today = LocalDate.now();
        var date1 = LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return (today.getMonth() == date1.getMonth() &&
                today.getDayOfMonth() == date1.getDayOfMonth());
    }

    // check whether a date is today
    public static boolean isToday(Date date) {
        var today = LocalDate.now();
        var date1 = LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return (today.isEqual(date1));
    }
}
