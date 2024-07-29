package org.example.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Role {

  public enum ERole {
    ROLE_USER_1,
    ROLE_USER_2,
    ROLE_USER_3,
    ROLE_ADMIN;
  }


  private Integer id;

  private ERole name;

  public Role(ERole name) {
    this.name = name;
  }

}