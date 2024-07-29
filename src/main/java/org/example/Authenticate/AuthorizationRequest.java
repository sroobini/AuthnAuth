package org.example.Authenticate;

import jakarta.validation.constraints.NotBlank;

public class AuthorizationRequest {
    @NotBlank
    private String resource;

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
