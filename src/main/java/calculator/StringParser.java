package calculator;

import exstensions.InvalidInputString;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StringParser {
    private static void checkBeforeParse(String expression){
        try {
            Validator.validate(expression);
        } catch (InvalidInputString ex) {
            System.out.println(ex);
            System.exit(1);
        }
    }

    public static ArrayList<String> getOpersList(String expression){
        checkBeforeParse(expression);
        return (ArrayList<String>) Stream.of(expression.replaceAll("[0-9]", "").split("")).collect(Collectors.toList());
    }

    public static ArrayList<Integer> getValuesList(String expression){
        checkBeforeParse(expression);
        return (ArrayList<Integer>) Stream.of(expression.split("[*\\-+/]")).map(v->Integer.parseInt(v)).collect(Collectors.toList());
    }

}

