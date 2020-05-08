package berlin;

import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class TestValues {

    private static Stream<Arguments> firstRowArguments() {
        return Stream.of(
                arguments(1, expectedRow(0,4,BarColor.RED)),
                arguments(4, expectedRow(0,4,BarColor.RED)),
                arguments(5, expectedRow(1,3,BarColor.RED)),
                arguments(9, expectedRow(1,3,BarColor.RED)),
                arguments(10, expectedRow(2,2,BarColor.RED)),
                arguments(14, expectedRow(2,2,BarColor.RED)),
                arguments(15, expectedRow(3,1,BarColor.RED)),
                arguments(19, expectedRow(3,1,BarColor.RED)),
                arguments(20, expectedRow(4,0,BarColor.RED)),
                arguments(23, expectedRow(4,0,BarColor.RED)),
                arguments(24, expectedRow(4,0,BarColor.RED))
        );
    }

    private static Stream<Arguments> secondRowArguments() {
        return Stream.of(
                arguments(5,expectedRow(0,4,BarColor.RED)),
                arguments(10, expectedRow(0,4,BarColor.RED)),
                arguments(15, expectedRow(0,4,BarColor.RED)),
                arguments(20, expectedRow(0,4,BarColor.RED)),
                arguments(6, expectedRow(1,3,BarColor.RED)),
                arguments(11, expectedRow(1,3,BarColor.RED)),
                arguments(16, expectedRow(1,3,BarColor.RED)),
                arguments(21, expectedRow(1,3,BarColor.RED)),
                arguments(2, expectedRow(2,2,BarColor.RED)),
                arguments(7, expectedRow(2,2,BarColor.RED)),
                arguments(12, expectedRow(2,2,BarColor.RED)),
                arguments(17, expectedRow(2,2,BarColor.RED)),
                arguments(22, expectedRow(2,2,BarColor.RED)),
                arguments(3, expectedRow(3,1,BarColor.RED)),
                arguments(8, expectedRow(3,1,BarColor.RED)),
                arguments(13, expectedRow(3,1,BarColor.RED)),
                arguments(23, expectedRow(3,1,BarColor.RED)),
                arguments(4, expectedRow(4,0,BarColor.RED)),
                arguments(9, expectedRow(4,0,BarColor.RED)),
                arguments(14, expectedRow(4,0,BarColor.RED)),
                arguments(19, expectedRow(4,0,BarColor.RED)),
                arguments(24, expectedRow(4,0,BarColor.RED))
        );
    }

    private static Stream<Arguments> thirdRowArguments() {
        return Stream.of(
                arguments(
                        35,
                        new BarColor[]{
                                BarColor.YELLOW,BarColor.YELLOW,BarColor.RED,BarColor.YELLOW,BarColor.YELLOW,
                                BarColor.RED,BarColor.YELLOW,BarColor.NONE,BarColor.NONE,BarColor.NONE,BarColor.NONE}
                ),
                arguments(
                        53,
                        new BarColor[]{
                                BarColor.YELLOW,BarColor.YELLOW,BarColor.RED,BarColor.YELLOW,BarColor.YELLOW,
                                BarColor.RED,BarColor.YELLOW,BarColor.YELLOW,BarColor.RED,BarColor.YELLOW,BarColor.NONE}
                ),
                arguments(
                        22,
                        new BarColor[]{
                                BarColor.YELLOW,BarColor.YELLOW,BarColor.RED,BarColor.YELLOW,BarColor.NONE,
                                BarColor.NONE,BarColor.NONE,BarColor.NONE,BarColor.NONE,BarColor.NONE,BarColor.NONE}
                ),
                arguments(
                        14,
                        new BarColor[]{
                                BarColor.YELLOW,BarColor.YELLOW,BarColor.NONE,BarColor.NONE,BarColor.NONE,
                                BarColor.NONE,BarColor.NONE,BarColor.NONE,BarColor.NONE,BarColor.NONE,BarColor.NONE}
                )
        );
    }

    private static Stream<Arguments> fourthRowArguments(){
        return Stream.of(
                arguments(0, expectedRow(0, 4, BarColor.YELLOW)),
                arguments(5, expectedRow(0, 4, BarColor.YELLOW)),
                arguments(10, expectedRow(0, 4, BarColor.YELLOW)),
                arguments(15, expectedRow(0, 4, BarColor.YELLOW)),
                arguments(20, expectedRow(0, 4, BarColor.YELLOW)),
                arguments(25, expectedRow(0, 4, BarColor.YELLOW)),
                arguments(30, expectedRow(0, 4, BarColor.YELLOW)),
                arguments(35, expectedRow(0, 4, BarColor.YELLOW)),
                arguments(40, expectedRow(0, 4, BarColor.YELLOW)),
                arguments(45, expectedRow(0, 4, BarColor.YELLOW)),
                arguments(50, expectedRow(0, 4, BarColor.YELLOW)),
                arguments(55, expectedRow(0, 4, BarColor.YELLOW)),

                arguments(1, expectedRow(1, 3, BarColor.YELLOW)),
                arguments(6, expectedRow(1, 3, BarColor.YELLOW)),
                arguments(11, expectedRow(1, 3, BarColor.YELLOW)),
                arguments(16, expectedRow(1, 3, BarColor.YELLOW)),
                arguments(21, expectedRow(1, 3, BarColor.YELLOW)),
                arguments(26, expectedRow(1, 3, BarColor.YELLOW)),
                arguments(31, expectedRow(1, 3, BarColor.YELLOW)),
                arguments(36, expectedRow(1, 3, BarColor.YELLOW)),
                arguments(41, expectedRow(1, 3, BarColor.YELLOW)),
                arguments(46, expectedRow(1, 3, BarColor.YELLOW)),
                arguments(51, expectedRow(1, 3, BarColor.YELLOW)),
                arguments(56, expectedRow(1, 3, BarColor.YELLOW)),

                arguments(2, expectedRow(2, 2, BarColor.YELLOW)),
                arguments(7, expectedRow(2, 2, BarColor.YELLOW)),
                arguments(12, expectedRow(2, 2, BarColor.YELLOW)),
                arguments(17, expectedRow(2, 2, BarColor.YELLOW)),
                arguments(22, expectedRow(2, 2, BarColor.YELLOW)),
                arguments(27, expectedRow(2, 2, BarColor.YELLOW)),
                arguments(32, expectedRow(2, 2, BarColor.YELLOW)),
                arguments(37, expectedRow(2, 2, BarColor.YELLOW)),
                arguments(42, expectedRow(2, 2, BarColor.YELLOW)),
                arguments(47, expectedRow(2, 2, BarColor.YELLOW)),
                arguments(52, expectedRow(2, 2, BarColor.YELLOW)),
                arguments(57, expectedRow(2, 2, BarColor.YELLOW)),

                arguments(3, expectedRow(3, 1, BarColor.YELLOW)),
                arguments(8, expectedRow(3, 1, BarColor.YELLOW)),
                arguments(13, expectedRow(3, 1, BarColor.YELLOW)),
                arguments(18, expectedRow(3, 1, BarColor.YELLOW)),
                arguments(23, expectedRow(3, 1, BarColor.YELLOW)),
                arguments(28, expectedRow(3, 1, BarColor.YELLOW)),
                arguments(33, expectedRow(3, 1, BarColor.YELLOW)),
                arguments(38, expectedRow(3, 1, BarColor.YELLOW)),
                arguments(43, expectedRow(3, 1, BarColor.YELLOW)),
                arguments(48, expectedRow(3, 1, BarColor.YELLOW)),
                arguments(53, expectedRow(3, 1, BarColor.YELLOW)),
                arguments(58, expectedRow(3, 1, BarColor.YELLOW)),

                arguments(4, expectedRow(4, 0, BarColor.YELLOW)),
                arguments(9, expectedRow(4, 0, BarColor.YELLOW)),
                arguments(14, expectedRow(4, 0, BarColor.YELLOW)),
                arguments(19, expectedRow(4, 0, BarColor.YELLOW)),
                arguments(24, expectedRow(4, 0, BarColor.YELLOW)),
                arguments(29, expectedRow(4, 0, BarColor.YELLOW)),
                arguments(34, expectedRow(4, 0, BarColor.YELLOW)),
                arguments(39, expectedRow(4, 0, BarColor.YELLOW)),
                arguments(44, expectedRow(4, 0, BarColor.YELLOW)),
                arguments(49, expectedRow(4, 0, BarColor.YELLOW)),
                arguments(54, expectedRow(4, 0, BarColor.YELLOW)),
                arguments(59, expectedRow(4, 0, BarColor.YELLOW))
        );
    }

    private static BarColor[] expectedRow(int lampsOn, int none,BarColor lampColor) {
        BarColor[] colors = new BarColor[lampsOn + none];

        for (int i = 0; i < lampsOn; i++) {
            colors[i] = lampColor;
        }

        for (int j = lampsOn; j < lampsOn + none; j++) {
            colors[j] = BarColor.NONE;
        }

        return colors;
    }

    private static Stream<Arguments> secondsArguments() {
        Collection<Arguments> arguments = new ArrayList<>();

        for (int i = 0; i < 59; i++) {
            arguments.add(arguments(i, i % 2 == 0 ? BarColor.YELLOW : BarColor.NONE));
        }

        return arguments.stream();
    }

    private static IntStream sourceOfInvalidHours(){
        return IntStream.of(-1, -24, 0,25, 10_000, -5_000);
    }

    private static IntStream sourceOfInvalidMinutes(){
        return IntStream.of(-1, -50, -10_000, 60, 10_000);
    }

    private static IntStream sourceOfInvalidSeconds(){
        return IntStream.of(-1, -50, -10_000, 60, 70, 10_000);
    }

}
