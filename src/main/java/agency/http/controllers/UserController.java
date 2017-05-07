package agency.http.controllers;

import agency.entity.User;
import agency.exceptions.ValidationException;
import agency.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Value("${pagination.per_page}")
    private int perPage;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Map index(@RequestParam(value = "page") int page, Locale locale) {
        Pageable pageable = new PageRequest(page, perPage);

        Map <String, Object> response = new HashMap<>();
        response.put("content",  userRepository.findAll());
        response.put("lang",  locale);

        return response;
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public User show(@RequestParam(value = "id") String id) {

        return this.userRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public User create(@Valid @RequestBody User user, BindingResult result) throws ValidationException {

        if (StringUtils.isEmpty(user.getPassword())) {
            result.addError(new ObjectError("user", "Password must be set"));
        }

        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        return this.userRepository.save(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public User save(@Valid @RequestBody User user, BindingResult result) throws ValidationException {

        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        return this.userRepository.save(user);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public User delete(@RequestParam(value = "id") String id) {

        User user = this.userRepository.findOne(id);

        userRepository.delete(user);

        return user;
    }
}
