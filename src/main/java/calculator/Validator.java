package calculator;

import exceptions.InvalidInputString;

public class Validator {
    
    private static String expressionTemplate = "^[0-9+.\\-*\\/]*$";
    private static String[] invalidSymbols = new String[]{"(", ")", ","};
    
    public static void validate(String expression) throws InvalidInputString {
        if (expression.intern() == "") {
            throw new InvalidInputString("Input string is empty");
        }
        if (!expression.matches(expressionTemplate)) {
            throw new InvalidInputString("The input string is incorrect. Make sure you enter only nums and operators.");
        }
    }
}
