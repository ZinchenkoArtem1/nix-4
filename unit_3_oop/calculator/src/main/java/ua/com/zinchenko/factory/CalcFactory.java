package ua.com.zinchenko.factory;

import org.reflections.Reflections;
import ua.com.zinchenko.abstr.CalcService;

import java.util.Set;

public class CalcFactory {
    private static CalcFactory instance;
    private Reflections reflections;
    private Set<Class<? extends CalcService>> calcServices;

    private CalcFactory() {
        reflections = new Reflections("ua.com.zinchenko.impl");
        calcServices = reflections.getSubTypesOf(CalcService.class);
    }

    public static CalcFactory getInstance() {
        if (instance == null) {
            instance = new CalcFactory();
        }
        return instance;
    }

    public CalcService getCalcService() {
        for (Class<? extends CalcService> calcService : calcServices) {
            if (!calcService.isAnnotationPresent(Deprecated.class)) {
                try {
                    return calcService.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("Cannot create new instance for calcService");
                }
            }
        }
        throw new RuntimeException("there aren't any CalcService in ua.com.zinchenko.impl");
    }
}
