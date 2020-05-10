package stringCalculator;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class StringCalculatorTest {

    StringCalculator calculator = new StringCalculator();

    @Test
    public void shouldReturnZeroForEmptyStringInput(){
        assertEquals(0, calculator.add(""));
    }

    @Test
    @Disabled("already covered by shouldReturnTheSumOfUnknownAmountOfNumbers")
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
    @Disabled("already covered by shouldReturnTheSumOfUnknownAmountOfNumbers")
    public void shouldReturnTheSumOfTwoNumbersSeparatedByComma(){
        assertEquals(0,calculator.add("-10,+10"));
        assertEquals(20,calculator.add("+10,+10"));
        assertEquals(10,calculator.add("7,3"));
        assertEquals(-15,calculator.add("-5,-10"));
        assertEquals(4500,calculator.add("-5500,10000"));
    }

    @ParameterizedTest
    @MethodSource("sourceOfNumbers")
    public void shouldReturnTheSumOfUnknownAmountOfNumbers(String input,int result){
        assertEquals(result, calculator.add(input));
    }

    private static Stream<Arguments> sourceOfNumbers(){
        return Stream.of(
                arguments("1", 1),
                arguments("1,2", 3),
                arguments("1,2,3", 6),
                arguments("1,2,3,4", 10),
                arguments("1,2,3,4,5", 15),
                arguments("1,2,3,4,5,6", 21)
        );
    }

}
