## Functional programmint

## Keyboard Shortcuts

```java
ctrl +d = duplicate
psvm + tab = private void static
sout = system out println
ctrl+ alt +v = extract variable
ctrl + w = extend selection
ctrl + backspace = delete word
shft+ctrl+enter = complete current statement
shft+f6 = rename
ctrl + b -> navigate declaration or usages
alt +1 -> colaps lateral bar
```

## Function <>

- Calling the function:
    - You call it with .apply
    

- Lambda function:
    - You define the input and output type between ```<>```
    - The number before ````->``` represents the argument the function takes in
    - What's after ```->``` is the return statement

### Chaining functions

- We use the .AndThen
- You can chain them and create a new function
- You can execute them at the same time we chain them.


```java
package functionalinterface;

import java.util.function.Function;

public class _Function {
    public static void main(String[] args) {
        int increment = incrementByOne(0);
        System.out.println(increment);

        Integer increment2 = incrementByOneFunction.apply(0);
        System.out.println(increment2);

        Function<Integer, Integer> addBy1AndThenMultiplyBy10 =
                incrementByOneFunction.andThen(multipleBy10Function);
        System.out.println(addBy1AndThenMultiplyBy10.apply(1));

        System.out.println(incrementByOneFunction.andThen(multipleBy10Function).apply(1));


    }

    static Function<Integer, Integer> incrementByOneFunction = number -> number + 1;

    static Function<Integer, Integer> multipleBy10Function = number -> number * 10;

    static int incrementByOne(int number) {
        return number + 1;
    }


}

```

## BiFunction

Instead of taking one input, it takes two inputs and produces one Output

## Consumer 
(It's like a void function)
- Takes 1 input

- It gets executed with ```.accept```

```java
static Consumer<Customer> greetCustomerConsumer = customer ->
            System.out.println("Hello " + customer.customerName +
                    ", thanks for registering phone Number "
                    + customer.customerPhoneNumber);
```


## Biconsumer and Conditionals in java

- Takes two arguments as input

```java
static BiConsumer<Customer, Boolean> greetCustomerConsumerV2 = (customer, showPhoneNumber) ->
            System.out.println("Hello " + customer.customerName +
                    ", thanks for registering phone Number "
                    + (showPhoneNumber ? (customer.customerPhoneNumber) : "***"));

```

## Predicate
Boolean valued function of one argument

- You can chain them and use ```and``` or ```or```
```java
package functionalinterface;

import java.util.function.Predicate;

public class _Predicate {
    public static void main(String[] args) {
        System.out.println("Without predicate");
        System.out.println(isPhoneNumberValid("07000000000"));
        System.out.println(isPhoneNumberValid("09330000000"));
        System.out.println("With predicate");
        System.out.println(isPhoneNumberValidPredicate.test("07000000003"));

        // Chaining predicates
        System.out.println("Chaining predicates");
        System.out.println(isPhoneNumberValidPredicate.and(containsNumber3).test("07000000000"));
        System.out.println(isPhoneNumberValidPredicate.or(containsNumber3).test("07000000003"));

    }

    static Predicate<String> isPhoneNumberValidPredicate = phoneNumber ->
            phoneNumber.startsWith("07") && phoneNumber.length() == 11;

    static Predicate<String> containsNumber3 = phoneNumber ->
            phoneNumber.contains("3");

    static boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber.startsWith("07") && phoneNumber.length() == 11;
    }
}

``` 

### Supplier
Returns a value!, takes in no arguments.

```java
package functionalinterface;

import java.util.List;
import java.util.function.Supplier;

public class _Supplier {
    public static void main(String[] args) {
        System.out.println(getDBConnectionUrl());
        System.out.println(getDBConnectionUrlSupplier.get());
        System.out.println(getDBConnectionUrlSuppliers.get());
    }

    // Returns a value, takes no arguments
    static String getDBConnectionUrl() {
        return "jdbc://localhost:5432/users";
    }

    static Supplier<String> getDBConnectionUrlSupplier = () ->
            "jdbc://localhost:5432/users";

    static Supplier<List<String>> getDBConnectionUrlSuppliers = () ->
            List.of("jdbc://localhost:5432/users",
                    "jdbc://localhost:5432/users");
}

```

## Extras

- Conditional: see biConsumer
- Starts with: 
```java
static boolean isPhoneNumberValid(String phoneNumber){
        return phoneNumber.startsWith("07") && phoneNumber.length() == 11;
    }
```

- filling a list ```List.of()``:
```java
static Supplier<List<String>> getDBConnectionUrlSuppliers = () ->
            List.of("jdbc://localhost:5432/users",
                    "jdbc://localhost:5432/users");
```

# Streams
[Streams](https://github.com/amigoscode/java-streams-youtube/blob/master/src/main/java/Main.java)

### Map
Does transformations on element or elements.

- Example 1: Getting the  gender of every person
    - We stream the people list
    - We map, convert every single one of the om genders, so we have a list full o genders.
    - We collect to a set **Removing duplicates**
    - We use for Each
    
- Example 2: Getting the names of every person

- Example3b: Getting the lenght of every name

As you can see, the arguemnts of map, forEach, mapToInt, etc, are the functional methods we learned above like:
function, bifunction, consumer and predicate.


## Optional

- Works with suppliers
> Inside orElseGet we can do any extra computation we want to modify the value

```java
orElseGet( ()->{
// .. Extra computation to retrieve the value
return ""; // return the value
})
```

```java
Object value = Optional.ofNullable("Hello")
                .orElseGet(() -> "default value");

        System.out.println(value);
```

- **orElse throw**
```java
Supplier<IllegalStateException> exception = () -> new IllegalStateException("Exception");
        Object value = Optional.ofNullable("Hello")
                .orElseThrow(exception);
```

- **ifPresent**:

```java
Optional.ofNullable("john@gmail.com")
                .ifPresent(email -> System.out.println("Sending email to " + email));

>> Sending email to john@gmail.com
```

- Check if email es valid or is empty:

```java
Optional.ofNullable("john@gmail.com")
                .ifPresentOrElse(
                        email -> System.out.println("Sending email to " + email), () -> {
                            System.out.println("Cannot send email");
                        });

>> Sending email to John

Optional.ofNullable(null)
                .ifPresentOrElse(
                        email -> System.out.println("Sending email to " + email), () -> {
                            System.out.println("Cannot send email");
                        });

>> Cannot send email
```

> Optional has to mehthods "of" and "ofNullable", of means we are sure the value is not null


## Example: Checking if a member of a class is null NULL VALUES VALIDATED

- We let the user know a filed might be nullablle using optional in the getter.

```java
package Optionals;

import org.w3c.dom.ls.LSOutput;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
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
```

## Tricks
printing elements in list
```java
females.forEach(System.out::println);
```