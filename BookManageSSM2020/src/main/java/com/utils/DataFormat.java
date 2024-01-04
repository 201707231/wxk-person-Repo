package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataFormat {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date time = new Date();

    public String currentTime(){
        String currentTime=String.format(dateFormat.format(time));
        return currentTime;
    }

    public String lastTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.YEAR, 1);
        String lastTime=String.format(dateFormat.format(calendar.getTime()));
        return lastTime;
    }

    public String currentTimeS(){
        String currentTime=String.format(dateFormat1.format(time));
        return currentTime;
    }

    public String returnTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.MONTH, 1);
        String lastTime=String.format(dateFormat1.format(calendar.getTime()));
        return lastTime;
    }

    public String addNewMonth(String timeStr){
        String addTime="";
        try {
            Date newTime = dateFormat1.parse(timeStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(newTime);
            calendar.add(Calendar.MONTH, 1);
            addTime=String.format(dateFormat1.format(calendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return addTime;
    }
}
