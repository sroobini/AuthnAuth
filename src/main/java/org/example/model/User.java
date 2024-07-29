package org.example.model;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class User extends Account {

  private String passwordHash;

  public User(String username, String email, String passwordHash) {
    this.username = username;
    this.email = email;
    this.passwordHash = passwordHash;
  }

}
