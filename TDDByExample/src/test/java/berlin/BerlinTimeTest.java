package berlin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class BerlinTimeTest {

    @Test
    public void shouldReturnTheTopLightIsOffWhenSecondsAreOdd(){
        BerlinConverter converter = new BerlinConverter(19, 55, 1);
        assertFalse(converter.isLightOn());
    }

}
