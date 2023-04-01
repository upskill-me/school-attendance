package me.upskill.schoolattendance.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.upskill.schoolattendance.api.ContextHolder;
import me.upskill.schoolattendance.api.ErrorCodes;
import me.upskill.schoolattendance.exceptions.StatusCodeMyException;
import me.upskill.schoolattendance.properties.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private AppProperties properties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod method = (HandlerMethod) handler;
        if (method.getMethod().isAnnotationPresent(AuthRequirement.class)) {
            // get header value
            String authHeader = request.getHeader("Authorization");
            // Bearer {token}
            String[] tokens = authHeader.split(" ");
            if (!"Bearer".equalsIgnoreCase(tokens[0])) {
                throw new StatusCodeMyException(ErrorCodes.INVALID_AUTH_HEADER, "Invalid Authorization header", 401);
            }
            String jwt = tokens[1];
            // decode jwt token
            Algorithm algorithm = Algorithm.HMAC256(properties.getSecretKey());
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(properties.getServiceName())
                    .build();
            try {
                DecodedJWT decodedJWT = verifier.verify(jwt);
                Map<String, Claim> claims = decodedJWT.getClaims();

                long userId = claims.get("userId").asLong();

                // authentication is done, we can also authorize here using the same method
                String[] requiredRoles = method.getMethodAnnotation(AuthRequirement.class).roles();
                List<String> userRoles = claims.get("roles").asList(String.class);

                for (String rr : requiredRoles) {
                    if (!userRoles.contains(rr)) {
                        throw new StatusCodeMyException(ErrorCodes.INVALID_AUTH_HEADER, "You don't have permission to access this api", 403);
                    }
                }
                ContextHolder.saveUserId(userId);
            } catch (Exception ex) {
                throw new StatusCodeMyException(ErrorCodes.INVALID_AUTH_HEADER, "Invalid Authorization header", 401);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // no op
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ContextHolder.clearUserId();
    }
}
