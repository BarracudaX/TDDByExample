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

    BerlinClock berlinClock;

    @BeforeEach
    public void setUp() {
        berlinClock = new BerlinClock(19, 55, 1);
    }

    @ParameterizedTest
    @MethodSource("secondsArguments")
    public void shouldReturnYellowLightIfTheSecondsAreEvenAndNoneIfOdd(int seconds, BarColor expected) {
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
    public void shouldReturnColorsArrayThatIndicatesHowManyLampsInFirstRowAreOn(int hours, BarColor[] expectedResult) {
        berlinClock.setHours(hours);
        BarColor[] firstRow = berlinClock.getFirstRow();

        assertRowHas(expectedResult, firstRow);
    }

    @ParameterizedTest
    @MethodSource("firstRowArguments")
    public void shouldReturnConsistentResultForFirstRow(int hours, BarColor[] expected) {
        berlinClock.setHours(hours);

        assertRowHas(expected, berlinClock.getFirstRow());
        assertRowHas(expected, berlinClock.getFirstRow());
    }

    /**
     * Each lamp that is on in the seconds row indicates +1hour.
     */
    @ParameterizedTest
    @MethodSource("secondRowArguments")
    public void shouldReturnColorsArrayThatIndicatesHowManyLampsInTheSecondsRowAreOn(int hours, BarColor[] expected) {
        berlinClock.setHours(hours);
        assertRowHas(expected, berlinClock.getSecondRow());
    }

    @ParameterizedTest
    @MethodSource("secondRowArguments")
    public void shouldReturnConsistentResultForSecondRow(int hours, BarColor[] expected) {
        berlinClock.setHours(hours);
        assertRowHas(expected, berlinClock.getSecondRow());
        assertRowHas(expected, berlinClock.getSecondRow());
    }

    /**
     * Every lamp that is on in the third row is equal to 5 minutes.Every third lamp denotes 15,30,45min.
     */
    @ParameterizedTest
    @MethodSource("thirdRowArguments")
    public void shouldReturnArrayOfColorsThatIndicateHowManyLampsInTheThirdRowAreOn(int minutes,BarColor[] expected) {
        berlinClock.setMinutes(minutes);
        assertRowHas(expected, berlinClock.getThirdRow());
    }

    private static Stream<Arguments> firstRowArguments() {
        return Stream.of(
                arguments(0, expectedRow(0,4)),
                arguments(4, expectedRow(0,4)),
                arguments(5, expectedRow(1,3)),
                arguments(9, expectedRow(1,3)),
                arguments(10, expectedRow(2,2)),
                arguments(14, expectedRow(2,2)),
                arguments(15, expectedRow(3,1)),
                arguments(19, expectedRow(3,1)),
                arguments(20, expectedRow(4,0)),
                arguments(23, expectedRow(4,0))
        );
    }

    private static Stream<Arguments> secondRowArguments() {
        return Stream.of(
                arguments(5,expectedRow(0,4)),
                arguments(10, expectedRow(0,4)),
                arguments(15, expectedRow(0,4)),
                arguments(20, expectedRow(0,4)),
                arguments(6, expectedRow(1,3)),
                arguments(11, expectedRow(1,3)),
                arguments(16, expectedRow(1,3)),
                arguments(21, expectedRow(1,3)),
                arguments(2, expectedRow(2,2)),
                arguments(7, expectedRow(2,2)),
                arguments(12, expectedRow(2,2)),
                arguments(17, expectedRow(2,2)),
                arguments(22, expectedRow(2,2)),
                arguments(3, expectedRow(3,1)),
                arguments(8, expectedRow(3,1)),
                arguments(13, expectedRow(3,1)),
                arguments(23, expectedRow(3,1)),
                arguments(4, expectedRow(4,0)),
                arguments(9, expectedRow(4,0)),
                arguments(14, expectedRow(4,0)),
                arguments(19, expectedRow(4,0)),
                arguments(24, expectedRow(4,0))
        );
    }

    private static Stream<Arguments> thirdRowArguments() {
        return Stream.of(
                arguments(
                        35,
                        new BarColor[]{
                        BarColor.YELLOW,BarColor.YELLOW,BarColor.RED,BarColor.YELLOW,BarColor.YELLOW,
                        BarColor.RED,BarColor.YELLOW,BarColor.NONE,BarColor.NONE,BarColor.NONE,BarColor.NONE}
                        ),
                arguments(
                        53,
                        new BarColor[]{
                        BarColor.YELLOW,BarColor.YELLOW,BarColor.RED,BarColor.YELLOW,BarColor.YELLOW,
                        BarColor.RED,BarColor.YELLOW,BarColor.YELLOW,BarColor.RED,BarColor.YELLOW,BarColor.NONE}
                        ),
                arguments(
                        22,
                        new BarColor[]{
                        BarColor.YELLOW,BarColor.YELLOW,BarColor.RED,BarColor.YELLOW,BarColor.NONE,
                        BarColor.NONE,BarColor.NONE,BarColor.NONE,BarColor.NONE,BarColor.NONE,BarColor.NONE}
                        ),
                arguments(
                        14,
                        new BarColor[]{
                        BarColor.YELLOW,BarColor.YELLOW,BarColor.NONE,BarColor.NONE,BarColor.NONE,
                        BarColor.NONE,BarColor.NONE,BarColor.NONE,BarColor.NONE,BarColor.NONE,BarColor.NONE}
                        )
        );
    }

    private static BarColor[] expectedRow(int red, int none) {
        BarColor[] colors = new BarColor[red + none];

        for (int i = 0; i < red; i++) {
            colors[i] = BarColor.RED;
        }

        for (int j = red; j < red + none; j++) {
            colors[j] = BarColor.NONE;
        }

        return colors;
    }

    private static Stream<Arguments> secondsArguments() {
        Collection<Arguments> arguments = new ArrayList<>();

        for (int i = 0; i < 59; i++) {
            arguments.add(arguments(i, i % 2 == 0 ? BarColor.YELLOW : BarColor.NONE));
        }

        return arguments.stream();
    }

    private void assertRowHas(BarColor[] expected, BarColor[] actual) {
        assertEquals(expected.length,actual.length);

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i],actual[i],
                    "Expected : "+expected[i]+",but actual value was : "+actual[i]+".Index = "+i);
        }
    }

}
