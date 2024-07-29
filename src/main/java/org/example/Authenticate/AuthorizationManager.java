package org.example.Authenticate;

import org.example.JWT.JwtUtils;
import org.example.Response.AuthorizationStatus;
import org.example.common.ObjectMapper;
import org.example.model.AuthPrincipal;
import org.example.model.Role;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AuthorizationManager {

    private Map<String, User> userDB = new HashMap<>();
    private Map<String, List<String>> resourcePolicyDB = new HashMap<>();
    private Map<String, List<String>> rolePolicyDB = new HashMap<>();



    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JwtUtils jwtUtils;

    protected boolean validateToken(String token) {

        if (token != null) {
            return jwtUtils.validateJwtToken(token);
        }
        return false;
    }


    protected AuthPrincipal getAuthPrincipal(String userName) throws Exception {
        if(userName.isEmpty()) {
            throw new Exception("invalid_user");
        }
        userDB = objectMapper.buildUserMap();
         User user = userDB.get(userName);
        return objectMapper.mapToAuthPrincipal((user));
    }

    protected AuthorizationStatus checkPermission(AuthPrincipal principal, AuthorizationRequest request) {
        List<String> resourceRoles;
        String resource = request.getResource();
        AuthorizationStatus authorizationStatus = AuthorizationStatus.DENY;
        // if resource is present which we expect in every authorization call
        // load resource to role mapping and evaluate decision.
        // else load the Role based policies and set those permissions in authorization response
        // for clients to evaluate and decide
        if (resource != null) {
            resourceRoles = loadResourcePermissions(resource);
            if(!resourceRoles.isEmpty()) {
                for (Role role : principal.getRoles()) {
                    if (resourceRoles.contains(role.getName().toString())) {
                        authorizationStatus = AuthorizationStatus.ALLOW;
                    }
                }
            } else {
                authorizationStatus = validateRolePermissions(principal);
            }
        } else {
            authorizationStatus = validateRolePermissions(principal);
        }
        return authorizationStatus;

    }

    private AuthorizationStatus validateRolePermissions(AuthPrincipal principal) {
        List<String> rolePermissions;
        rolePermissions = loadRolePermissions(principal);
        AuthorizationStatus authorizationStatus;
        if (!rolePermissions.isEmpty()) {
            principal.setPermissions(rolePermissions);
            authorizationStatus = AuthorizationStatus.RESOURCE_NOT_PRESENT;
        } else {
            authorizationStatus = AuthorizationStatus.POLICY_NOT_FOUND;
        }
        return authorizationStatus;
    }

    private List<String> loadResourcePermissions(String resource) {
        List<String> permissions = new ArrayList<>();
        if (!resource.isEmpty()) {
            // load permission using user resource - more fine grained authorization
            resourcePolicyDB = objectMapper.buildResourcePolicyMap();
            permissions =  resourcePolicyDB.get(resource);
        }
        return permissions;
    }

    private List<String> loadRolePermissions(AuthPrincipal principal) {
        List<String> permissions = new ArrayList<>();
            rolePolicyDB = objectMapper.buildRolePolicyMap();
            for(Role role: principal.getRoles()) {
                permissions.addAll(rolePolicyDB.get(role.getName().toString()));
            }
        return permissions;
    }
}
