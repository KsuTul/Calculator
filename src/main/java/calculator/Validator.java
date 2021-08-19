package calculator;

import extensions.InvalidInputString;

public class Validator {

    private static String expressionTemplate = "^[0-9]*[0-9+.\\-*\\/]*[0-9]$";
    private static String[] invalidSymbols = new String[]{"(", ")", ","};

    public static void validate(String expression) throws InvalidInputString {
            if(expression.matches(expressionTemplate) == false){
                throw new InvalidInputString("The input string is incorrect. Make sure you enter only nums and operators.");
            }

            for(String i: invalidSymbols){
                if(expression.contains(i)){
                    throw new InvalidInputString("The input string contains invalid symbol"+ i);
                }
            }
    }
}
