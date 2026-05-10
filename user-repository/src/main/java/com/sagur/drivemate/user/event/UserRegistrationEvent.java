package com.sagur.drivemate.user.event;

import java.util.Set;

import com.sagur.product.common.utils.enums.Gender;
import com.sagur.drivemate.user.model.Role;
import com.sagur.drivemate.user.model.User;

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
