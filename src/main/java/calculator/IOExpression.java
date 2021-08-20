package calculator;

import java.math.BigDecimal;
import java.util.Scanner;

public class IOExpression {
    public static String InputExpression() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Put expression");
        return scanner.nextLine().replaceAll(" ", "");
    }
    
    public static void OutputExpression(BigDecimal result) {
        System.out.println(result);
    }
}
