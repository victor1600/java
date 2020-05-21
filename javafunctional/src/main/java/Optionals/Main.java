package Optionals;

import org.w3c.dom.ls.LSOutput;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Supplier<IllegalStateException> exception = () -> new IllegalStateException("Exception");

        Optional.ofNullable("john@gmail.com")
                .ifPresentOrElse(
                        email -> System.out.println("Sending email to " + email), () -> {
                            System.out.println("Cannot send email");
                        });


        Optional<String> hello = Optional.ofNullable(null);
        System.out.println(hello.isPresent());
        System.out.println(hello.isEmpty());

        String orElse = hello
                .map(String::toUpperCase)
                .orElseGet(() -> {
                    // Extra computation to retrieve the value
                    return "world";
                });
        System.out.println(orElse);

        // If Present
        hello.ifPresent(word -> {
            System.out.println(word);
        });

        // Or Else
        hello.ifPresentOrElse(System.out::println, () -> System.out.println("World"));

//        Employee employee1 = new Employee();
//
//        Optional.ofNullable(employee1.name)
//                .ifPresentOrElse(System.out::println, () -> {
//                            employee1.name = "John";
//                            System.out.println(employee1.name);
//                        }
//                );
//
//        Employee employee2 = new Employee();
//        employee2.name = "Victor";
//        Optional.ofNullable(employee2.name)
//                .ifPresentOrElse(System.out::println, () -> {
//                            employee1.name = "John";
//                            System.out.println(employee2.name);
//                        }
//                );
        Employee person = new Employee("james", null);
        String email = person
                .getEmail()
                .map(String::toLowerCase)
                .orElse("Email not provided");

        System.out.println(email);

    }


}


class Employee {
    private final String name;
    private final String email;

    public Employee(String name, String email) {
        this.name = name;
        this.email = email;
    }


    public String getName() {
        return name;
    }

    // Letting the user know this field it is optional
    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }
}