package agency.http.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import agency.entity.Customer;
import agency.exceptions.ValidationException;
import agency.repositories.CustomerRepository;

@RestController
@RequestMapping("/api/customer")
public class CustomerController extends AbstractController {

    @Autowired
    private CustomerRepository customers;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Customer> index(@RequestParam(value = "page") int page) {
        Pageable pageable = new PageRequest(--page, perPage);

        return customers.findAll(pageable);
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public Customer show(@RequestParam(value="id") String id) {
        return this.customers.findOne(id);
    }
    @RequestMapping(method = RequestMethod.POST)
    public Customer create(@Valid @RequestBody Customer customer, BindingResult result) throws ValidationException {

        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        return this.customers.save(customer);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Customer save(@Valid @RequestBody Customer customer, BindingResult result) throws ValidationException {

        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        return this.customers.save(customer);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public Customer delete(@RequestParam(value = "id") String id) {

        Customer user = this.customers.findOne(id);

        customers.delete(user);

        return user;
    }
}
