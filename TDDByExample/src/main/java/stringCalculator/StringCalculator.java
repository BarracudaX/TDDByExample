package stringCalculator;

public class StringCalculator {


    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        String[] arguments = numbers.split(",");

        int a = Integer.parseInt(arguments[0]);

        if (arguments.length == 1) {
            return a;
        }

        int b = Integer.parseInt(arguments[1]);

        return a + b;
    }

}
