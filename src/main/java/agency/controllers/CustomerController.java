package agency.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import agency.entity.Customer;
import agency.exceptions.ValidationException;
import agency.repositories.CustomerRepository;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customers;

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> index() {

       return this.customers.findAll();
    }

    public Customer show(@RequestParam(value="id") String id) {

        return this.customers.findOne(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public Customer create(@Valid Customer customer, BindingResult result) throws ValidationException {

        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        return new Customer("New customer", "Surname");
    }

    public Customer save() {

        Customer customer = new Customer();
        customer.setFirstName("John");

        customers.save(customer);

        return customer;
    }

    public Customer delete() {

        Customer customer = new Customer();
        customer.setFirstName("John");


        return customer;
    }
}
