package berlin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class BerlinTimeTest {

    BerlinTime berlinTime;

    @BeforeEach
    public void setUp(){
        berlinTime = new BerlinTime(19, 55, 1);
    }

    @ParameterizedTest
    @MethodSource("secondsArguments")
    public void shouldReturnTrueIfSecondsAreEvenAndFalseOtherwise(int seconds,boolean expected) {
        berlinTime.setSeconds(seconds);
        assertEquals(expected, berlinTime.isLightOn());
    }

    @ParameterizedTest
    @MethodSource("firstRowArguments")
    public void shouldReturnABooleanArrayThatIndicatesHowManyLampsInTheFirstRowAreOnDependingOnTheHours(int hours,boolean[] expectedResult){
        berlinTime.setHours(hours);
        boolean[] firstRow = berlinTime.getFirstRow();

        assertRowHas(expectedResult, firstRow);
    }

    @ParameterizedTest
    @MethodSource("firstRowArguments")
    public void shouldReturnConsistentResultForFirstRow(int hours,boolean[] expected){
        berlinTime.setHours(hours);
        boolean[] firstCall = berlinTime.getFirstRow();
        boolean[] secondCall = berlinTime.getFirstRow();

        assertRowHas(expected,firstCall);
        assertRowHas(expected,secondCall);
    }

    private static Stream<Arguments> firstRowArguments(){
        return Stream.of(
                arguments(0,new boolean[]{false,false,false,false}),
                arguments(4,new boolean[]{false,false,false,false}),
                arguments(5,new boolean[]{true,false,false,false}),
                arguments(9,new boolean[]{true,false,false,false}),
                arguments(10,new boolean[]{true,true,false,false}),
                arguments(14,new boolean[]{true,true,false,false}),
                arguments(15,new boolean[]{true,true,true,false}),
                arguments(19,new boolean[]{true,true,true,false}),
                arguments(20,new boolean[]{true,true,true,true}),
                arguments(23,new boolean[]{true,true,true,true})
        );
    }

    private static Stream<Arguments> secondsArguments(){
        Collection<Arguments> arguments = new ArrayList<>();

        for (int i = 0; i < 59; i++) {
            arguments.add(arguments(i, i % 2 == 0));
        }

        return arguments.stream();
    }

    private void assertRowHas(boolean[] expected, boolean[] actual) {
        assertEquals(4,expected.length);
        assertEquals(4, actual.length);

        assertEquals(expected[0],actual[0]);
        assertEquals(expected[1],actual[1]);
        assertEquals(expected[2],actual[2]);
        assertEquals(expected[3],actual[3]);
    }

}
