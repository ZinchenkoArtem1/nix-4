package ua.com.zinchenko;

import org.apache.commons.lang3.RandomStringUtils;
import ua.com.zinchenko.Tasks.Task1;
import ua.com.zinchenko.Tasks.Task2;
import ua.com.zinchenko.Tasks.Task3;

import java.util.Map;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Task1 task1 = new Task1();
        Task2 task2 = new Task2();
        Task3 task3 = new Task3();
        boolean isAppWork = true;
        String randomString;
        String inputString;
        int lessonsCount;
        while(isAppWork) {
            loadMenu();
            Scanner sc = new Scanner(System.in);
            System.out.print("---> ");
            int number = sc.nextInt();
            switch (number) {
                case 1:
                    randomString = getRandomString();
                    System.out.println("Random string is: " + randomString);
                    System.out.println("Sum of number in string is: " +
                            task1.countSumOfNumbers(randomString));
                    break;
                case 2:
                    System.out.print("Input string: ");
                    inputString = sc.next();
                    System.out.println("Sum of number in string is: " +
                            task1.countSumOfNumbers(inputString));
                    break;
                case 3:
                    randomString = getRandomString();
                    System.out.println("Random string is: " + randomString);
                    printTask2Result(task2.getMapWithSymbolsCount(randomString));
                    break;
                case 4:
                    System.out.println("Input string: ");
                    inputString = sc.next();
                    printTask2Result(task2.getMapWithSymbolsCount(inputString));
                    break;
                case 5:
                    System.out.println("Input lessons count");
                    lessonsCount = sc.nextInt();
                    System.out.println("Lessons end in: " + task3.getTimeEndLessonInStrFormat(lessonsCount));
                    break;
                case 0:
                    isAppWork = false;
                    break;
                default:
                    System.out.println("No valid input");
                    break;
            }
        }
    }
    public static void loadMenu() {
        System.out.println("Choose task and data for test:");
        System.out.println("1. (Task1) Count sum numbers in random string");
        System.out.println("2. (Task1) Count sum numbers in input string");
        System.out.println("3. (Task2) Get count of each symbol in random string");
        System.out.println("4. (Task2)  Get count of each symbol in input string");
        System.out.println("5. (Task3) Get time of end lessons");
        System.out.println("0. Exit app");
    }

    public static String getRandomString() {
        return RandomStringUtils.randomAscii(10, 20);
    }

    public static void printTask2Result(Map<Character, Integer> map) {
        System.out.println("Count of each symbols in string");
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            System.out.println(entry.getValue() + " : " + entry.getKey());
        }
    }
}
