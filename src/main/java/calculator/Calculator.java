package calculator;

import exceptions.InvalidInputString;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;


public class Calculator {
    private static List<String> opers;
    private static List<BigDecimal> values;
    
    public static BigDecimal calculate(String expression) {
        checkBeforeCalculate(expression);
        BigDecimal result = null;
        StringBuilder str = new StringBuilder(StringParser.addMultSignBeforeOpenBracket(expression));
        Pattern pattern = Pattern.compile("[\\*|\\+|\\-|\\/|\\(|\\)]");
        while (pattern.matcher(str.toString()).find()) {
            BracketsPosition brPos = getDeepestBrackets(str.toString());
            if (brPos.closeBrackets == 0) {
                result = calculateExpression(str.toString());
                return result;
            } else {
                str.replace(brPos.openBrackets, brPos.closeBrackets + 1,
                        calculateExpression(str.substring(brPos.openBrackets + 1, brPos.closeBrackets)).toString());
            }
        }
        return new BigDecimal(str.toString());
    }
    
    private static void checkBeforeCalculate(String expression) {
        try {
            Validator.validate(expression);
        } catch (InvalidInputString ex) {
            System.out.println(ex);
            System.exit(1);
        }
    }
    
    private static BigDecimal calculateExpression(String expression) {
        BigDecimal result;
        opers = StringParser.getOpersList(expression);
        values = StringParser.getValuesList(expression);
        
        if (opers.size() == 0 && values.size() > 0) {
            return values.get(0);
        }
        
        while (opers.contains("*") || opers.contains("/")) {
            calculateAction("*");
            try {
                calculateAction("/");
            } catch (ArithmeticException e) {
                System.out.println("In this statement we divide on zero");
                System.exit(1);
            }
        }
        while (opers.contains("+") || opers.contains("-")) {
            calculateAction("+");
            calculateAction("-");
        }
        result = values.get(0);
        return result;
    }
    
    private static void calculateAction(String action) throws ArithmeticException {
        int currentIndex = opers.indexOf(action);
        int nextIndex = currentIndex + 1;
        if (currentIndex == -1) {
            return;
        }
        BigDecimal result;
        switch (action) {
            case "+":
                result = values.get(currentIndex).add(values.get(nextIndex));
                beforeNextStep(currentIndex, result);
                break;
            case "-":
                result = values.get(currentIndex).subtract(values.get(nextIndex));
                beforeNextStep(currentIndex, result);
                break;
            case "*":
                result = values.get(currentIndex).multiply(values.get(nextIndex));
                beforeNextStep(currentIndex, result);
                break;
            case "/":
                result = values.get(currentIndex).divide(values.get(nextIndex));
                beforeNextStep(currentIndex, result);
                break;
        }
    }
    
    private static void beforeNextStep(int currentIndex, BigDecimal tempRes) {
        values.subList(currentIndex, currentIndex + 2).clear();
        values.add(currentIndex, tempRes);
        opers.remove(currentIndex);
    }
    
    private static BracketsPosition getDeepestBrackets(String expression) {
        var deepestOpenPos = 0;
        var deepestClosePos = 0;
        var currentDepth = 0;
        var maxDepth = 0;
        var closed = true;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                currentDepth++;
                if (currentDepth > maxDepth) {
                    deepestOpenPos = i;
                    maxDepth = currentDepth;
                    closed = false;
                }
            }
            if (expression.charAt(i) == ')') {
                if (!closed) {
                    deepestClosePos = i;
                    closed = true;
                }
                currentDepth--;
            }
        }
        
        return new BracketsPosition(deepestOpenPos, deepestClosePos);
    }
    
    private static class BracketsPosition {
        public int openBrackets;
        public int closeBrackets;
    
        public BracketsPosition(int openBrackets, int closeBrackets) {
            this.openBrackets = openBrackets;
            this.closeBrackets = closeBrackets;
        }
    }
    
}
