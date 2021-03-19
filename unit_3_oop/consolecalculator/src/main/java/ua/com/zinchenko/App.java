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
            int task = consoleService.getNumberFromConsole();
            BigInteger res;

            switch (task) {
                case 1:
                    res = calcService.sum(GetNumberFromUserInput(), GetNumberFromUserInput());
                    PrintResult(res.toString());
                    break;
                case 2:
                    res = calcService.minus(GetNumberFromUserInput(), GetNumberFromUserInput());
                    PrintResult(res.toString());
                    break;
                case 3:
                    res = calcService.multi(GetNumberFromUserInput(), GetNumberFromUserInput());
                    PrintResult(res.toString());
                    break;
                case 4:
                    res = calcService.divide(GetNumberFromUserInput(), GetNumberFromUserInput());
                    PrintResult(res.toString());
                    break;
                case 0:
                    isWork = false;
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

    private BigInteger GetNumberFromUserInput() {
        consoleService.printDefaultText("Input number:");
        String numInStr = consoleService.getStringFromConsole();
        return new BigInteger(numInStr);
    }

    private void PrintResult(String result) {
        consoleService.printDefaultText("Result of operations is: " + result);
    }
}
