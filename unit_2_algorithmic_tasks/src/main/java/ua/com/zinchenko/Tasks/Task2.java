package ua.com.zinchenko.Tasks;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Stream;

public class Task2 {

    public SortedMap<Character, Integer> getMapWithSymbolsCount(String randomString) {
        SortedMap<Character, Integer> map = new TreeMap<>();

        char[] chars = randomString.toCharArray();
        Arrays.sort(chars);

        for (Character c : chars) {
            if (Character.isAlphabetic(c)) {
                map.merge(c, 1, Integer::sum);
            }
        }
        return map;
    }
}
