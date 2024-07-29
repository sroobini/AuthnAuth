package org.example.Response;

import org.example.model.Role;
import java.util.Set;

public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private String username;
  private String email;
  private Set<Role> roles;

  public JwtResponse(String accessToken, String username, String email, Set<Role> roles) {
    this.token = accessToken;
    this.username = username;
    this.email = email;
    this.roles = roles;
  }

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Set<Role> getRoles() {
    return roles;
  }
}
