package calculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private static ArrayList<String> opers;
    private static ArrayList<Double> values;
    
    public static Double calculate(String expression) {
        double result;
        opers = StringParser.getOpersList(expression);
        values = StringParser.getValuesList(expression);
        
        if (values.size() - 1 != opers.size()) {
            System.out.println("The input string has extra operators");
            System.exit(1);
        }
        while (opers.contains("*") || opers.contains("/")) {
            calculateAction("*");
            calculateAction("/");
        }
        while (opers.contains("+") || opers.contains("-")) {
            calculateAction("+");
            calculateAction("-");
        }
        result = values.get(0);
        return result;
    }
    
    private static void calculateAction(String action) {
        int currentIndex = opers.indexOf(action);
        int nextIndex = currentIndex + 1;
        if (currentIndex == -1) {
            return;
        }
        double result = 0;
        switch (action) {
            case "+" -> {
                result = values.get(currentIndex) + values.get(nextIndex);
                beforeNextStep(opers, values, currentIndex, nextIndex, result);
            }
            case "-" -> {
                result = values.get(currentIndex) - values.get(nextIndex);
                beforeNextStep(opers, values, currentIndex, nextIndex, result);
            }
            case "*" -> {
                result = values.get(currentIndex) * values.get(nextIndex);
                beforeNextStep(opers, values, currentIndex, nextIndex, result);
            }
            case "/" -> {
                try {
                    result = values.get(currentIndex) / values.get(nextIndex);
                } catch (ArithmeticException e) {
                    System.out.println("In this statement we divide on zero");
                    System.exit(1);
                }
                beforeNextStep(opers, values, currentIndex, nextIndex, result);
            }
        }
    }
    
    private static void beforeNextStep(List<String> opers, List<Double> values, int currentIndex, int nextIndex,
                                       double tempRes) {
        values.subList(currentIndex, nextIndex + 1).clear();
        values.add(currentIndex, tempRes);
        opers.remove(currentIndex);
    }
}
