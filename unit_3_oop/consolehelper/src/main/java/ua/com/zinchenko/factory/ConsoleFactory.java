package ua.com.zinchenko.factory;

import org.reflections.Reflections;
import ua.com.zinchenko.abstr.ConsoleService;

import java.util.Set;

public class ConsoleFactory {
    private static ConsoleFactory instance;
    private Reflections reflections;
    private Set<Class<? extends ConsoleService>> consoleServices;

    private ConsoleFactory() {
        reflections = new Reflections("ua.com.zinchenko.impl");
        consoleServices = reflections.getSubTypesOf(ConsoleService.class);
    }

    public static ConsoleFactory getInstance() {
        if (instance == null) {
            instance = new ConsoleFactory();
        }
        return instance;
    }

    public ConsoleService getConsoleService() {
        for (Class<? extends ConsoleService> consoleService : consoleServices) {
            if (!consoleService.isAnnotationPresent(Deprecated.class)) {
                try {
                    return consoleService.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("Cannot create new instance for consoleService");
                }
            }
        }
        throw new RuntimeException("there aren't any ConsoleService in ua.com.zinchenko.impl");
    }
}
