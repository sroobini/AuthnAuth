package org.example.common;

import org.example.dao.ClientCredentialsDAO;
import org.example.dao.PolicyDAO;
import org.example.dao.UserDAO;
import org.example.model.AuthPrincipal;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ObjectMapper {

    @Autowired
    public UserDAO userDAO;

    @Autowired
    public ClientCredentialsDAO clientCredentialsDAO;

    @Autowired
    public PolicyDAO policyDAO;

    public AuthPrincipal mapToAuthPrincipal(User user) {
        if (user == null) {
            return null;
        }
        AuthPrincipal authPrincipal = new AuthPrincipal();
        authPrincipal.setId(user.getId());
        authPrincipal.setEmail(user.getEmail());
        authPrincipal.setUsername(user.getUsername());
        authPrincipal.setRoles(user.getRoles());
        return  authPrincipal;
    }

    public Map<String, User> buildUserMap() {
        Map<String, User> userMap  = userDAO.loadUsers();
        //users.forEach(user-> userMap.put(user.getUsername(), user));
        return userMap;
    }

    public Map<String, String> buildClientCredentialsMap() {
        Map<String, String> clientMap  = new HashMap<>();
        clientMap = clientCredentialsDAO.loadClientCredentials();
        return clientMap;
    }

    public Map<String, List<String>> buildResourcePolicyMap() {
        Map<String, List<String>> resourcePolicyMap;
        resourcePolicyMap = policyDAO.loadResourcePolicies();
        return resourcePolicyMap;
    }

    public Map<String, List<String>> buildRolePolicyMap() {
        Map<String, List<String>> rolePolicyMap;
        rolePolicyMap = policyDAO.loadRolePolicies();
        return rolePolicyMap;
    }
}
