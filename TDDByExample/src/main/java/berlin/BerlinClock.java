package berlin;

import java.util.function.Predicate;

public class BerlinClock {

    private int hours;

    private int minutes;

    private int seconds;

    public BerlinClock(int hours, int minutes, int seconds) {
        setHours(hours);
        setMinutes(minutes);
        setSeconds(seconds);
    }

    public BarColor isLightOn() {
        return seconds % 2 == 0 ? BarColor.YELLOW : BarColor.NONE;
    }

    public BarColor[] getFirstRow() {
        BarColor[] result = new BarColor[4];

        fillRow(result, hours, value -> value >= 5, value -> value - 5,BarColor.RED);

        return result;
    }

    public BarColor[] getSecondRow() {
        BarColor[] result = new BarColor[4];

        fillRow(result, hours % 5, value -> value > 0, (value) -> value - 1,BarColor.RED);

        return result;
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

    public BarColor[] getFourthRow() {
        BarColor[] colors = new BarColor[4];

        fillRow(colors,minutes % 5,value -> value > 0,value -> value - 1,BarColor.YELLOW);

        return colors;
    }

    private <T> void fillRow(BarColor[] colors, T initValue, Predicate<T> tester,
                             Transformer<T> transformer,BarColor successColor) {
        for (int i = 0; i < colors.length; i++) {

            if (tester.test(initValue)) {
                colors[i] = successColor;
            } else {
                colors[i] = BarColor.NONE;
            }
            initValue = transformer.transform(initValue);
        }
    }

    public void setSeconds(int seconds) {
        if (seconds < 0 || seconds > 59) {
            throw new IllegalArgumentException("Seconds are invalid.Valid seconds [0-59]");
        }
        this.seconds = seconds;
    }

    public void setHours(int hours) {
        if (hours < 1 || hours > 24) {
            throw new IllegalArgumentException("Hours are invalid.Correct values [1,24].");
        }
        this.hours = hours;
    }

    public void setMinutes(int minutes) {
        if (minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException("Minutes are invalid.Valid values [0,59]");
        }
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
}
