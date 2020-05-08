package berlin;

import java.util.function.Predicate;

public class BerlinClock {

    private int hours;

    private int minutes;

    private int seconds;

    public BerlinClock(int hours, int minutes, int seconds) {
        setHours(hours);
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public BarColor isLightOn() {
        return seconds % 2 == 0 ? BarColor.YELLOW : BarColor.NONE;
    }

    public BarColor[] getFirstRow() {
        BarColor[] result = new BarColor[4];

        int hours = this.hours;

        fillRow(result, hours, value -> value >= 5, value -> value - 5);

        return result;
    }

    public BarColor[] getSecondRow() {
        BarColor[] result = new BarColor[4];

        int hours = this.hours;

        hours %= 5;

        fillRow(result, hours, value -> value > 0, (value) -> value - 1);

        return result;
    }

    private <T> void fillRow(BarColor[] colors, T initValue, Predicate<T> tester, Transformer<T> transformer) {
        for (int i = 0; i < colors.length; i++) {

            if (tester.test(initValue)) {
                colors[i] = BarColor.RED;
            } else {
                colors[i] = BarColor.NONE;
            }
            initValue = transformer.transform(initValue);
        }
    }

    public BarColor[] getThirdRow() {
        BarColor[] colors = new BarColor[11];

        int counter = 0;

        int minutes = this.minutes;

        for (int i = 0; i < colors.length; i++) {
            counter++;
            if (minutes < 5) {
                colors[i] = BarColor.NONE;
            } else if (counter == 3) {
                counter = 0;
                colors[i] = BarColor.RED;
            } else {
                colors[i] = BarColor.YELLOW;
            }
            minutes -= 5;
        }

        return colors;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setHours(int hours) {
        if (hours < 1 || hours > 24) {
            throw new IllegalArgumentException("Hours are invalid.Correct values [1,24].");
        }
        this.hours = hours;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    private interface Transformer<T> {

        T transform(T t);
    }
}
