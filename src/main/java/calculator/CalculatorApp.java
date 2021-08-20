package calculator;

public class CalculatorApp {
    public static void main(String[] args) {
        String str = IOExpression.InputExpression();
        IOExpression.OutputExpression(Calculator.calculate(str));
    }
}
