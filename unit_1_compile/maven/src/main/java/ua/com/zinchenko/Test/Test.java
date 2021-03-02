package ua.com.zinchenko.Test;

import org.apache.commons.lang3.*;

public class Test {

    public void run() {
        Cat cat = new Cat();
        cat.setName("Murzik");
        cat.setAge(3);
        System.out.println(StringUtils.upperCase(cat.toString()));
    }
}
