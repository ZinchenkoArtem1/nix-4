package ua.com.zinchenko.impl;

import ua.com.zinchenko.abstr.ConsoleService;

import java.math.BigInteger;
import java.util.Scanner;

public class DefaultConsoleService implements ConsoleService {

    private static Scanner scanner;

    @Override
    public void printHeader(String header) {
        System.out.println("----------------");
        System.out.println(header);
        System.out.println("----------------");
    }

    @Override
    public void printDefaultText(String text) {
        System.out.println(text);
    }

    @Override
    public int getIntFromConsole() {
        scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    @Override
    public String getStringFromConsole() {
        scanner = new Scanner(System.in);
        return scanner.next();
    }

    @Override
    public BigInteger getBigIntegerFromConsole() {
        scanner = new Scanner(System.in);
        return new BigInteger(scanner.next());
    }
}
