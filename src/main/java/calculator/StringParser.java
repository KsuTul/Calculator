package calculator;

import exceptions.InvalidInputString;

import java.math.BigDecimal;
import java.util.List;
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
    
    public static List<String> getOpersList(String expression) {
        checkBeforeParse(expression);
        return Stream.of(expression.replaceAll("[0-9.]", "").split("")).filter(s -> s.intern() != "").collect(Collectors.toList());
    }
    
    public static List<BigDecimal> getValuesList(String expression) {
        checkBeforeParse(expression);
        return Stream.of(expression.split("[*\\-+/]")).filter(s -> s.intern() != "").map(BigDecimal::new).collect(Collectors.toList());
    }
}

