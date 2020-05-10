package stringCalculator;

public class StringCalculator {


    public int add(String numbers) {

        if (numbers.contains("\n") && numbers.contains(",")) {
            throw new IllegalArgumentException("Both:new line and comma cannot be used.");
        }

        String[] arguments = numbers.lines().toArray(String[]::new);

        if (arguments.length == 1) {
            arguments = arguments[0].split(",");
        }

        int sum  = 0;

        for (String argument : arguments) {
            if (!argument.isEmpty()) {
                sum += Integer.parseInt(argument);
            }
        }

        return sum;
    }

}
