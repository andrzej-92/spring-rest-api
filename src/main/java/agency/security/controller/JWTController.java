package agency.security.controller;

import agency.exceptions.BadCredentialsException;
import agency.security.dto.AuthDTO;
import agency.security.model.JwtUser;
import agency.security.model.User;
import agency.security.service.JWTService;
import agency.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class JWTController {
    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @GetMapping(value = "/api/secure/hello/{name}")
    public Map helloPublic(@PathVariable String name, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();

        response.put("Hello", name);

        response.put("user", request.getAttribute("jwtUser"));

        return response;
    }

    @GetMapping(value = "/api/public/hello/{name}")
    public Map helloSecure(@PathVariable String name) {
        Map<String, String> response = new HashMap<>();

        response.put("Hello", name);

        return response;
    }

    @PostMapping(value = "/api/login")
    public Map auth(@RequestBody AuthDTO auth) throws BadCredentialsException {

        String userName = auth.getUsername();
        String passWord = auth.getPassword();

        Boolean hasValidCredentials = userService.authenticate(userName, passWord);

        if (hasValidCredentials) {
            Map<String, String> response = new HashMap<>();
            JwtUser user = new JwtUser(userName, passWord);
            response.put("token", jwtService.getToken(user));

            return response;
        }

        throw new BadCredentialsException();
    }

    @GetMapping(value = "/api/me")
    public Map me() {

        Map<String, User> response = new HashMap<String, User>();

        response.put("user", this.userService.findUserByUserName("user1"));

        response.put("isMap", this.userService.findUserByUserName("admin@dev.local"));

        return response;
    }

    @GetMapping(value = "/api/products")
    public Map products(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();

        response.put("products", "Dummy products list");
        response.put("user", request.getAttribute("jwtUser"));

        return response;
    }
}