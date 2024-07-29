package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AuthPrincipal extends  Account {

    public String grantType;

    public String token;

    public boolean isPasswordLocked;

    public List<String> permissions;

}
