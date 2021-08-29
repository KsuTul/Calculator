package calculator;

import exceptions.InvalidInputString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    
    private final static String EXPRESSION_TEMP = "^[0-9()-][0-9+.\\-*/()]*$";
    private final static String INCORRECT_SYMB_AMOUNT = "\\*{2}|\\+{2}|/{2}";
    
    public static void validate(String expression) throws InvalidInputString {
        if (expression.intern().equals("")) {
            throw new InvalidInputString("Input string is empty");
        }
        if (!expression.matches(EXPRESSION_TEMP)) {
            throw new InvalidInputString("The input string is incorrect. Make sure you enter only nums and operators " +
                    "or your first symbol not num/minus/brackets");
        }
        
        Pattern pattern = Pattern.compile(INCORRECT_SYMB_AMOUNT);
        Matcher matcher = pattern.matcher(expression);
        if (matcher.find()) {
            throw new InvalidInputString("The input string has extra opers");
        }
    }
}
