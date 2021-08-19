package calculator;
import java.util.ArrayList;
import java.util.List;

public class Calculator {

    public static Integer calculate(String expression){
        int result = 0;
        ArrayList<String> opers = StringParser.getOpersList(expression);
        ArrayList<Integer> values = StringParser.getValuesList(expression);

         if(opers.size()!=values.size()-1){
              System.out.println("The input string has extra operators");
              System.exit(1);
         }
        while(opers.indexOf("*") != -1|| opers.indexOf("/") != -1){
             int mulIndex = opers.indexOf("*");
             int mulNextIndex = mulIndex + 1;
            if(mulIndex != -1){
              result = values.get(mulIndex) * values.get(mulNextIndex);
              beforeNextStep(opers,values,mulIndex,mulNextIndex,result);
            }
            int devideIndex = opers.indexOf("/");
            int devideNextIndex = devideIndex +  1;
            if(devideIndex != -1){
                try{
                    result = values.get(devideIndex) / values.get(devideNextIndex);
                }catch (ArithmeticException e){
                    System.out.println("In this statement we devide on zero");
                    System.exit(1);
                }
                beforeNextStep(opers,values,devideIndex,devideNextIndex,result);
            }
        }
        while(opers.indexOf("+") != -1|| opers.indexOf("-") != -1){
            int plusIndex = opers.indexOf("+");
            int plusNextIndex = plusIndex + 1;
            if(plusIndex != -1){
                result = values.get(plusIndex) + values.get(plusNextIndex);
                beforeNextStep(opers,values,plusIndex,plusNextIndex,result);

            }
            int minusIndex = opers.indexOf("-");
            int minusNextIndex = minusIndex + 1;
            if(minusIndex != -1){
                result = values.get(minusIndex) - values.get(minusNextIndex);
                beforeNextStep(opers,values,minusIndex,minusNextIndex,result);
            }
        }
        result = values.get(0);
        return result;
    }

    private static void beforeNextStep(List<String> opers, List<Integer> values, int currentIndex, int nextIndex, int tempRes){
        values.remove(currentIndex);
        values.add(currentIndex, tempRes);
        values.remove(nextIndex) ;
        opers.remove(currentIndex);
    }
}
