package agency.http.controllers;

import agency.entity.Customer;
import agency.entity.Policy;
import agency.entity.User;
import agency.exceptions.LogicException;
import agency.exceptions.ValidationException;
import agency.repositories.CustomerRepository;
import agency.repositories.PoliciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static agency.services.AuthService.USER_REQUEST_KEY;

@RestController
@RequestMapping("/api/policy")
public class PoliciesController extends AbstractController {

    @Autowired
    private PoliciesRepository policies;

    @Autowired
    private CustomerRepository customers;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Policy> index(@RequestParam(value = "page") int page) {

        Sort sort = new Sort(Sort.Direction.DESC, "_id");
        Pageable pageable = new PageRequest(--page, perPage, sort);

        return policies.findAll(pageable);
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public Policy show(@RequestParam(value="id") String id) {
        return this.policies.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Policy create(@Valid @RequestBody Policy policy, BindingResult result, HttpServletRequest request) throws ValidationException, LogicException {

        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        policy.setUser((User) request.getAttribute(USER_REQUEST_KEY));

        return this.policies.save(policy);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Policy save(@Valid @RequestBody Policy policy, BindingResult result) throws ValidationException, LogicException {

        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        return this.policies.save(policy);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public Policy delete(@RequestParam(value = "id") String id) {

        Policy policy = this.policies.findOne(id);

        policies.delete(policy);

        return policy;
    }
}
