package agency.http.controllers;

import agency.entity.Product;
import agency.exceptions.LogicException;
import agency.exceptions.ValidationException;
import agency.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController extends AbstractController {

    @Autowired
    private ProductRepository products;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Product> index(@RequestParam(value = "page") int page) {
        Pageable pageable = new PageRequest(--page, perPage);

        return products.findAll(pageable);
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public Product show(@RequestParam(value="id") String id) {
        return this.products.findOne(id);
    }
    @RequestMapping(method = RequestMethod.POST)
    public Product create(@Valid @RequestBody Product customer, BindingResult result) throws ValidationException, LogicException {

        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        return this.products.save(customer);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Product save(@Valid @RequestBody Product customer, BindingResult result) throws ValidationException, LogicException {

        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        return this.products.save(customer);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public Product delete(@RequestParam(value = "id") String id) {

        Product user = this.products.findOne(id);

        products.delete(user);

        return user;
    }
}
