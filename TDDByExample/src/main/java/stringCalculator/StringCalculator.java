package stringCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class StringCalculator {


    public int add(String input) {
        if (input.isEmpty()) {
            return 0;
        }

        if (input.contains("\n") && input.contains(",")) {
            throw new IllegalArgumentException("Both:new line and comma cannot be used.");
        }

        String[] numbers = getStringNumbers(input);

        int sum = 0;

        List<Integer> negativeNumbers = new ArrayList<>();

        for (String argument : numbers) {
            int nextNumber = Integer.parseInt(argument);
            if (nextNumber < 0) {
                negativeNumbers.add(nextNumber);
            }
            if (nextNumber < 1001) {
                sum += nextNumber;
            }
        }

        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException("Negative numbers not allowed:" + negativeNumbers.toString());
        }

        return sum;
    }

    private Optional<String> getDelimiter(String numbers) {

        //orElse impossible of first if in add method.
        String firstLine = numbers.lines().findFirst().orElse("");

        String delimiter = null;

        if (firstLine.startsWith("//")) {
            delimiter = firstLine.substring(firstLine.indexOf('[')+1,firstLine.lastIndexOf(']'));
        }

        return Optional.ofNullable(delimiter);
    }

    private String[] getStringNumbers(String numbers) {
        Optional<String> delimiter = getDelimiter(numbers);

        String[] arguments;

        if (delimiter.isPresent()) {
            arguments = numbers.split("\n")[1].split(Pattern.quote(delimiter.get()));
        } else {
            arguments = numbers.lines().toArray(String[]::new);

            if (arguments.length == 1) {
                arguments = arguments[0].split(",");
            }

        }

        return arguments;
    }

}
