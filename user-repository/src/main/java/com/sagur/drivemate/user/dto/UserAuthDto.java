package com.sagur.drivemate.user.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserAuthDto {
  private String username;
  private String password;
  private List<String> roles;
}
