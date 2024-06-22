package bankingProj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
    private final long customerId;
    private String customerName;
    private String customerAddress;
    private String customerEmail;
    private String customerPhone;
    private static int count = 0;

    public Customer(String customerName, String address, String email, String phone) {
        this.customerName = customerName;
        this.customerAddress = address;
        this.customerEmail = email;
        this.customerPhone = phone;
        this.customerId = generateCustomerId();
    }
    public Customer(String customerName, String email, String phone) {
        this.customerName = customerName;
        this.customerAddress = "-";
        this.customerEmail = email;
        this.customerPhone = phone;
        this.customerId = generateCustomerId();
    }
    public Customer(String customerName, String phone) {
        this.customerName = customerName;
        this.customerAddress = "-";
        this.customerEmail = "-";
        this.customerPhone = phone;
        this.customerId = generateCustomerId();
    }

    private long generateCustomerId() {
        return System.currentTimeMillis() + count++;
    }
}
