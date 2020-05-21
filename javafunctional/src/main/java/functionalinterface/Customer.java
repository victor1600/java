package functionalinterface;

import java.time.LocalDate;

public class Customer  {
    private final String name;
    private final String email;
    private final String phonNumber;
    private final LocalDate dob;

    public Customer(String name, String email, String phonNumber, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.phonNumber = phonNumber;
        this.dob = dob;
    }
}
