package calculator;

import exceptions.InvalidInputString;

import java.math.BigDecimal;
import java.util.List;

public class Calculator {
    private static List<String> opers;
    private static List<BigDecimal> values;
    
    public static BigDecimal calculate(String expression) {
        BigDecimal result;
        opers = StringParser.getOpersList(expression);
        values = StringParser.getValuesList(expression);
        try {
            if (opers.size() == 0) {
                return values.get(0);
            }
            if (values.size() - 1 != opers.size()) {
                throw new InvalidInputString("The input string has extra operators");
            }
        } catch (InvalidInputString e) {
            System.out.println(e);
            System.exit(1);
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
}
