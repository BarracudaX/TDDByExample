package berlin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BerlinTimeTest {

    BerlinConverter converter;

    @BeforeEach
    public void setUp(){
        converter = new BerlinConverter(19, 55, 1);
    }

    @Test
    public void shouldReturnTheTopLightIsOffWhenSecondsAreOdd() {
        converter.setSeconds(1);
        assertFalse(converter.isLightOn());
    }

    @Test
    public void shouldReturnTheTopLightIsOnWhenSecondsAreEven() {
        converter.setSeconds(2);
        assertTrue(converter.isLightOn());
    }

    @Test
    public void for_10_hours_should_return_a_boolean_array_that_indicates_that_first_two_lamps_are_on(){
        converter.setHours(10);
        boolean[] firstRow = converter.getFirstRow();

        assertEquals(4,firstRow.length);
        assertTrue(firstRow[0]);
        assertTrue(firstRow[1]);
        assertFalse(firstRow[2]);
        assertFalse(firstRow[3]);
    }
}
