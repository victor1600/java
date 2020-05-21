package functionalinterface;

import java.util.function.BiFunction;
import java.util.function.Function;

public class _Function {
    public static void main(String[] args) {
        int increment = incrementByOne(0);
        System.out.println(increment);

        // Function takes 1 argument and produces 1 result
        Integer increment2 = incrementByOneFunction.apply(0);
        System.out.println(increment2);

        // Chaining and creaning a new function
        Function<Integer, Integer> addBy1AndThenMultiplyBy10 =
                incrementByOneFunction.andThen(multipleBy10Function);
        System.out.println(addBy1AndThenMultiplyBy10.apply(1));
        // Chaning and executing at once
        System.out.println(incrementByOneFunction.andThen(multipleBy10Function).apply(1));

        // BiFunction: Takes 2 argument and produes 1 result
        // Classic
        System.out.println(incrementByOneAndMultiply(4, 100));
        // BiFunc
        System.out.println(incrementByOneAndMultiplyBiFunction.apply(4, 100));

    }

    static Function<Integer, Integer> incrementByOneFunction = number -> number + 1;

    static Function<Integer, Integer> multipleBy10Function = number -> number * 10;

    static BiFunction<Integer, Integer, Integer> incrementByOneAndMultiplyBiFunction =
            (numberToIncrementByOne, numberToMultiplyBy)
                    -> (numberToIncrementByOne + 1) * numberToMultiplyBy;

    static int incrementByOne(int number) {
        return number + 1;
    }

    static int incrementByOneAndMultiply(int number, int numToMultiplyBy) {
        return (number + 1) * numToMultiplyBy;
    }


}
