package agency.entity;

import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotNull;

public class Customer {

    @Id
    private String id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    public Customer() {

    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer setFirstName(String name) {
        this.firstName = name;

        return this;
    }
}
