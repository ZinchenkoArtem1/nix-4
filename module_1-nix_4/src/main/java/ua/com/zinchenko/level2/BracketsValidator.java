package ua.com.zinchenko.level2;

import java.util.Stack;

public class BracketsValidator {
    public boolean isBracketsRight(String input) {
        Stack<Character> stack = new Stack<>();
        int lengthInput = input.length();

        for (int i = 0; i < lengthInput; i++) {
            char ch = input.charAt(i);

            switch (ch) {
                case '{' :
                case '[':
                case '(':
                    stack.push(ch);
                    break;
                case '}':
                case ']':
                case ')':
                    if (!stack.isEmpty()) {
                        char lastChInStack = stack.pop();
                        if (!isBracketsMatch(lastChInStack, ch))
                            return false;
                    } else {
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }
        return stack.isEmpty();
    }

    private boolean isBracketsMatch(char openBracket, char closeBracket) {
        return openBracket == '(' && closeBracket == ')'||
                openBracket == '{' && closeBracket == '}' ||
                openBracket == '[' && closeBracket == ']';
    }
}
