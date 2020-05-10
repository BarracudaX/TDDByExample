package stringCalculator;

public class StringCalculator {


    public int add(String numbers) {
        String[] arguments = numbers.split(",");

        int sum  = 0;

        for (String argument : arguments) {
            if (!argument.isEmpty()) {
                sum += Integer.parseInt(argument);
            }
        }

        return sum;
    }

}
