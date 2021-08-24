package calculator;

import java.util.Scanner;

public class IOExpression {
    public static String InputExpression() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Put expression");
        var d = scanner.nextLine().replaceAll(" ", "");
        if (d.equalsIgnoreCase("quite") || d.equalsIgnoreCase("exit")) {
            System.exit(1);
        }
        return d;
    }
    
    public static void OutputExpression(String result) {
        System.out.println(result);
    }
}
