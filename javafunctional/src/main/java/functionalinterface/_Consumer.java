package functionalinterface;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class _Consumer {

    public static void main(String[] args) {
        Customer maria = new Customer("Maria", "999999");
        greetcustomer(maria);
        // Consumer functional interfaace
        greetCustomerConsumer.accept(maria);
        greetCustomerConsumerV2.accept(maria, false);

    }

    static BiConsumer<Customer, Boolean> greetCustomerConsumerV2 = (customer, showPhoneNumber) ->
            System.out.println("Hello " + customer.customerName +
                    ", thanks for registering phone Number "
                    + (showPhoneNumber ? (customer.customerPhoneNumber) : "***"));

    static Consumer<Customer> greetCustomerConsumer = customer ->
            System.out.println("Hello " + customer.customerName +
                    ", thanks for registering phone Number "
                    + customer.customerPhoneNumber);

    // Traditional way
    static void greetcustomer(Customer customer) {
        System.out.println("Hello " + customer.customerName +
                ", thanks for registering phone Number "
                + customer.customerPhoneNumber);
    }

    static class Customer {
        private final String customerName;
        private final String customerPhoneNumber;

        public Customer(String customerName, String customerPhoneNumber) {
            this.customerName = customerName;
            this.customerPhoneNumber = customerPhoneNumber;
        }
    }

}


