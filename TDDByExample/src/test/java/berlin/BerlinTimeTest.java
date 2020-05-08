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

//14
public class BerlinTimeTest {

    BerlinClock berlinClock;

    @BeforeEach
    public void setUp() {
        //IMPORTANT:DON'T CHANGE THE MINUTES AND SECONDS FROM 0.
        berlinClock = new BerlinClock(15, 0, 0);
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

    @Test
    public void shouldNotChangeTheMinutesOfTheClockWhenAskedForFirstRow() {
        assertStateNotChangedAfterAction(() -> berlinClock.getFirstRow());
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

    @Test
    public void shouldNotChangeTheMinutesOfTheClockWhenAskedForSecondRow() {
        assertStateNotChangedAfterAction(() -> berlinClock.getSecondRow());
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

    @Test
    public void shouldNotChangeTheMinutesOfTheClockWhenAskedForThirdRow(){
        assertStateNotChangedAfterAction(() -> berlinClock.getThirdRow());
    }

    /**
     * Each lamp that is on in the fourth row indicates +1 minute(max,4min)
     */
    @ParameterizedTest
    @MethodSource("berlin.TestValues#fourthRowArguments")
    public void shouldReturnArrayOfColorsThatIndicateWhichLampsInTheFourthRowAreOn(int minutes,BarColor[] expected){
        berlinClock.setMinutes(minutes);
        BarColor[] colors = berlinClock.getFourthRow();
        assertRowHas(expected, colors);
    }

    @Test
    public void shouldNotChangeTheMinutesOfTheClockWhenAskedForFourthRow(){
        assertStateNotChangedAfterAction(() -> berlinClock.getFourthRow());
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

    @ParameterizedTest
    @MethodSource("berlin.TestValues#sourceOfInvalidSeconds")
    public void setterAndConstructorShouldThrowIAEWhenSecondsAreInvalid(int invalidSeconds) {
        assertThrows(IllegalArgumentException.class,
                () -> berlinClock = new BerlinClock(20, 20, invalidSeconds));
        assertThrows(IllegalArgumentException.class, () -> berlinClock.setSeconds(invalidSeconds));
    }

    @Test
    public void shouldThrowIAEWhenHoursAre24AndMinutesAreGreaterThanZero(){
        assertThrows(IllegalArgumentException.class,
                () -> berlinClock = new BerlinClock(24, 1, 0));

        berlinClock = new BerlinClock(24, 0, 0);
        assertThrows(IllegalArgumentException.class,
                () -> berlinClock.setMinutes(1));

        berlinClock.setHours(23);
        berlinClock.setMinutes(50);
        assertThrows(IllegalArgumentException.class, () -> berlinClock.setHours(24));
    }

    @Test
    public void shouldThrowIAEWhenHoursAre24AndSecondsAreGreaterThanZero(){
        assertThrows(IllegalArgumentException.class,
                () -> berlinClock = new BerlinClock(24, 0, 1));

        berlinClock = new BerlinClock(24, 0, 0);
        assertThrows(IllegalArgumentException.class,
                () -> berlinClock.setSeconds(1));

        berlinClock.setHours(23);
        berlinClock.setSeconds(50);
        assertThrows(IllegalArgumentException.class, () -> berlinClock.setHours(24));
    }

    private void assertRowHas(BarColor[] expected, BarColor[] actual) {
        assertEquals(expected.length,actual.length);

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i],actual[i],
                    "Expected : "+expected[i]+",but actual value was : "+actual[i]+".Index = "+i);
        }
    }

    private void assertStateNotChangedAfterAction(Action action) {
        int hoursBefore = berlinClock.getHours();
        int minutesBefore = berlinClock.getMinutes();
        int secondsBefore = berlinClock.getSeconds();

        action.perform();

        int hoursAfter = berlinClock.getHours();
        int minutesAfter = berlinClock.getMinutes();
        int secondsAfter = berlinClock.getSeconds();

        assertEquals(hoursBefore,hoursAfter);
        assertEquals(minutesBefore,minutesAfter);
        assertEquals(secondsBefore,secondsAfter);
    }

    private interface Action{
        void perform();
    }
}
