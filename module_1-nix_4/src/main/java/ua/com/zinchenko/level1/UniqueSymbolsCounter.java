package ua.com.zinchenko.level1;

import java.util.Arrays;

public class UniqueSymbolsCounter {
    public int countUniqueSymbols(int[] numbers) {
        Arrays.sort(numbers);
        int uniqueCounter = 1;
        for(int i = 0; i < numbers.length - 1; i++) {
            if(numbers[i] != numbers[i + 1]) {
                uniqueCounter++;
            }
        }
        return uniqueCounter;
    }

}
