package calculator;

import exceptions.InvalidInputString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    
    private static String expressionTemplate = "^[0-9()-][0-9+.\\-*\\/()]*$";
    
    public static void validate(String expression) throws InvalidInputString {
        if (expression.intern() == "") {
            throw new InvalidInputString("Input string is empty");
        }
        if (!expression.matches(expressionTemplate)) {
            throw new InvalidInputString("The input string is incorrect. Make sure you enter only nums and operators or your first symbol not num/minus/brackets");
        }
        
        Pattern pattern = Pattern.compile("\\*{2}|\\+{2}|\\/{2}");
        Matcher matcher = pattern.matcher(expression);
        if (matcher.find()) {
            throw new InvalidInputString("The input string has extra opers");
        }
    }
}
