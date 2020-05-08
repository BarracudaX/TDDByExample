package berlin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

//11
public class BerlinTimeTest {

    BerlinClock berlinClock;

    @BeforeEach
    public void setUp() {
        berlinClock = new BerlinClock(19, 55, 1);
    }

    @ParameterizedTest
    @MethodSource("berlin.TestValues#secondsArguments")
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
    @MethodSource("berlin.TestValues#firstRowArguments")
    public void shouldReturnColorsArrayThatIndicatesHowManyLampsInFirstRowAreOn(int hours, BarColor[] expectedResult) {
        berlinClock.setHours(hours);
        BarColor[] firstRow = berlinClock.getFirstRow();
        assertRowHas(expectedResult, firstRow);
    }

    @ParameterizedTest
    @MethodSource("berlin.TestValues#firstRowArguments")
    public void shouldReturnConsistentResultForFirstRow(int hours, BarColor[] expected) {
        berlinClock.setHours(hours);

        assertRowHas(expected, berlinClock.getFirstRow());
        assertRowHas(expected, berlinClock.getFirstRow());
    }

    /**
     * Each lamp that is on in the seconds row indicates +1hour.
     */
    @ParameterizedTest
    @MethodSource("berlin.TestValues#secondRowArguments")
    public void shouldReturnColorsArrayThatIndicatesHowManyLampsInTheSecondsRowAreOn(int hours, BarColor[] expected) {
        berlinClock.setHours(hours);
        assertRowHas(expected, berlinClock.getSecondRow());
    }

    @ParameterizedTest
    @MethodSource("berlin.TestValues#secondRowArguments")
    public void shouldReturnConsistentResultForSecondRow(int hours, BarColor[] expected) {
        berlinClock.setHours(hours);
        assertRowHas(expected, berlinClock.getSecondRow());
        assertRowHas(expected, berlinClock.getSecondRow());
    }

    /**
     * Every lamp that is on in the third row is equal to 5 minutes.Every third lamp denotes 15,30,45min.
     */
    @ParameterizedTest
    @MethodSource("berlin.TestValues#thirdRowArguments")
    public void shouldReturnArrayOfColorsThatIndicateHowManyLampsInTheThirdRowAreOn(int minutes,BarColor[] expected) {
        berlinClock.setMinutes(minutes);
        assertRowHas(expected, berlinClock.getThirdRow());
    }

    @ParameterizedTest
    @MethodSource("berlin.TestValues#thirdRowArguments")
    public void shouldReturnConsistentColorsForTheThirdRow(int minutes,BarColor[] expected){
        berlinClock.setMinutes(minutes);
        assertRowHas(expected,berlinClock.getThirdRow());
        assertRowHas(expected,berlinClock.getThirdRow());
    }

    /**
     * Each lamp that is on in the fourth row indicates +1 minute(max,4min)
     */
    @ParameterizedTest
    @MethodSource("berlin.TestValues#fourthRowArguments")
    public void shouldReturnArrayOfColorsThatIndicateWhichLampsInTheFourthRowAreOn(){
        berlinClock.setMinutes(30);
        BarColor[] colors = berlinClock.getFourthRow();
        assertRowHas(new BarColor[]{BarColor.NONE, BarColor.NONE, BarColor.NONE, BarColor.NONE}, colors);

        berlinClock.setMinutes(31);
        colors = berlinClock.getFourthRow();
        assertRowHas(new BarColor[]{BarColor.YELLOW, BarColor.NONE, BarColor.NONE, BarColor.NONE}, colors);

    }

    @ParameterizedTest
    @MethodSource("berlin.TestValues#sourceOfInvalidHours")
    public void setterAndConstructorShouldThrowIAEWhenHoursAreInvalid(int invalidHours){
        assertThrows(IllegalArgumentException.class,
                () -> berlinClock = new BerlinClock(invalidHours, 30, 20));
        assertThrows(IllegalArgumentException.class, () -> berlinClock.setHours(invalidHours));
    }

    @ParameterizedTest
    @MethodSource("berlin.TestValues#sourceOfInvalidMinutes")
    public void setterAndConstructorShouldThrowIAEWhenMinutesAreInvalid(int invalidMinutes){
        assertThrows(IllegalArgumentException.class,
                () -> berlinClock = new BerlinClock(20, invalidMinutes, 20));
        assertThrows(IllegalArgumentException.class, () -> berlinClock.setMinutes(invalidMinutes));
    }
    private void assertRowHas(BarColor[] expected, BarColor[] actual) {
        assertEquals(expected.length,actual.length);

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i],actual[i],
                    "Expected : "+expected[i]+",but actual value was : "+actual[i]+".Index = "+i);
        }
    }

}
