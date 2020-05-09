package stringCalculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTest {

    StringCalculator calculator = new StringCalculator();

    @Test
    public void shouldReturnZeroForEmptyStringInput(){
        assertEquals(0, calculator.add(""));
    }

    @Test
    public void shouldReturnTheSumOfOneNumber(){
        assertEquals(1, calculator.add("1"));
        assertEquals(0, calculator.add("0"));
        assertEquals(-10, calculator.add("-10"));
        assertEquals(10, calculator.add("+10"));
    }

    @Test
    public void shouldThrowIAEWhenFirstArgumentOfStringIsNotANumber(){
        assertThrows(IllegalArgumentException.class, () -> calculator.add("x"));
        assertThrows(IllegalArgumentException.class, () -> calculator.add("209x12"));
        assertThrows(IllegalArgumentException.class, () -> calculator.add("00x"));
        assertThrows(IllegalArgumentException.class, () -> calculator.add("y0209"));
    }

    @Test
    public void shouldReturnTheSumOfTwoNumbersSeparatedByComma(){
        assertEquals(0,calculator.add("-10,+10"));
        assertEquals(20,calculator.add("+10,+10"));
        assertEquals(10,calculator.add("7,3"));
        assertEquals(-15,calculator.add("-5,-10"));
        assertEquals(4500,calculator.add("-5500,10000"));
    }


}
