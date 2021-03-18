package ua.com.zinchenko.abstr;

public interface ConsoleService {
    void printHeader(String header);

    void printDefaultText(String text);

    int getNumberFromConsole();

    String getStringFromConsole();
}
