package ua.com.zinchenko;

import java.util.Scanner;

public class App {

    public void Run() {

        boolean isWork = true;
        String str;
        String dest;
        int firstIndex;
        int lastIndex;
        Scanner scanner;
        while (isWork) {
            LoadMenu();
            scanner = new Scanner(System.in);
            int task = scanner.nextInt();
            switch (task) {
                case 1:
                    System.out.println("Input string");
                    scanner = new Scanner(System.in);
                    str = scanner.nextLine();
                    PrintResult(ReverseString.reverse(str));
                    break;
                case 2:
                    System.out.println("Input string");
                    scanner = new Scanner(System.in);
                    str = scanner.nextLine();
                    System.out.println("Input destination");
                    dest = scanner.next();
                    PrintResult(ReverseString.reverse(str, dest));
                    break;
                case 3:
                    System.out.println("Input string");
                    scanner = new Scanner(System.in);
                    str = scanner.nextLine();
                    System.out.println("Input first index");
                    firstIndex = scanner.nextInt();
                    System.out.println("Input last index");
                    lastIndex = scanner.nextInt();
                    PrintResult(ReverseString.reverse(str, firstIndex, lastIndex));
                    break;
                case 0:
                    isWork = false;
                    break;
                default:
                    System.out.println("Wrong input");
            }
        }
    }

    private void LoadMenu() {
        System.out.println("String revers");
        System.out.println("1. Reverse string");
        System.out.println("2. Reverse string on destination");
        System.out.println("3. Reverse string from first to last index");
        System.out.println("0. Exit app");
        System.out.println("Input number of task");
    }

    private void PrintResult(String result) {
        System.out.println("Result is: " + result);
    }
}
