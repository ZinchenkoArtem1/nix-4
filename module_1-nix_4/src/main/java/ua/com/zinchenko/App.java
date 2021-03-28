package ua.com.zinchenko;

import ua.com.zinchenko.level1.ChessResolver;
import ua.com.zinchenko.level1.Triangle;
import ua.com.zinchenko.level1.UniqueSymbolsCounter;
import ua.com.zinchenko.level2.BracketsValidator;
import ua.com.zinchenko.level3.GameOfLife;

import java.util.Arrays;
import java.util.Scanner;

public class App {
    public void Run() {

        boolean isWork = true;
        Scanner scanner;
        while (isWork) {
            LoadMenu();
            scanner = new Scanner(System.in);
            int task = scanner.nextInt();
            switch (task) {
                case 1:
                    System.out.println("Input array of numbers(Example: 1 5 6 4 2)");
                    scanner = new Scanner(System.in);
                    String input = scanner.nextLine();
                    int[] numbers = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
                    UniqueSymbolsCounter uniqueSymbolsCounter = new UniqueSymbolsCounter();
                    System.out.println("Result: " + uniqueSymbolsCounter.countUniqueSymbols(numbers));
                    break;
                case 2:
                    System.out.println("Input in turn x1, y1, x2, y2");
                    scanner = new Scanner(System.in);
                    System.out.print("Input x1: ");
                    int x1 = scanner.nextInt();
                    System.out.print("Input y1: ");
                    int y1 = scanner.nextInt();
                    System.out.print("Input x2: ");
                    int x2 = scanner.nextInt();
                    System.out.print("Input y2: ");
                    int y2 = scanner.nextInt();
                    ChessResolver chessResolver = new ChessResolver();
                    if(chessResolver.isMoveHorse(x1, y1, x2, y2)) {
                        System.out.println("Horse can move");
                        break;
                    }
                    System.out.println("Horse cannot move");
                    break;
                case 3:
                    System.out.println("Input in turn ax, ay, bx, by, cx, cy");
                    scanner = new Scanner(System.in);
                    System.out.print("Input ax: ");
                    int ax = scanner.nextInt();
                    System.out.print("Input ay: ");
                    int ay = scanner.nextInt();
                    System.out.print("Input bx: ");
                    int bx = scanner.nextInt();
                    System.out.print("Input by: ");
                    int by = scanner.nextInt();
                    System.out.print("Input cx: ");
                    int cx = scanner.nextInt();
                    System.out.print("Input cy: ");
                    int cy = scanner.nextInt();
                    Triangle triangle = new Triangle(ax, ay, bx, by, cx, cy);
                    System.out.println("Square is: " + triangle.getSquare());
                    break;
                case 4:
                    scanner = new Scanner(System.in);
                    System.out.println("Input string for validation");
                    String str = scanner.nextLine();
                    BracketsValidator bracketsValidator = new BracketsValidator();
                    if (bracketsValidator.isBracketsRight(str)) {
                        System.out.println("String is correct");
                    }
                    else {
                        System.out.println("String is incorrect");
                    }
                    break;
                case 5:
                    GameOfLife gameOfLife = new GameOfLife();
                    int[][] board = {};
                    boolean isGameRun = true;
                    int gameStep;
                    while (isGameRun) {
                        System.out.println("Input number of task in game");
                        System.out.println("1. Generate new table");
                        System.out.println("2. Do one step");
                        System.out.println("0. Exit game");
                        scanner = new Scanner(System.in);
                        gameStep = scanner.nextInt();
                        switch (gameStep) {
                            case 1:
                                System.out.println("Input count of rows");
                                int rows = scanner.nextInt();
                                System.out.println("Input count of columns");
                                int columns = scanner.nextInt();
                                board = gameOfLife.generateRandomBoard(rows, columns);
                                System.out.println("Generated board");
                                gameOfLife.printBoard(board);
                                break;
                            case 2:
                                System.out.println("Old board");
                                gameOfLife.printBoard(board);
                                board = gameOfLife.getNextState(board);
                                System.out.println("New board");
                                gameOfLife.printBoard(board);
                                break;
                            case 3:
                                isGameRun = false;
                                break;
                        }
                    }
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
        System.out.println("Module 1 test");
        System.out.println("LEVEL 1");
        System.out.println("1. Count unique symbols in array of numbers");
        System.out.println("2. Can chess horse move?");
        System.out.println("3. Count square of triangle");
        System.out.println("LEVEL 2");
        System.out.println("4. Validate string on brackets match");
        System.out.println("LEVEL 3");
        System.out.println("5. Game of life");
        System.out.println("0. Exit app");
        System.out.println("Input number of task");
    }
}
