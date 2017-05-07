package agency.http.interceptors;

import agency.entity.User;
import com.mongodb.util.JSON;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static agency.services.AuthService.USER_REQUEST_KEY;


public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("INTERCEPTION ACTIVE !");

        System.out.println("USER" + request.getAttribute(USER_REQUEST_KEY));

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
        }

        return true;
    }
}
