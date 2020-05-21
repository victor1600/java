package streams;

import imperative.main;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import static streams._Stream.Gender.FEMALE;
import static streams._Stream.Gender.MALE;

public class _Stream {
    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("John", MALE),
                new Person("Maria", FEMALE),
                new Person("Aisha", FEMALE),
                new Person("Alex", MALE),
                new Person("Alice", FEMALE)
        );

        System.out.println(" Example1: Getting diferent genders");
        people.stream()
                .map(person -> person.gender)
                .collect(Collectors.toSet())
                .forEach(System.out::println);
        // For each takes in a consumer, thats why it does returns anything

        System.out.println("Example2: Getting different names");
        people.stream()
                .map(person -> person.name)
                .collect(Collectors.toSet())
                .forEach(System.out::println);

        System.out.println("Example3: Getting the length of every name");
        people.stream()
                .map(person -> person.name)
                .mapToInt(String::length)
                .forEach(System.out::println);

        System.out.println("Example 3 illustrated: Seeing whats happening behind the scenes" );

        Function<Person, String> personStringFunction = person -> person.name;
        // Takes in a string and returns an integer
        ToIntFunction<String> length = String::length;
        IntConsumer println = x -> System.out.println(x);
        people.stream()
                .map(personStringFunction)
                .mapToInt(length)
                .forEach(println);

        System.out.println("example 4");
        Predicate<Person> personPredicate = person -> FEMALE.equals(person.gender);
        boolean containsOnlyFemales = people.stream()
                // Want to know that in our list we only have females
                .allMatch(personPredicate);
        System.out.println(containsOnlyFemales);


        boolean containsAnyFemales = people.stream()
                // Want to know that in our list we at least on female
                .anyMatch(person -> FEMALE.equals(person.gender));
        System.out.println(containsAnyFemales);


        boolean doesNotContainFemales = people.stream()
                // Want to know that in our list we dont have females
                .noneMatch(person -> FEMALE.equals(person.gender));
        System.out.println(containsAnyFemales);
        System.out.println(doesNotContainFemales);



    }

    static class Person {
        private final String name;
        private final Gender gender;

        public Person(String name, Gender gender) {
            this.name = name;
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", gender=" + gender +
                    '}';
        }
    }

    enum Gender {
        MALE, FEMALE
    }
}
