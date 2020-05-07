package berlin;

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

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setHours(int hours) {
        if (hours < 0 || hours > 23) {
            throw new IllegalArgumentException("Hours are invalid.Correct values [0,23].");
        }
        this.hours = hours;
    }

    public BarColor[] getFirstRow() {
        BarColor[] result = new BarColor[4];

        int hours = this.hours;

        for (int i = 0; i < result.length; i++) {
            if (hours >= 5) {
                hours -= 5;
                result[i] = BarColor.RED;
            }else{
                result[i] = BarColor.NONE;
            }
        }

        return result;
    }

    public BarColor[] getSecondRow() {
        BarColor[] result = new BarColor[4];

        int hours = this.hours;

        hours %= 5;

        for (int i = 0; i < result.length; i++) {
            if (hours > 0) {
                hours -= 1;
                result[i] = BarColor.RED;
            }else{
                result[i] = BarColor.NONE;
            }
        }

        return result;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public BarColor[] getThirdRow() {
        BarColor[] colors = new BarColor[11];
        int counter = 0;
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
}
