package berlin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class BerlinTimeTest {

    BerlinClock berlinClock;

    @BeforeEach
    public void setUp(){
        berlinClock = new BerlinClock(19, 55, 1);
    }

    @ParameterizedTest
    @MethodSource("secondsArguments")
    public void shouldReturnTrueIfSecondsAreEvenAndFalseOtherwise(int seconds,boolean expected) {
        berlinClock.setSeconds(seconds);
        assertEquals(expected, berlinClock.isLightOn());
    }

    /**
     * Each lamp that is on in first row of Berlin Clock indicates a +5 hours in the time.
     * 0 lamps on : hours < 5,
     * first lamp on: hours < 10,
     * first and second lamps on : hours < 15,
     * and so on...
     */
    @ParameterizedTest
    @MethodSource("firstRowArguments")
    public void shouldReturnBooleanArrayThatIndicatesHowManyLampsInFirstRowAreOn(int hours, boolean[] expectedResult){
        berlinClock.setHours(hours);
        boolean[] firstRow = berlinClock.getFirstRow();

        assertRowHas(expectedResult, firstRow);
    }

    @ParameterizedTest
    @MethodSource("firstRowArguments")
    public void shouldReturnConsistentResultForFirstRow(int hours,boolean[] expected){
        berlinClock.setHours(hours);

        assertRowHas(expected,berlinClock.getFirstRow());
        assertRowHas(expected,berlinClock.getFirstRow());
    }

    /**
     * Each lamp that is on in the seconds row indicates +1hour.
     */
    @ParameterizedTest
    @MethodSource("secondRowArguments")
    public void shouldReturnABooleanArrayThatIndicatesHowManyLampsInTheSecondsRowAreOn(int hours,boolean[] expected){
        berlinClock.setHours(hours);
        assertRowHas(expected, berlinClock.getSecondRow());
    }

    @ParameterizedTest
    @MethodSource("secondRowArguments")
    public void shouldReturnConsistentResultForSecondRow(int hours,boolean[] expected) {
        berlinClock.setHours(hours);
        assertRowHas(expected,berlinClock.getSecondRow());
        assertRowHas(expected,berlinClock.getSecondRow());
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

    private static Stream<Arguments> secondRowArguments(){
        return Stream.of(
                arguments(5,new boolean[]{false,false,false,false}),
                arguments(10,new boolean[]{false,false,false,false}),
                arguments(15,new boolean[]{false,false,false,false}),
                arguments(20,new boolean[]{false,false,false,false}),
                arguments(6,new boolean[]{true,false,false,false}),
                arguments(11,new boolean[]{true,false,false,false}),
                arguments(16,new boolean[]{true,false,false,false}),
                arguments(21,new boolean[]{true,false,false,false}),
                arguments(2,new boolean[]{true,true,false,false}),
                arguments(7,new boolean[]{true,true,false,false}),
                arguments(12,new boolean[]{true,true,false,false}),
                arguments(17,new boolean[]{true,true,false,false}),
                arguments(22,new boolean[]{true,true,false,false}),
                arguments(3,new boolean[]{true,true,true,false}),
                arguments(8,new boolean[]{true,true,true,false}),
                arguments(13,new boolean[]{true,true,true,false}),
                arguments(23,new boolean[]{true,true,true,false}),
                arguments(4,new boolean[]{true,true,true,true}),
                arguments(9,new boolean[]{true,true,true,true}),
                arguments(14,new boolean[]{true,true,true,true}),
                arguments(19,new boolean[]{true,true,true,true}),
                arguments(24,new boolean[]{true,true,true,true})
        );
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
