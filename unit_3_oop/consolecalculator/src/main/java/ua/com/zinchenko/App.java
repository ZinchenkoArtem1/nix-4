package ua.com.zinchenko;

import ua.com.zinchenko.abstr.CalcService;
import ua.com.zinchenko.abstr.ConsoleService;
import ua.com.zinchenko.factory.CalcFactory;
import ua.com.zinchenko.factory.ConsoleFactory;

import java.math.BigInteger;

public class App {

    private final CalcService calcService;
    private final ConsoleService consoleService;

    public App() {
        calcService = CalcFactory.getInstance().getCalcService();
        consoleService = ConsoleFactory.getInstance().getConsoleService();
    }

    public void Run() {
        boolean isWork = true;

        while (isWork) {
            LoadMenu();
            int task = consoleService.getIntFromConsole();
            BigInteger firstNum;
            BigInteger secondNum;
            BigInteger res;

            switch (task) {
                case 1:
                    consoleService.printDefaultText("Input first number:");
                    firstNum = consoleService.getBigIntegerFromConsole();
                    consoleService.printDefaultText("Input second number:");
                    secondNum = consoleService.getBigIntegerFromConsole();
                    res = calcService.sum(firstNum, secondNum);
                    PrintResult(res.toString());
                    break;
                case 2:
                    consoleService.printDefaultText("Input first number:");
                    firstNum = consoleService.getBigIntegerFromConsole();
                    consoleService.printDefaultText("Input second number:");
                    secondNum = consoleService.getBigIntegerFromConsole();
                    res = calcService.minus(firstNum, secondNum);
                    PrintResult(res.toString());
                    break;
                case 3:
                    consoleService.printDefaultText("Input first number:");
                    firstNum = consoleService.getBigIntegerFromConsole();
                    consoleService.printDefaultText("Input second number:");
                    secondNum = consoleService.getBigIntegerFromConsole();
                    res = calcService.multi(firstNum, secondNum);
                    PrintResult(res.toString());
                    break;
                case 4:
                    consoleService.printDefaultText("Input first number:");
                    firstNum = consoleService.getBigIntegerFromConsole();
                    consoleService.printDefaultText("Input second number:");
                    secondNum = consoleService.getBigIntegerFromConsole();
                    res = calcService.divide(firstNum, secondNum);
                    PrintResult(res.toString());
                    break;
                case 0:
                    isWork = false;
                    break;
                default:
                    consoleService.printDefaultText("Wrong input");
            }
        }
    }

    private void LoadMenu() {
        consoleService.printHeader("Calculator");
        consoleService.printDefaultText("1. Add");
        consoleService.printDefaultText("2. Minus");
        consoleService.printDefaultText("3. Multiply");
        consoleService.printDefaultText("4. Divide");
        consoleService.printDefaultText("0. Exit app");
        consoleService.printDefaultText("Input number of task");
    }

    private void PrintResult(String result) {
        consoleService.printDefaultText("Result of operations is: " + result);
    }
}
