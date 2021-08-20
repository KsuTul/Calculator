package calculatorTest;

import calculator.Calculator;
import calculator.StringParser;
import calculator.Validator;
import extensions.InvalidInputString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CalculatorTest {
	@Test
	public void validateReturnMessageIfEmptyString() {
		String expression = "";
		String expectedErrorMessage = "Input string is empty";
		
		Throwable thrown = Assertions.assertThrows(InvalidInputString.class, () -> {
			Validator.validate(expression);
		});
		
		Assertions.assertEquals(expectedErrorMessage, thrown.toString());
	}
	
	@Test
	public void validateReturnMessageIfStringHasInvalidSymbols() {
		String expression = "1sg+(3,4+4)";
		String expectedErrorMessage = "The input string is incorrect. Make sure you enter only nums and operators.";
		
		Throwable thrown = Assertions.assertThrows(InvalidInputString.class, () -> {
			Validator.validate(expression);
		});
		
		Assertions.assertEquals(expectedErrorMessage, thrown.toString());
	}
	
	@Test
	public void getOpersListReturnOpers() {
		String expression = "1+6*30-25/5+90";
		ArrayList<String> expectedList = new ArrayList<>() {
			{
				add("+");
				add("*");
				add("-");
				add("/");
				add("+");
			}
		};
		
		ArrayList<String> actualList = StringParser.getOpersList(expression);
		
		Assertions.assertEquals(expectedList.size(), actualList.size());
		Assertions.assertEquals(expectedList.get(0), actualList.get(0));
		Assertions.assertEquals(expectedList.get(expectedList.size() - 1), actualList.get(actualList.size() - 1));
	}
	
	@Test
	public void getValuesReturnValues() {
		String expression = "5+28-44.5*20";
		ArrayList<BigDecimal> expectedList = new ArrayList<>() {
			{
				add(new BigDecimal("5.0"));
				add(new BigDecimal("28.0"));
				add(new BigDecimal("44.5"));
				add(new BigDecimal("20.0"));
			}
		};
		
		ArrayList<BigDecimal> actualList = StringParser.getValuesList(expression);
		
		Assertions.assertEquals(expectedList.size(), actualList.size());
		Assertions.assertEquals(expectedList.get(0), actualList.get(0));
		Assertions.assertEquals(expectedList.get(expectedList.size() - 1), actualList.get(actualList.size() - 1));
	}
	
	@Test
	public void calculateReturnExceptionIfHasExtraOpers() {
		String expression = "-1++8";
		String expectedErrorMessage = "The input string has extra operators";
		
		Throwable thrown = Assertions.assertThrows(InvalidInputString.class, () -> {
			Calculator.calculate(expression);
		});
		
		Assertions.assertEquals(expectedErrorMessage, thrown.toString());
	}
	
	@Test
	public void calculateReturnCorrectResult() {
		String expression = "4.2+2*3/3-6.1";
		BigDecimal expected = new BigDecimal("0.1");
		
		BigDecimal actual = Calculator.calculate(expression);
		
		Assertions.assertEquals(expected, actual);
	}
}
