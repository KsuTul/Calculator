package calculator;


import exceptions.InvalidInputString;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StringParser {
    
    public static String addMultSignBeforeOpenBracket(String expression) {
        StringBuilder str = new StringBuilder(expression);
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                if (i >= 1 && Character.isDigit(expression.charAt(i - 1))) {
                    str.insert(i, '*');
                }
            }
        }
        return str.toString();
    }
    
    private static String getNewExpression(String expression) {
        StringBuilder str = new StringBuilder(expression);
        for (var i = 0; i < expression.length(); i++) {
            if (i != 0 && expression.charAt(i) == '-' && Character.isDigit(expression.charAt(i - 1))) {
                str.insert(i, '+');
            }
            if (expression.charAt(i) == '-' && expression.charAt(i + 1) == '-') {
                str.replace(i, i + 3, "+");
            }
        }
        return str.toString();
    }
    
    public static List<String> getOpersList(String expression) {
        var exp = getNewExpression(expression);
        return Stream.of(exp.replaceAll("[0-9.-]", "").split("")).filter(s -> s.intern() != "").collect(Collectors.toList());
    }
    
    public static List<BigDecimal> getValuesList(String expression) {
        var exp = getNewExpression(expression);
        return Stream.of(getNewExpression(expression).split("[*|\\|+|/|()]")).filter(s -> s.intern() != "").map(BigDecimal::new).collect(Collectors.toList());
    }
}

