package ua.com.zinchenko;

public class ReverseString {

    public static String reverse(String str) {
        StringBuilder result = new StringBuilder();
        final String[] words = str.split(" ");
        for(String word : words) {
            result.append(ReverseString.reverseWord(word)).append(" ");
        }
        return result.toString();
    }

    public static String reverse(String str, String dest) {
        return str.replace(dest, ReverseString.reverseWord(dest));
    }

    public static String reverse(String str, int firstIndex, int lastIndex) {
        return str.substring(0, firstIndex) +
                ReverseString.reverseWord(str.substring(firstIndex, lastIndex)) +
                str.substring(lastIndex);
    }


    private static String reverseWord(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            result.insert(0, str.charAt(i));
        }
        return result.toString();
    }
}
