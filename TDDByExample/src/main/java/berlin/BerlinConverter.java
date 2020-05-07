package berlin;

public class BerlinConverter {

    private int hours;

    private int minutes;

    private int seconds;

    public BerlinConverter(int hours, int minutes, int seconds) {
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
}
