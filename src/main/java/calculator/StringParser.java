package calculator;

import extensions.InvalidInputString;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StringParser {
    private static void checkBeforeParse(String expression) {
        try {
            Validator.validate(expression);
        } catch (InvalidInputString ex) {
            System.out.println(ex);
            System.exit(1);
        }
    }
    
    public static ArrayList<String> getOpersList(String expression) {
        checkBeforeParse(expression);
        return (ArrayList<String>) Stream.of(expression.replaceAll("[0-9.]", "").split("")).collect(Collectors.toList());
    }
    
    public static ArrayList<Double> getValuesList(String expression) {
        checkBeforeParse(expression);
        return (ArrayList<Double>) Stream.of(expression.split("[*\\-+/]")).filter(s -> s != "").map(Double::parseDouble).collect(Collectors.toList());
    }
}

