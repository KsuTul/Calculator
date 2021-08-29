package calculatorTest;

import calculator.Calculator;
import calculator.StringParser;
import calculator.Validator;
import exceptions.InvalidInputString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
		String expectedErrorMessage = "The input string is incorrect. Make sure you enter only nums and operators or your first symbol not num/minus/brackets";

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

		List<String> actualList = StringParser.getOpersList(expression);

		Assertions.assertEquals(expectedList.size(), actualList.size());
		Assertions.assertEquals(expectedList.get(0), actualList.get(0));
		Assertions.assertEquals(expectedList.get(expectedList.size() - 1), actualList.get(actualList.size() - 1));
	}

	@Test
	public void getValuesReturnValues() {
		String expression = "5+28-44.5*20";
		List<BigDecimal> expectedList = new ArrayList<>() {
			{
				add(new BigDecimal("5"));
				add(new BigDecimal("28"));
				add(new BigDecimal("44.5"));
				add(new BigDecimal("20"));
			}
		};

		List<BigDecimal> actualList = StringParser.getValuesList(expression);

		Assertions.assertEquals(expectedList.size(), actualList.size());
		Assertions.assertEquals(expectedList.get(0), actualList.get(0));
		Assertions.assertEquals(expectedList.get(expectedList.size() - 1), actualList.get(actualList.size() - 1));
	}

	@Test
	public void calculateReturnCorrectResult() throws InvalidInputString {
		String expression = "4.2+2*3/3-6.1";
		String expected = "0.1";

		String actual = Calculator.calculate(expression);

		Assertions.assertEquals(expected, actual);
	}

	@Test
	public void calculateReturnNumIfStringConsistOnlyNums() throws InvalidInputString {
		String expression = "22222";
		String expected = "22222";

		String actual = Calculator.calculate(expression);

		Assertions.assertEquals(expected, actual);
	}

	@Test
	public void validateReturnErrorMessageIfEmptyString() {
		String expression = "";
		String expectedErrorMessage = "Input string is empty";

		Throwable thrown = Assertions.assertThrows(InvalidInputString.class, () -> {
			Validator.validate(expression);
		});

		Assertions.assertEquals(expectedErrorMessage, thrown.toString());
	}

	@Test
	public void validateReturnErrorMessageIfExtraOpers() {
		String expression = "1++2";
		String expectedErrorMessage = "The input string has extra opers";

		Throwable thrown = Assertions.assertThrows(InvalidInputString.class, () -> {
			Validator.validate(expression);
		});

		Assertions.assertEquals(expectedErrorMessage, thrown.toString());
	}

	@Test
	public void calculateWithMinus() throws InvalidInputString {
		String expression = "(-2)-((-4)*3.5)";
		String expected = "12.0";

		String actual = Calculator.calculate(expression);
		Assertions.assertEquals(expected, actual);
	}
}
