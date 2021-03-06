package com.poddubchak.oauth.Security;

import com.poddubchak.oauth.Service.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class TokenAuthService {
    private static final String AUTH_HEADER_NAME ="X-Auth-Token";


    @Autowired
    UserService userService;

    @Autowired
    TokenHandler tokenHandler;

    public Optional<Authentication> getAuthentication(@NonNull HttpServletRequest request) {

        return Optional
                .ofNullable(request.getHeader(AUTH_HEADER_NAME))
                .flatMap(tokenHandler::extractUserId)
                .flatMap(userService::findById)
                .map(UserAuthentication::new);






    }
}
