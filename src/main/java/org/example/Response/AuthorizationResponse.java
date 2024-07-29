package org.example.Response;

import lombok.NonNull;
import org.example.model.Role;

import java.util.List;
import java.util.Set;

public class AuthorizationResponse {

    public AuthorizationStatus getAuthorizationStatus() {
        return authorizationStatus;
    }

    public void setAuthorizationStatus(AuthorizationStatus authorizationStatus) {
        this.authorizationStatus = authorizationStatus;
    }

    private AuthorizationStatus authorizationStatus;
    private Long id;
    private String username;

    private String email;
    private Set<Role> roles;

    @NonNull
    private List<String> permissions;

    public AuthorizationResponse(Long id, String username, String email,
                                 Set<Role> roles, AuthorizationStatus authorizationStatus, List<String> permissions) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.authorizationStatus = authorizationStatus;
        this.permissions = permissions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    @NonNull
    public List<String> getPermissions() {
        return permissions;
    }
}
