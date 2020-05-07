package berlin;

public class BerlinClock {

    private int hours;

    private int minutes;

    private int seconds;

    public BerlinClock(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public boolean isLightOn() {
        return seconds % 2 == 0;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public boolean[] getFirstRow() {
        boolean[] result = new boolean[4];

        int hours = this.hours;

        for (int i = 0; i < result.length; i++) {
            if (hours >= 5) {
                hours -= 5;
                result[i]=true;
            }
        }

        return result;
    }

    public boolean[] getSecondRow() {
        boolean[] result = new boolean[4];

        int hours = this.hours;

        hours %= 5;

        for (int i = 0; i < result.length; i++) {
            if (hours > 0) {
                hours -= 1;
                result[i] = true;
            }
        }

        return result;
    }
}
