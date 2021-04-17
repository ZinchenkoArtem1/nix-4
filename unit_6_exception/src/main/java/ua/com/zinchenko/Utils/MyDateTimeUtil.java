package ua.com.zinchenko.Utils;

public class MyDateTimeUtil {

    /**
     * number of milliseconds in different
     * date and time units
     */
    public final static long ONE_SECOND = 1000;
    public final static long ONE_MINUTE = ONE_SECOND * 60;
    public final static long ONE_HOUR =  ONE_MINUTE * 60;
    public final static long ONE_DAY = ONE_HOUR * 24;

    public final static long ONE_FEBRUARY_IN_LEAP_YEAR = 29 * ONE_DAY;
    public final static long ONE_FEBRUARY_IN_STANDARD_YEAR = 28 * ONE_DAY;
    public final static long ONE_MONTH_WITH_31_DAYS = 31 * ONE_DAY;
    public final static long ONE_MONTH_WITH_30_DAYS = 30 * ONE_DAY;

    public final static long ONE_LEAP_YEAR = 366 * ONE_DAY;
    public final static long ONE_STANDARD_YEAR = 365 * ONE_DAY;

    public final static long ONE_CENTURY = 25 * ONE_LEAP_YEAR + 75 * ONE_STANDARD_YEAR;

    public static boolean isYearLeap(int year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 400 == 0) {
            return true;
        } else return year % 100 != 0;
    }

    public static int leapYearBetween(int yearBegin, int yearEnd) {
        return leapYearsBefore(yearEnd) - leapYearsBefore(yearBegin + 1);
    }

    public static int leapYearsBefore(int year) {
        year--;
        return (year / 4) - (year / 100) + (year / 400);
    }


}
