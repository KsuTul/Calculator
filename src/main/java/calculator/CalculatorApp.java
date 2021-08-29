package calculator;

import java.util.Scanner;

public class CalculatorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Put expression : ");
        while (scanner.hasNextLine()) {
            String expression = IOExpression.InputExpression(scanner);
            IOExpression.OutputExpression(expression);
        }
    }
}
