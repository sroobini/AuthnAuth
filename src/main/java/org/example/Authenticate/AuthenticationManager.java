package org.example.Authenticate;

import org.example.common.ObjectMapper;
import org.example.model.AuthPrincipal;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AuthenticationManager {

    private Map<String, String> allowedClientCredentials = new HashMap<>();

    private Map<String, User> userDB = new HashMap<>();

    @Autowired
    ObjectMapper objectMapper;

    protected void checkHeader(String headers) throws Exception {
        String authHeader = headers;
        String[] clientCredentials = null;
        if (authHeader != null) {
            clientCredentials = authHeader.split(":");
        }

        validateClientCredentials(clientCredentials);
    }

    private void validateClientCredentials(String[] clientCredentials) throws Exception {
        String clientId = clientCredentials[0];
        String clientSecret = clientCredentials[1];
        allowedClientCredentials = objectMapper.buildClientCredentialsMap();
        if(clientId != null && clientSecret != null) {
            if (allowedClientCredentials.get(clientId) == null ||
                    !allowedClientCredentials.get(clientId).equals(clientSecret)) {
                throw new Exception("Invalid Client");
            }
        }
    }

    protected AuthPrincipal validateUser(LoginRequest request) throws Exception {
        String userName = request.getUsername();
        //password is hashed by client and sent to the login api.
        // the server has only the hashed version of the password stored
        // only the hashed versions of the password is compared.
        String password = request.getPassword();

        if (userName != null && password != null) {
            User user = loadUserByUsername(userName);
            if (user == null) {
                throw new Exception("invalid_user");
            }
            validatePassword(user, request);
            return objectMapper.mapToAuthPrincipal((user));
        }
        return null;
    }

    private void validatePassword(User user, LoginRequest request) throws Exception {
        String password = request.getPassword();
        if(! password.equals(user.getPasswordHash())) {
            throw new Exception("invalid_password");
        }
    }

    private User loadUserByUsername(String userName) {
        userDB = objectMapper.buildUserMap();
        return userDB.get(userName);
    }
}
