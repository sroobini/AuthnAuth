package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class Account {

    public long id;

    public String username;

    public String email;

    private Set<Role> roles = new HashSet<>();

}
