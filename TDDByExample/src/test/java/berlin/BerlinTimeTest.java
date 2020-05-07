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

}
