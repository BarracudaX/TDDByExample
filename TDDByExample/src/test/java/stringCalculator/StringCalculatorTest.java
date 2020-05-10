package stringCalculator;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
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

    @ParameterizedTest
    @MethodSource("sourceOfNumbersSeparatedByNewLine")
    public void shouldAddNumbersSeparatedByNewLine(){
        assertEquals(6, calculator.add("1\n2\n3"));
    }

    @Test
    public void shouldThrowIAEWhenBothNewLineAndCommaAreUsed(){
        assertThrows(IllegalArgumentException.class, () -> calculator.add("1\n2,3"));
        assertThrows(IllegalArgumentException.class, () -> calculator.add("1,2\n3"));
        assertThrows(IllegalArgumentException.class, () -> calculator.add("1,\n3"));
        assertThrows(IllegalArgumentException.class, () -> calculator.add("1\n,3"));
        assertThrows(IllegalArgumentException.class, () -> calculator.add("1\n,"));
        assertThrows(IllegalArgumentException.class, () -> calculator.add("1,\n"));
    }

    @Test
    public void shouldAllowSpecifyingDelimiterAtFirstLineOfString(){
        assertEquals(3, calculator.add("//[;]\n1;2"));
        assertEquals(6, calculator.add("//[?]\n1?2?3"));
    }

    @Test
    @Disabled("covered by shouldThrowIAEWhenAddingNegativeNumbersWithMessageThatContainsAllOfThem")
    public void shouldThrowIAEWhenAddingNegativeNumberWithMessage(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> calculator.add("-1,2"));
        assertTrue(ex.getMessage().contains("-1"));

        ex = assertThrows(IllegalArgumentException.class, () -> calculator.add("1\n-22"));
        assertTrue(ex.getMessage().contains("-22"));

        ex = assertThrows(IllegalArgumentException.class, () -> calculator.add("//[;]\n-4\n2"));
        assertTrue(ex.getMessage().contains("-4"));
    }

    @Test
    public void shouldThrowIAEWhenAddingNegativeNumbersWithMessageThatContainsAllOfThem(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> calculator.add("2,-1,-2,5"));
        assertTrue(ex.getMessage().contains("-1"));
        assertTrue(ex.getMessage().contains("-2"));

        ex = assertThrows(IllegalArgumentException.class, () -> calculator.add("2,-1,5"));
        assertTrue(ex.getMessage().contains("-1"));

        ex = assertThrows(IllegalArgumentException.class, () -> calculator.add("2\n-13\n-55"));
        assertTrue(ex.getMessage().contains("-13"));
        assertTrue(ex.getMessage().contains("-55"));

        ex = assertThrows(IllegalArgumentException.class, () -> calculator.add("//[;]\n-2;11;-8"));
        assertTrue(ex.getMessage().contains("-2"));
        assertTrue(ex.getMessage().contains("-8"));
    }

    @Test
    public void shouldIgnoreNumbersBiggerThanOneThousand(){
        assertEquals(1002, calculator.add("1000,1,1"));
        assertEquals(2, calculator.add("1001,1,1"));
        assertEquals(4, calculator.add("5000\n2\n2"));
        assertEquals(5, calculator.add("//[;]\n40232;3;2"));
    }

    @Test
    public void shouldAllowSpecifyingDelimiterOfAnyLength(){
        assertEquals(4,calculator.add("//[****]\n2****2"));
        assertEquals(5,calculator.add("//[**]\n3**2"));
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

    private static Stream<Arguments> sourceOfNumbersSeparatedByNewLine(){
        return Stream.of(
                arguments("1", 1),
                arguments("1\n2", 3),
                arguments("1\n2\n3", 6),
                arguments("1\n2\n3\n4", 10),
                arguments("1\n2\n3\n4\n5", 15),
                arguments("1\n2\n3\n4\n5\n6", 21)
        );
    }

}
