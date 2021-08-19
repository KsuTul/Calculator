package calculator;

public class CalculatorTest {
    public static void main(String[] args) {
        String str = IOExpression.InputExpression();
        IOExpression.OutputExpression(Calculator.calculate(str));
    }
}
