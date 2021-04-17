package ua.com.zinchenko.domain;

import ua.com.zinchenko.Utils.MyDateTimeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyDateTimeFormat {

    private static final String STANDARD_FORMAT = "dd/mm/yy/h/m/s/ms";

    private String format;

    public static String[] formats = {
            "dd/mm/yy",
            "m/d/yyyy",
            "mmm-d-yy",
            "dd-mmm-yyyy h:m:s",
            "m:d:yyyy h:m:s"
    };

    //default format
    public MyDateTimeFormat() {
        format = STANDARD_FORMAT;
    }

    public MyDateTimeFormat(String format) { this.format = format; }

    //set new format by user
    public void setFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
    // convert ticks to string in format
    public String format(MyDateTime dateTime) {
        String[] ft = format.split("\\s|:|-|/");

        String[] ft1 = format.split("m+|d+|y+|h+|s+");

        StringBuilder date = new StringBuilder();

        for(int i = 0; i < 3; i++) {
            switch (ft[i]) {
                case "d":
                    date.append(ft1[i]).append(dateTime.getDays());
                    break;
                case "dd":
                    date.append(ft1[i]).append(String.valueOf(dateTime.getDays()).length() == 1 ?
                            "0" + dateTime.getDays() : dateTime.getDays());
                    break;
                case "m":
                    date.append(ft1[i]).append(dateTime.getMonths());
                    break;
                case "mm":
                    date.append(ft1[i]).append(String.valueOf(dateTime.getMonths()).length() == 1 ?
                            "0" + dateTime.getMonths() : dateTime.getMonths());
                    break;
                case "mmm":
                    date.append(ft1[i]).append(Month.values()[dateTime.getMonths() - 1].getName());
                    break;
                case "yy":
                    date.append(ft1[i]).append(dateTime.getYears() - ((dateTime.getYears() / 100) * 100));
                    break;
                case "yyyy":
                    date.append(ft1[i]).append(dateTime.getYears());
                    break;
            }
        }

        if (ft.length > 3) {
            date.append(ft1[3]).append(dateTime.getHours());
        }
        if (ft.length > 4) {
            date.append(ft1[4]).append(dateTime.getMinutes());
        }
        if (ft.length > 5) {
            date.append(ft1[5]).append(dateTime.getSeconds());
        }
        if (ft.length > 6) {
            date.append(ft1[6]).append(dateTime.getMilliseconds());
        }


        return date.toString();
    }

    public MyDateTime parse(String dateTime) {

        String[] date = dateTime.split("\\s|:|-|/");
        String[] ft = format.split("\\s|:|-|/");
        int formatSize = ft.length;

        int years = MyDateTime.DEFAULT_COUNT_YEARS;
        int months = MyDateTime.DEFAULT_MONTH;
        int days = MyDateTime.DEFAULT_COUNT_DAYS;
        int hours = MyDateTime.DEFAULT_COUNT_HOURS;
        int minutes = MyDateTime.DEFAULT_COUNT_MINUTES;
        int seconds = MyDateTime.DEFAULT_COUNT_SECONDS;
        int milliseconds = MyDateTime.DEFAULT_COUNT_MILLISECONDS;

        for(int i = 0; i < 3; i++) {
            switch (ft[i]) {
                case "d":
                case "dd":
                    days = (Integer.parseInt(date[i]));
                    break;
                case "m":
                case "mm":
                    months = Integer.parseInt(date[i]);
                    break;
                case "mmm":
                    months = Month.getByName(date[i]).getNum();
                    break;
                case "yy":
                    years = (Integer.parseInt("20" + date[i]));
                    break;
                case "yyyy":
                    years = (Integer.parseInt(date[i]));
                    break;
            }
        }

        if(formatSize > 3) {
            hours = (Integer.parseInt(date[3]));
        }
        if(formatSize > 4) {
            minutes = (Integer.parseInt(date[4]));
        }
        if(formatSize > 5) {
            seconds = (Integer.parseInt(date[5]));
        }
        if(formatSize > 6) {
            milliseconds = (Integer.parseInt(date[6]));
        }

        return new MyDateTime(years, months, days, hours, minutes, seconds, milliseconds);
     }
}
