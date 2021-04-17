package ua.com.zinchenko;

import ua.com.zinchenko.Utils.MyDateTimeUtil;
import ua.com.zinchenko.domain.DateTimeUnit;
import ua.com.zinchenko.domain.MyDateTime;
import ua.com.zinchenko.domain.MyDateTimeFormat;
import ua.com.zinchenko.services.DateServiceImpl;

import java.util.*;

public class App {

    private final DateServiceImpl dataService;
    private final MyDateTimeFormat inputFormat;
    private final MyDateTimeFormat outputFormat;

    public App() {
        dataService = new DateServiceImpl();
        inputFormat = new MyDateTimeFormat();
        outputFormat = new MyDateTimeFormat();
    }

    public void run() {
        boolean isWork = true;
        int taskNumber;
        while (isWork) {
            Scanner scanner = new Scanner(System.in);
            loadMainMenu();
            try {
                taskNumber = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, you must input number of task from 1 to 7 include");
                continue;
            }
            switch (taskNumber) {
                case 1:
                    findDifferent();
                    break;
                case 2:
                    addToDate();
                    break;
                case 3:
                    subtractionFromDate();
                    break;
                case 4:
                    compareInAscendingOrder();
                    break;
                case 5:
                    compareInDescendingOrder();
                    break;
                case 6:
                    changeFormat();
                    break;
                case 7:
                    isWork = false;
                    break;
                default:
                    System.out.println("Invalid input, you must input number of task from 1 to 7 include");
                    break;
            }
        }
    }

    private void findDifferent(){
        Scanner scanner = new Scanner(System.in);
        MyDateTime firstDateTime;
        MyDateTime secondDateTime;

        try {
            System.out.print("Input first date(in the format: " +
                    inputFormat.getFormat() + "): ");
            firstDateTime = inputFormat.parse(scanner.nextLine());
            System.out.print("Input second date(in the format: " +
                    inputFormat.getFormat() + "): ");
            secondDateTime = inputFormat.parse(scanner.nextLine());
        } catch (RuntimeException e) {
            System.out.println("Please, input date in correct format");
            return;
        }

        boolean isWork = true;

        while (isWork) {
            scanner = new Scanner(System.in);
            System.out.println("---------------------");
            System.out.println("Choose unit for output difference between " +
                    outputFormat.format(firstDateTime) + " and " +
                    outputFormat.format(secondDateTime));
            printUnits();
            int typeOfUnitNum;
            try {
                typeOfUnitNum = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, you must input number of task from 1 to 7 include");
                continue;
            }
            switch (typeOfUnitNum) {
                case 1:
                    System.out.println("Difference between " + outputFormat.format(firstDateTime)
                    + " and " + outputFormat.format(secondDateTime) +
                            " in milliseconds is: " +
                            + dataService.calcDifferenceInMilliseconds(firstDateTime, secondDateTime));
                    break;
                case 2:
                    System.out.println("Difference between " + outputFormat.format(firstDateTime)
                            + " and " + outputFormat.format(secondDateTime) +
                            " in seconds is: " +
                            + dataService.calcDifferenceInSeconds(firstDateTime, secondDateTime));
                    break;
                case 3:
                    System.out.println("Difference between " + outputFormat.format(firstDateTime)
                            + " and " + outputFormat.format(secondDateTime) +
                            " in minutes is: " +
                            + dataService.calcDifferenceInMinutes(firstDateTime, secondDateTime));
                    break;
                case 4:
                    System.out.println("Difference between " + outputFormat.format(firstDateTime)
                            + " and " + outputFormat.format(secondDateTime) +
                            " in hours is: " +
                            + dataService.calcDifferenceInHours(firstDateTime, secondDateTime));
                    break;

                case 5:
                    System.out.println("Difference between " + outputFormat.format(firstDateTime)
                            + " and " + outputFormat.format(secondDateTime) +
                            " in years is: " +
                            + dataService.calcDifferenceInYears(firstDateTime, secondDateTime));
                    break;
                case 6:
                    System.out.println("Difference between " + outputFormat.format(firstDateTime)
                            + " and " + outputFormat.format(secondDateTime) +
                            " in centuries is: " +
                            + dataService.calcDifferenceInCenturies(firstDateTime, secondDateTime));
                    break;
                case 7:
                    isWork = false;
                    break;
                default:
                    System.out.println("Invalid input, you must input number of task from 1 to 7 include");
                    break;
            }
        }
    }

    private void addToDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input date(in the format: " +
                inputFormat.getFormat() + "): ");
        MyDateTime dateTime;
        try {
            dateTime = inputFormat.parse(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Please, input date in correct format");
            return;
        }

        boolean isWork = true;

        while (isWork) {
            scanner = new Scanner(System.in);
            System.out.println("---------------------");
            System.out.println("Choose unit for add to " +
                    outputFormat.format(dateTime));
            printUnits();
            int typeOfUnitNum;
            try {
                typeOfUnitNum = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, you must input number of task from 1 to 7 include");
                continue;
            }
            switch (typeOfUnitNum) {
                case 1:
                    System.out.print("Input milliseconds: ");
                    dataService.addToDate(dateTime, DateTimeUnit.MILLISECOND, scanner.nextInt());
                    break;
                case 2:
                    System.out.print("Input seconds: ");
                    dataService.addToDate(dateTime, DateTimeUnit.SECOND, scanner.nextInt());
                    break;
                case 3:
                    System.out.print("Input minutes: ");
                    dataService.addToDate(dateTime, DateTimeUnit.MINUTE, scanner.nextInt());
                    break;
                case 4:
                    System.out.print("Input hours: ");
                    dataService.addToDate(dateTime, DateTimeUnit.HOUR, scanner.nextInt());
                    break;
                case 5:
                    System.out.print("Input years: ");
                    dataService.addToDate(dateTime, DateTimeUnit.YEAR, scanner.nextInt());
                    break;
                case 6:
                    System.out.print("Input centuries: ");
                    dataService.addToDate(dateTime, DateTimeUnit.CENTURY, scanner.nextInt());
                    break;
                case 7:
                    isWork = false;
                    break;
                default:
                    System.out.println("Invalid input, you must input number of task from 1 to 7 include");
                    break;
            }
            System.out.println("Result is: " + outputFormat.format(dateTime));
        }
    }

    private void subtractionFromDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input date(in the format: " +
                inputFormat.getFormat() + "): ");
        MyDateTime dateTime;
        try {
            dateTime = inputFormat.parse(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Please, input date in correct format");
            return;
        }

        boolean isWork = true;

        while (isWork) {
            scanner = new Scanner(System.in);
            System.out.println("---------------------");
            System.out.println("Choose unit for subtraction from " +
                    outputFormat.format(dateTime));
            printUnits();
            int typeOfUnitNum;
            try {
                typeOfUnitNum = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, you must input number of task from 1 to 7 include");
                continue;
            }
            switch (typeOfUnitNum) {
                case 1:
                    System.out.print("Input milliseconds: ");
                    dataService.subtractionFromDateTicks(dateTime, DateTimeUnit.MILLISECOND, scanner.nextInt());
                    break;
                case 2:
                    System.out.print("Input seconds: ");
                    dataService.subtractionFromDateTicks(dateTime, DateTimeUnit.SECOND, scanner.nextInt());
                    break;
                case 3:
                    System.out.print("Input minutes: ");
                    dataService.subtractionFromDateTicks(dateTime, DateTimeUnit.MINUTE, scanner.nextInt());
                    break;
                case 4:
                    System.out.print("Input hours: ");
                    dataService.subtractionFromDateTicks(dateTime, DateTimeUnit.HOUR, scanner.nextInt());
                    break;
                case 5:
                    System.out.print("Input years: ");
                    dataService.subtractionFromDateTicks(dateTime, DateTimeUnit.YEAR, scanner.nextInt());
                    break;
                case 6:
                    System.out.print("Input centuries: ");
                    dataService.subtractionFromDateTicks(dateTime, DateTimeUnit.CENTURY, scanner.nextInt());
                    break;
                case 7:
                    isWork = false;
                    break;
                default:
                    System.out.println("Invalid input, you must input number of task from 1 to 7 include");
                    break;
            }
            System.out.println("Result is: " + outputFormat.format(dateTime));
        }
    }

    private void compareInAscendingOrder() {
        boolean isGetDateTime = true;
        List<MyDateTime> dateTimes = new ArrayList<>();

        while (isGetDateTime) {
            Scanner scanner = new Scanner(System.in);
            loadAscendingOrderMenu();
            int taskNum;
            try {
                taskNum = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, you must input number of task from 1 to 4 include");
                continue;
            }
            switch (taskNum) {
                case 1:
                    System.out.print("Input date(in the format: " +
                            inputFormat.getFormat() + "): ");
                    MyDateTime dateTime;
                    try {
                        dateTime = inputFormat.parse(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println("Please, input date in correct format");
                        break;
                    }
                    dateTimes.add(dateTime);
                    break;
                case 2:
                    System.out.println("----Unordered list------");
                    printDates(dateTimes);
                    break;
                case 3:
                    dataService.compareAscending(dateTimes);
                    System.out.println("------Ordered list----");
                    printDates(dateTimes);
                    break;
                case 4:
                    isGetDateTime = false;
                    break;
                default:
                    System.out.println("Invalid input, you must input number of task from 1 to 4 include");
                    break;
            }
        }
    }

    private void compareInDescendingOrder() {
        boolean isGetDateTime = true;
        List<MyDateTime> dateTimes = new ArrayList<>();

        while (isGetDateTime) {
            Scanner scanner = new Scanner(System.in);
            loadDescendingOrderMenu();
            int taskNum;
            try {
                taskNum = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, you must input number of task from 1 to 4 include");
                continue;
            }
            switch (taskNum) {
                case 1:
                    System.out.print("Input date(in the format: " +
                            inputFormat.getFormat() + "): ");
                    MyDateTime dateTime;
                    try {
                        dateTime = inputFormat.parse(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println("Please, input date in correct format");
                        break;
                    }
                    dateTimes.add(dateTime);
                    break;
                case 2:
                    System.out.println("----Unordered list------");
                    printDates(dateTimes);
                    break;
                case 3:
                    dataService.compareDescending(dateTimes);
                    System.out.println("------Ordered list----");
                    printDates(dateTimes);
                    break;
                case 4:
                    isGetDateTime = false;
                    break;
                default:
                    System.out.println("Invalid input, you must input number of task from 1 to 4 include");
                    break;
            }
        }
    }

    private void changeFormat() {
        boolean isWork = true;
        int taskNum;
        while (isWork) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("----Change format menu------");
            System.out.println("1. Change output format");
            System.out.println("2. Change input format");
            System.out.println("3. Back");
            System.out.print("For choose task input number of task(from 1 to 3 include): ");
            try {
                taskNum = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, you must input number of task from 1 to 3 include");
                continue;
            }

            switch (taskNum) {
                case 1:
                    outputFormat.setFormat(selectFormat());
                    break;
                case 2:
                    inputFormat.setFormat(selectFormat());
                    break;
                case 3:
                    isWork = false;
                    break;
                default:
                    System.out.println("Invalid input, you must input number of task from 1 to 3 include");
                    break;
            }
        }
    }

    private String selectFormat() {
        boolean isWork = true;
        int formatNum = 1;
        while (isWork) {
            Scanner scanner = new Scanner(System.in);
            loadFormats();
            try {
                formatNum = scanner.nextInt();
                if(formatNum < 1 || formatNum > 5) {
                    System.out.println("Invalid input, you must input number of task from 1 to 4 include");
                    continue;
                }
                isWork = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, you must input number of task from 1 to 4 include");
            }
        }

        return MyDateTimeFormat.formats[formatNum - 1];
    }

    private void loadMainMenu() {
        System.out.println("--------Main menu----------");
        System.out.println("1. Get difference between two dates");
        System.out.println("2. Add to date");
        System.out.println("3. Minus from date");
        System.out.println("4. Compare list of dates in ascending order");
        System.out.println("5. Compare list of dates in descending order");
        System.out.println("6. Change format for input/output");
        System.out.println("7. Exit app");
        System.out.print("For choose task - input number(from 1 to 7 include): ");
    }

    private void printUnits() {
        System.out.println("---------Units----------");
        System.out.println("1. Milliseconds");
        System.out.println("2. Seconds");
        System.out.println("3. Minutes");
        System.out.println("4. Hours");
        System.out.println("5. Years");
        System.out.println("6. Centuries");
        System.out.println("7. Back");
        System.out.print("For choose unit input number(from 1 to 6 include) or 7 for go back: ");
    }

    private void loadAscendingOrderMenu() {
        System.out.println("-----Ascending order menu------");
        System.out.println("1. Add new date in list for ordering");
        System.out.println("2. View all date in the list");
        System.out.println("3. Compare in ascending order");
        System.out.println("4. Back");
        System.out.print("For choose task - input number(from 1 to 4 include): ");
    }

    private void loadDescendingOrderMenu() {
        System.out.println("-----Descending order menu------");
        System.out.println("1. Add new date in list for ordering");
        System.out.println("2. View all date in the list");
        System.out.println("3. Compare in descending order");
        System.out.println("4. Back");
        System.out.print("For choose task - input number(from 1 to 4 include): ");
    }

    private void loadFormats() {
        System.out.println("----Formats------");
        for(int i = 0; i < MyDateTimeFormat.formats.length; i++) {
            System.out.println((i + 1) + " : " + MyDateTimeFormat.formats[i]);
        }
        System.out.print("For choose format - input number(from 1 to 5 include): ");
    }

    private void printDates(List<MyDateTime> dateTimes) {
        for (MyDateTime dt: dateTimes) {
            System.out.print(outputFormat.format(dt) + ", ");
        }
        System.out.println();
    }
}
