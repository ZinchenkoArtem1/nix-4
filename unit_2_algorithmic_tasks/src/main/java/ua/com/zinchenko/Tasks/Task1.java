package ua.com.zinchenko.Tasks;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {

    public int countSumOfNumbers(String randomString) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(randomString);
        int sum = 0;
        while (matcher.find()) {
            sum += Integer.parseInt(matcher.group(0));
        }
        return sum;
    }
}
