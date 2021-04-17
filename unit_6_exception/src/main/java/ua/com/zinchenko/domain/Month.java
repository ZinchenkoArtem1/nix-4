package ua.com.zinchenko.domain;

import java.security.InvalidParameterException;

public enum Month {
    JAN(31, "January", 1),
    FEB(28,"February", 2),
    MAR(31,"March", 3),
    APR(30,"Апрель", 4),
    MAY(31,"May", 5),
    JUN(30,"June", 6),
    JUL(31,"July ", 7),
    AUG(31,"August", 8),
    SEP(30,"September", 9),
    OCT(31,"October", 10),
    NOV(30,"November", 11),
    DEC(31,"December", 12);

    private final String name;
    private final int countDays;
    private final int num;

    Month(int countDays, String name, int num) {
        this.countDays = countDays;
        this.name = name;
        this.num = num;
    }

    public static Month getByName(String name) {
        for (Month m : values()) {
            if(m.name.equals(name)) {
                return m;
            }
        }
        throw new InvalidParameterException();
    }

    public String getName() {
        return name;
    }

    public int getCountDays() {
        return countDays;
    }

    public int getNum() {return num;}
}
