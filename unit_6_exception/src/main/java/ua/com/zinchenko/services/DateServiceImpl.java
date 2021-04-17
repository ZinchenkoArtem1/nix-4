package ua.com.zinchenko.services;

import ua.com.zinchenko.domain.DateTimeUnit;
import ua.com.zinchenko.domain.MyDateTime;
import ua.com.zinchenko.Utils.MyDateTimeUtil;

import javax.management.ObjectName;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DateServiceImpl {

    public long calcDifferenceInMilliseconds(MyDateTime dateTime1, MyDateTime dateTime2) {
        long seconds = calcDifferenceInSeconds(dateTime1, dateTime2);
        return seconds * 1000 + Math.abs(dateTime1.getMilliseconds() - dateTime2.getMilliseconds());
    }

    public long calcDifferenceInSeconds(MyDateTime dateTime1, MyDateTime dateTime2) {
        long minutes = calcDifferenceInMinutes(dateTime1, dateTime2);
        return minutes * 60 + Math.abs(dateTime1.getMinutes() - dateTime2.getMinutes());
    }

    public long calcDifferenceInMinutes(MyDateTime dateTime1, MyDateTime dateTime2) {
        long hours = calcDifferenceInHours(dateTime1, dateTime2);
        return hours * 60 + Math.abs(dateTime1.getMinutes() - dateTime2.getMinutes());
    }

    public long calcDifferenceInHours(MyDateTime dateTime1, MyDateTime dateTime2) {
        long days = calcDifferenceInDays(dateTime1, dateTime2);
        return days * 24 + Math.abs(dateTime1.getHours() - dateTime2.getHours());
    }

    public int calcDifferenceInDays(MyDateTime dateTime1, MyDateTime dateTime2) {
        int leapYearsBetween = MyDateTimeUtil.leapYearBetween(dateTime1.getYears(), dateTime2.getYears());
        int standardYearsBetween = calcDifferenceInYears(dateTime1, dateTime2) - leapYearsBetween;
        return leapYearsBetween * 366 + standardYearsBetween * 365 + Math.abs(dateTime1.getDays() - dateTime2.getDays());
    }

    public int calcDifferenceInYears(MyDateTime dateTime1, MyDateTime dateTime2) {
        return Math.abs(dateTime1.getYears() - dateTime2.getYears());
    }

    public int calcDifferenceInCenturies(MyDateTime dateTime1, MyDateTime dateTime2) {
        return  Math.abs(dateTime1.getYears() - dateTime2.getYears()) / 100;
    }


    public void addToDate(MyDateTime dateTime, DateTimeUnit unitType, int value) {
        switch (unitType) {
            case MILLISECOND:
                dateTime.setTicks(dateTime.getTicks() + value);
                break;
            case SECOND:
                dateTime.setTicks(dateTime.getTicks() + value * MyDateTimeUtil.ONE_SECOND);
                break;
            case MINUTE:
                dateTime.setTicks(dateTime.getTicks() + value * MyDateTimeUtil.ONE_MINUTE);
                break;
            case HOUR:
                dateTime.setTicks(dateTime.getTicks() + value * MyDateTimeUtil.ONE_HOUR);
                break;
            case YEAR:
                int countLeapYears = MyDateTimeUtil.leapYearBetween(dateTime.getYears(),
                        dateTime.getYears() + value);
                dateTime.setTicks(dateTime.getTicks() +
                        countLeapYears * MyDateTimeUtil.ONE_LEAP_YEAR +
                        (value - countLeapYears) * MyDateTimeUtil.ONE_STANDARD_YEAR);
                break;
            case CENTURY:
                dateTime.setTicks(dateTime.getTicks() + value * MyDateTimeUtil.ONE_CENTURY);
                break;
        }
    }

    public void subtractionFromDateTicks(MyDateTime dateTime, DateTimeUnit unitType, int value) {
        switch (unitType) {
            case MILLISECOND:
                dateTime.setTicks(dateTime.getTicks() - value);
                break;
            case SECOND:
                dateTime.setTicks(dateTime.getTicks() - value * MyDateTimeUtil.ONE_SECOND);
                break;
            case MINUTE:
                dateTime.setTicks(dateTime.getTicks() - value * MyDateTimeUtil.ONE_MINUTE);
                break;
            case HOUR:
                dateTime.setTicks(dateTime.getTicks() - value * MyDateTimeUtil.ONE_HOUR);
                break;
            case YEAR:
                int countLeapYears = MyDateTimeUtil.leapYearBetween(dateTime.getYears(),
                        dateTime.getYears() - value);
                dateTime.setTicks(dateTime.getTicks() -
                        countLeapYears * MyDateTimeUtil.ONE_LEAP_YEAR -
                        (value - countLeapYears) * MyDateTimeUtil.ONE_STANDARD_YEAR);
                break;
            case CENTURY:
                dateTime.setTicks(dateTime.getTicks() - value * MyDateTimeUtil.ONE_CENTURY);
                break;
        }
    }

    public void compareAscending(List<MyDateTime> dateTimes) {
        dateTimes.sort((o1, o2) -> {
            if (o1.getTicks() - o2.getTicks() > 0) {
                return 1;
            } else if (o1.getTicks() - o2.getTicks() < 0) {
                return -1;
            } else {
                return 0;
            }
        });
    }

    public void compareDescending(List<MyDateTime> dateTimes) {
        dateTimes.sort((o1, o2) -> {
            if (o1.getTicks() - o2.getTicks() > 0) {
                return -1;
            } else if (o1.getTicks() - o2.getTicks() < 0) {
                return 1;
            } else {
                return 0;
            }
        });
    }
}
