package ua.com.zinchenko.abstr;

import java.math.BigInteger;

public interface ConsoleService {
    void printHeader(String header);

    void printDefaultText(String text);

    int getIntFromConsole();

    String getStringFromConsole();

    BigInteger getBigIntegerFromConsole();
}
