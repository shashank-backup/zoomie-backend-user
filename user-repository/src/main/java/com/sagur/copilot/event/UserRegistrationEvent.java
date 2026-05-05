package com.sagur.copilot.event;

import java.util.Set;

import com.sagur.copilot.enums.Gender;
import com.sagur.copilot.model.Role;
import com.sagur.copilot.model.User;

import lombok.Data;

@Data
public class UserRegistrationEvent {

  private String username;

  private String password;

  private String email;

  private String fullName;

  private String contactNumber;

  private Gender gender;

  private String pinCode;

  private String countryCode;

  public enum UserStatus {
    ACTIVE,
    INACTIVE,
    BLOCKED,
    REGISTERED
  }

  private User.UserStatus status = User.UserStatus.ACTIVE;

  private Set<Role> roles;

  private User.UserLoginType userLoginType;

  private String city;

  private String state;

  private String referralCode;

}
