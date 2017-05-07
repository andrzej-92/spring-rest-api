package agency.services;

import agency.entity.User;
import agency.security.contracts.Authenticable;
import agency.security.contracts.UserProviderInterface;
import agency.security.model.JwtUser;
import agency.services.security.Hash;
import agency.services.security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    public static String USER_REQUEST_KEY = "AUTH_USER";

    @Autowired
    private UserProviderInterface userProvider;

    @Autowired
    private JWTService tokenService;

    @Autowired
    private Hash hash;

    private static Map<String, User> users = new HashMap<String, User>();

    static {
        User u = new User();
        u.setUsername("admin");
        u.setPassword("admin");
        u.setEmail("admin@dev.local");
        u.setIsActive(true);

        users.put("admin", u);
    }

    private User findUserByUserName(String userName) {
        return users.get(userName);
    }

    public boolean authenticate(String userName, CharSequence password) {

        User user = findUserByUserName(userName);

        if (null != user) {
            return user.getPassword().equals(password);
        }

        Authenticable mongoUser = this.userProvider.findUserByUsername(userName);

        if (null != mongoUser) {
            return this.hash.matches(password, mongoUser.getPassword());
        }

        return false;
    }

    public Authenticable user(HttpServletRequest request) {
        JwtUser u = this.tokenService.resolveUser(request);

        User memory = this.findUserByUserName(u.getUsername());

        if (null != memory) {
            return memory;
        }

        return this.userProvider.findUserByUsername(u.getUsername());
    }
}
