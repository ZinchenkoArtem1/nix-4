package ua.com.zinchenko.domain;

import ua.com.zinchenko.Utils.MyDateTimeUtil;

public class MyDateTime {

    /**
     * Default values of date and time
     */
    public final static int DEFAULT_COUNT_YEARS = 2021;
    public final static int DEFAULT_MONTH = 1;
    public final static int DEFAULT_COUNT_DAYS = 1;
    public final static int DEFAULT_COUNT_HOURS = 0;
    public final static int DEFAULT_COUNT_MINUTES = 0;
    public final static int DEFAULT_COUNT_SECONDS = 0;
    public final static int DEFAULT_COUNT_MILLISECONDS = 0;

    /**
     * countdown from January 1 in milliseconds
     */
    private long ticks;

    /**
     * count date time units in ticks
     */
    private int years;
    private int months;
    private int days;
    private int hours;
    private int minutes;
    private int seconds;
    private int milliseconds;

    /**
     * constructor with ticks
     */
    public MyDateTime(long ticks) {
        this.ticks = ticks;
        synchronizeDateTimeWithNewCountTicks();
    }

    public void setYears(int years) {
        this.years = years;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setMilliseconds(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    /**
     * constructors with different units of date and time
     * with default values
     */
    MyDateTime() {
        this.years = DEFAULT_COUNT_YEARS;
        this.months = DEFAULT_MONTH;
        this.days = DEFAULT_COUNT_DAYS;
        this.hours = DEFAULT_COUNT_HOURS;
        this.minutes = DEFAULT_COUNT_MINUTES;
        this.seconds = DEFAULT_COUNT_SECONDS;
        this.milliseconds = DEFAULT_COUNT_MILLISECONDS;
        synchronizeTicksWithNewDateTime();
    }
    public MyDateTime(int year, int month,
                      int day, int hours, int minutes,
                      int seconds, int milliseconds) {
        this.years = year;
        this.months = month;
        this.days = day;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = milliseconds;
        synchronizeTicksWithNewDateTime();
    }

    public MyDateTime(int year, int month,
                      int day, int hours, int minutes,
                      int seconds) {
        this(year, month, day, hours, minutes, seconds, DEFAULT_COUNT_MILLISECONDS);

    }

    public MyDateTime(int year, int month,
                      int day, int hours, int minutes) {
        this(year, month, day, hours, minutes, DEFAULT_COUNT_SECONDS);
    }

    public MyDateTime(int year, int month,
                      int day, int hours) {
        this(year, month, day, hours, DEFAULT_COUNT_MINUTES);
    }

    public MyDateTime(int year, int month,
                      int day) {
        this(year, month, day, DEFAULT_COUNT_HOURS);
    }

    public MyDateTime(int year, int month) {
        this(year, month, DEFAULT_COUNT_DAYS);
    }

    public MyDateTime(int year) {
        this(year, DEFAULT_MONTH);
    }

    public int getYears() {
        return years;
    }

    public int getMonths() {
        return months;
    }

    public int getDays() {
        return days;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    public void setTicks(long ticks) {
        this.ticks = ticks;
        synchronizeDateTimeWithNewCountTicks();
    }

    public long getTicks() {
        return ticks;
    }

    private void synchronizeDateTimeWithNewCountTicks() {
        long countOfUnallocatedTicks = ticks;

        years = 0;
        while (countOfUnallocatedTicks >= MyDateTimeUtil.ONE_STANDARD_YEAR) {
            if(MyDateTimeUtil.isYearLeap(years)) {
                countOfUnallocatedTicks -= MyDateTimeUtil.ONE_LEAP_YEAR;
            } else {
                countOfUnallocatedTicks -= MyDateTimeUtil.ONE_STANDARD_YEAR;
            }
            years++;
        }

        Month[] monthsArr = Month.values();
        months = 0;
        boolean hasMonth = true;
        while (hasMonth) {
            switch (monthsArr[months].getCountDays()) {
                case 28:
                    if(MyDateTimeUtil.isYearLeap(years) && countOfUnallocatedTicks >= MyDateTimeUtil.ONE_FEBRUARY_IN_LEAP_YEAR) {
                        countOfUnallocatedTicks -= MyDateTimeUtil.ONE_FEBRUARY_IN_STANDARD_YEAR;
                        months++;
                    } else if(countOfUnallocatedTicks >= MyDateTimeUtil.ONE_FEBRUARY_IN_STANDARD_YEAR) {
                        countOfUnallocatedTicks -= MyDateTimeUtil.ONE_FEBRUARY_IN_STANDARD_YEAR;
                        months++;
                    } else {
                        hasMonth = false;
                    }
                    break;
                case 30:
                    if(countOfUnallocatedTicks >= MyDateTimeUtil.ONE_MONTH_WITH_30_DAYS) {
                        countOfUnallocatedTicks -= MyDateTimeUtil.ONE_MONTH_WITH_30_DAYS;
                        months++;
                    } else {
                        hasMonth = false;
                    }
                    break;
                case 31:
                    if(countOfUnallocatedTicks >= MyDateTimeUtil.ONE_MONTH_WITH_31_DAYS) {
                        countOfUnallocatedTicks -= MyDateTimeUtil.ONE_MONTH_WITH_31_DAYS;
                        months++;
                    } else {
                        hasMonth = false;
                    }
                    break;
            }
        }

        days = (int) (countOfUnallocatedTicks / MyDateTimeUtil.ONE_DAY);
        countOfUnallocatedTicks -= days * MyDateTimeUtil.ONE_DAY;
        hours = (int) (countOfUnallocatedTicks / MyDateTimeUtil.ONE_HOUR);
        countOfUnallocatedTicks -= hours * MyDateTimeUtil.ONE_HOUR;
        minutes = (int) (countOfUnallocatedTicks / MyDateTimeUtil.ONE_MINUTE);
        countOfUnallocatedTicks -= minutes * MyDateTimeUtil.ONE_MINUTE;
        seconds = (int) (countOfUnallocatedTicks / MyDateTimeUtil.ONE_SECOND);
        countOfUnallocatedTicks -= seconds * MyDateTimeUtil.ONE_SECOND;
        milliseconds = (int) countOfUnallocatedTicks;
    }


    private void synchronizeTicksWithNewDateTime() {
        ticks = 0;
        ticks += milliseconds;
        ticks += seconds * MyDateTimeUtil.ONE_SECOND;
        ticks += minutes * MyDateTimeUtil.ONE_MINUTE;
        ticks += hours * MyDateTimeUtil.ONE_HOUR;
        ticks += days * MyDateTimeUtil.ONE_DAY;

        for(int i = months; i > 0; i--) {
            ticks += Month.values()[i - 1].getCountDays() * MyDateTimeUtil.ONE_DAY;
        }
        if (MyDateTimeUtil.isYearLeap(years)) {
            ticks += MyDateTimeUtil.ONE_DAY;
        }

        int countLeapYears = MyDateTimeUtil.leapYearsBefore(years);
        ticks += countLeapYears * MyDateTimeUtil.ONE_LEAP_YEAR;
        ticks += (years - countLeapYears) * MyDateTimeUtil.ONE_STANDARD_YEAR;
    }
}

