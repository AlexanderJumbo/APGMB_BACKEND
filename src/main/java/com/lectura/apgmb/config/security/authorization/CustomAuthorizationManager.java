package com.lectura.apgmb.config.security.authorization;

import com.lectura.apgmb.entities.secutiry.Operation;
import com.lectura.apgmb.entities.secutiry.User;
import com.lectura.apgmb.exceptions.ObjectNotFoundException;
import com.lectura.apgmb.repositories.auth.OperationRepository;
import com.lectura.apgmb.services.auth.JwtService;
import com.lectura.apgmb.services.auth.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;


    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext requestContext) {
        HttpServletRequest request = requestContext.getRequest();
        String url = extractUrl(request);
        String httpMethod = request.getMethod();

        String token = jwtService.extractJwtFromRequest(requestContext.getRequest());

        boolean isPublic = isPublic(url, httpMethod);

        if (isPublic){
            return new AuthorizationDecision(true);
        }

        //Verificar si la petición es para crear un módulo o una operación
        boolean isExtraordinaryAction = isExtraordinaryAction(url, httpMethod, authentication.get(), token);
        if(isExtraordinaryAction) return new AuthorizationDecision(true);

        boolean isGranted = isGranted(url, httpMethod, authentication.get());
        return new AuthorizationDecision(isGranted);
    }

    private boolean isExtraordinaryAction(String url, String httpMethod, Authentication authentication, String token) {

        Claims claims = jwtService.iExtractAllClaims(token);

        if(authentication == null || !(authentication instanceof UsernamePasswordAuthenticationToken)){
            throw new AuthenticationCredentialsNotFoundException("User not logged in");
        }

        boolean isExtraordinaryAction = url.equals("/api/module") || url.equals("/api/operation") || url.equals("/api/permission");
        return isExtraordinaryAction && claims.get("role").equals("ADMINISTRATOR");
    }

    private boolean isGranted(String url, String httpMethod, Authentication authentication) {

        if(authentication == null || !(authentication instanceof UsernamePasswordAuthenticationToken)){
            throw new AuthenticationCredentialsNotFoundException("User not logged in");
        }

        List<Operation> operations = obtainOperations(authentication);
        System.out.println("OPERATIONS: " + operations);
        boolean isGranted = operations.stream().anyMatch(getOperationPredicate(url, httpMethod));
        System.out.println("IS GRANTED " + isGranted);
        return isGranted;
    }

    private List<Operation> obtainOperations(Authentication authentication) {

        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
        String username = (String) authToken.getPrincipal();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("user not found. User: " + username));
        return user.getRole().getPermissions().stream()
                .map(grantedPermission -> grantedPermission.getOperation())
                .collect(Collectors.toList());
    }

    private boolean isPublic(String url, String httpMethod) {

        List<Operation> publicEndpoints = operationRepository.findPublicEndpoints();

        boolean isPublic = publicEndpoints.stream().anyMatch(getOperationPredicate(url, httpMethod));
        System.out.println("IS PUBLIC " + isPublic);
        return isPublic;
    }

    private Predicate<Operation> getOperationPredicate(String url, String httpMethod) {
        return operation -> {
            String basePath = operation.getModule().getBasePath();

            Pattern pattern = Pattern.compile(basePath.concat(operation.getPath()));
            Matcher matcher = pattern.matcher(url);

            return matcher.matches() && operation.getHttpMethod().equals(httpMethod);
        };
    }

    private String extractUrl(HttpServletRequest request) {

        String contextPath = request.getContextPath();
        String url = request.getRequestURI();
        url = url.replace(contextPath, "");

        System.out.println("URL: " + url);
        return url;
    }
}
