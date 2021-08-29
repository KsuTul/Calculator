package calculator;

import java.util.Scanner;

public class IOExpression {
    public static String InputExpression(Scanner scanner) {
        return scanner.nextLine().replaceAll(" ", "");
    }
    
    public static void OutputExpression(String expression) {
        String result = "";
        try{
            result = Calculator.calculate(expression);
        }catch (Exception ex){
            System.out.println(ex);
        }
        System.out.println(result);
    }
}
