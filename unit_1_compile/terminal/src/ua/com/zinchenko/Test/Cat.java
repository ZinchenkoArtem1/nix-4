package ua.com.zinchenko.Test;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Cat {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private int age;
}
