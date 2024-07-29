package org.example.Authenticate;

import lombok.AllArgsConstructor;
import org.example.JWT.JwtUtils;
import org.example.Response.AuthorizationResponse;
import org.example.Response.AuthorizationStatus;
import org.example.Response.ErrorResponse;
import org.example.Response.JwtResponse;
import org.example.model.AuthPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@AllArgsConstructor
public class AuthNAuthService {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthorizationManager authorizationManager;

    public ResponseEntity<?> authenticateUser(String headers, LoginRequest request) {

        try {
            authenticationManager.checkHeader(headers);
            AuthPrincipal principal = authenticationManager.validateUser(request);
            String jwt = jwtUtils.generateJwtToken(principal);

            return ResponseEntity.ok(new JwtResponse(jwt,
                    principal.getUsername(),
                    principal.getEmail(),
                    principal.getRoles()));
        } catch (Exception e) {
            return new ResponseEntity(new ErrorResponse(e.getMessage(), "Authentication Failure"), HttpStatus.UNAUTHORIZED);
        }
    }


    public ResponseEntity<?> authorizeUser(String authorizationHeader, AuthorizationRequest request) {

        try {
            AuthPrincipal principal = null;
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
                String authToken = authorizationHeader.split(" ")[1];
                if (authorizationManager.validateToken(authToken)) {
                    String userName = jwtUtils.getUserNameFromJwtToken(authToken);
                    principal = authorizationManager.getAuthPrincipal(userName);
                }
            } else {
                throw new Exception("invalid_bearer_token");
            }

            AuthorizationStatus authorizationStatus = authorizationManager.checkPermission(principal, request);

            return ResponseEntity.ok(new AuthorizationResponse(principal.id,
                        principal.getUsername(),
                        principal.getEmail(),
                        principal.getRoles(), authorizationStatus, principal.getPermissions()));

        } catch (Exception e) {
            return new ResponseEntity(new ErrorResponse(e.getMessage(), "Authorization Failure"), HttpStatus.UNAUTHORIZED);
        }
    }
}
