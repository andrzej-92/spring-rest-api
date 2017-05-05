package agency.security.filter;

import org.springframework.beans.factory.annotation.Autowired;
import agency.security.model.JwtUser;
import agency.security.service.JWTService;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/api/secure/*"})
public class JwtFilter implements Filter {

    @Autowired
    private JWTService jwtTokenService;

    @Value("${auth.jwt.header}")
    private String authHeader;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}


    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain
    ) throws IOException, ServletException {

        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        String authHeaderVal = httpRequest.getHeader(authHeader);

        authHeaderVal = authHeaderVal.substring(7); // Skip 'Bearer '

        if (0 == authHeaderVal.length()) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        try {
            JwtUser jwtUser = jwtTokenService.getUser(authHeaderVal);
            httpRequest.setAttribute("jwtUser", jwtUser);
        } catch (JwtException e) {
            httpResponse.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return;
        }

        chain.doFilter(httpRequest, httpResponse);
    }
}
