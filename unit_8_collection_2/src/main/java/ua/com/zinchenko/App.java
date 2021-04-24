package ua.com.zinchenko;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {
    public void run() {
        boolean isWork = true;
        MathSet<Integer> testSet = new MathSet<>();
        while (isWork) {
            loadMenu();
            int exp;
            Scanner scanner = new Scanner(System.in);
            try {
                exp = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You must input number of task(from 1 to 6 include)");
                continue;
            }
            int num;
            switch (exp) {
                case 1:
                    System.out.print("Input new element(integer) for add to set: ");
                    try {
                        num = scanner.nextInt();
                        testSet.add(num);
                        System.out.println("Input is complete");
                    } catch (NoSuchElementException e) {
                        System.out.println("Input is invalid, you must input integer value");
                    }
                    break;
                case 2:
                    if(testSet.size() == 0) {
                        System.out.println("List is empty");
                        break;
                    }
                    for(int i = 0; i < testSet.size(); i++) {
                        System.out.println("Index: " + (i) +
                                " => Element: " + testSet.get(i));
                    }
                    break;
                case 3:
                    testSet.sortAsc();
                    System.out.println("Set is sorted");
                    break;
                case 4:
                    testSet.sortDesc();
                    System.out.println("Set is sorted");
                    break;
                case 5 :
                    double median = testSet.getMedian();
                    System.out.println("Median is: " + median);
                    break;
                case 6:
                    double average = testSet.getAverage().doubleValue();
                    System.out.println("Average is: " + average);
                case 7:
                    testSet.clear();
                    break;
                case 8:
                    isWork = false;
                    break;
                default:
                    System.out.println("You must input number of task(from 1 to 6 include)");
                    break;
            }
        }
    }

    private void loadMenu() {
        System.out.println("It's console app for test MathSet");
        System.out.println("1. Add new element");
        System.out.println("2. Get all elements");
        System.out.println("3. Sort asc");
        System.out.println("4. Sort desc");
        System.out.println("5. Get median");
        System.out.println("6. Get average");
        System.out.println("7. Clear all set");
        System.out.println("8. Exit app");
        System.out.print("For choose task, input number of tasks from above: ");
    }
}
