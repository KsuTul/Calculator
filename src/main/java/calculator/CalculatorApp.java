package calculator;

public class CalculatorApp {
    public static void main(String[] args) {
        while (true) {
            String str = IOExpression.InputExpression();
            IOExpression.OutputExpression(Calculator.calculate(str));
        }
    }
}
