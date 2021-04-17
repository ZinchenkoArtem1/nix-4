package ua.com.zinchenko;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {
    public void run() {
        boolean isWork = true;
        List<Integer> testList = new OrderedList<>();
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
            int index;
            int num;
            switch (exp) {
                case 1:
                    System.out.print("Input new element(integer) for add to list: ");
                    try {
                        num = scanner.nextInt();
                        testList.add(num);
                        System.out.println("Input is complete");
                    } catch (NoSuchElementException e) {
                        System.out.println("Input is invalid, you must input integer value");
                    }
                    break;
                case 2:
                    System.out.print("Input index of element, for find him: ");
                    index = scanner.nextInt();
                    try {
                        num = testList.get(index);
                        System.out.println("Index: " + index +
                                " => Element: " + num);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("List don't have this index: " + index);
                    }
                    break;
                case 3:
                    System.out.print("Input index of element, for remove him: ");
                    index = scanner.nextInt();
                    try {
                        num = testList.remove(index);
                        System.out.println("Remove is complete");
                        System.out.print("Removed element: ");
                        System.out.println("Index: " + index +
                                " => Element: " + num);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("List don't have this index: " + index);
                    }
                    break;
                case 4:
                    if(testList.isEmpty()) {
                        System.out.println("List is empty");
                        break;
                    }
                    index = 0;
                    for (Integer i: testList) {
                        System.out.println("Index: " + (index++) +
                                " => Element: " + i);
                    }
                    break;
                case 5:
                    testList.clear();
                    System.out.println("Clear is complete");
                    break;
                case 6:
                    isWork = false;
                    break;
                default:
                    System.out.println("You must input number of task(from 1 to 6 include)");
                    break;
            }
        }
    }

    private void loadMenu() {
        System.out.println("It's console app for test OrderedList with integer elements");
        System.out.println("1. Add new element");
        System.out.println("2. Get element by index");
        System.out.println("3. Remove element by index");
        System.out.println("4. Get all elements");
        System.out.println("5. Clear list");
        System.out.println("6. Exit app");
        System.out.print("For choose task, input number of tasks from above: ");
    }
}
