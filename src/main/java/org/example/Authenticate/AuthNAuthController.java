package org.example.Authenticate;

import jakarta.ws.rs.core.*;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "v1/oauth2/")
@AllArgsConstructor
public class AuthNAuthController {

    @Autowired
    private final AuthNAuthService authNAuthService;

    /**
     *  Authentication API - returns access token in JWT format
     * @param authorizationHeader
     * @param request
     * @return
     */
    @PostMapping(path = "login")
    public ResponseEntity<?> login(@RequestHeader("Authorization") String authorizationHeader, @RequestBody LoginRequest request) {
        return authNAuthService.authenticateUser(authorizationHeader, request);
    }




    @PostMapping(path = "authorize")
    public ResponseEntity<?> authorize(@RequestHeader("Authorization") String authorizationHeader, @RequestBody AuthorizationRequest request) {
        return authNAuthService.authorizeUser(authorizationHeader, request);
    }
}
