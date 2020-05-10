package stringCalculator;

import java.util.*;
import java.util.regex.Pattern;

public class StringCalculator {
    private static final String DEFAULT_DELIMITER = ",";

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

    private String[] getStringNumbers(String numbers) {
        String[] delimiters = getDelimiters(numbers);

        String[] arguments;

        if (delimiters.length != 0) {
            arguments = splitNumbersAccordingToDelimiters(delimiters, numbers);
        } else {
            arguments = numbers.lines().toArray(String[]::new);//Split using \n

            if (arguments.length == 1) {//If only single line,split using default delimiter
                arguments = arguments[0].split(DEFAULT_DELIMITER);
            }

        }
        return arguments;
    }

    private String[] splitNumbersAccordingToDelimiters(String[] delimiters,String numbers) {

        String[] arguments = numbers.split("\n")[1].split(Pattern.quote(delimiters[0]));
        for (int i = 1; i < delimiters.length; i++) {
            List<String> result = new ArrayList<>();
            for (String argument : arguments) {
                if (argument.contains(delimiters[i])) {
                    result.addAll(Arrays.asList(argument.split(Pattern.quote(delimiters[i]))));
                }else{
                    result.add(argument);
                }
            }
            arguments = result.toArray(new String[0]);
        }

        return arguments;
    }

    private String[] getDelimiters(String numbers) {

        //orElse impossible of first if in add method.
        String firstLine = numbers.lines().findFirst().orElse("");

        if (firstLine.startsWith("//")) {
            StringBuilder delimiter = new StringBuilder();

            List<String> delimiters = new ArrayList<>();

            for (int i = 2; i < firstLine.length(); i++) {
                if (firstLine.charAt(i) == ']') {
                    delimiters.add(delimiter.toString());
                    delimiter = new StringBuilder();
                } else if (firstLine.charAt(i) != '[') {
                    delimiter.append(firstLine.charAt(i));
                }
            }
            return delimiters.stream().sorted((s, t1) -> t1.length() - s.length())
                    .toArray(String[]::new);
        }

        return new String[0];
    }

}
