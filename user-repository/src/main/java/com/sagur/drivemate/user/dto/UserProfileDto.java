package com.sagur.drivemate.user.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;

import com.sagur.product.common.utils.enums.Gender;
import com.sagur.product.common.utils.enums.RoleName;
import com.sagur.drivemate.user.model.User;
//import com.sagur.drivemate.user.model.UserProfile;
import com.sagur.drivemate.user.util.UserUtil;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserProfileDto {

  @NotNull(message = "fullName is empty")
  private String fullName;

  @NotNull(message = "email is empty")
  private String email;

  @NotNull(message = "username is empty")
  private String username;

  @NotNull(message = "contactNumber is empty")
  private String contactNumber;

  @NotNull(message = "role is empty")
  private RoleName role;

  @NotNull(message = "gender is empty")
  private Gender gender;

  private LocalDate dob;

  private String emergencyContact;

  private String referralCode;


//  public UserProfileDto(UserProfile userProfile) {
//    User user = userProfile.getUser();
//    this.username = user.getUsername();
//    this.contactNumber= user.getContactNumber();
//    this.email= user.getEmail();
//    this.fullName = user.getFullName();
//    this.gender= user.getGender();
//    this.dob= userProfile.getDob();
//    this.emergencyContact= userProfile.getEmergencyContact();
//    this.referralCode= userProfile.getReferralCode();
//    this.role= UserUtil.getRoleNames(user.getRoles()).stream().findFirst().orElse(null);
//  }

//
//  private String street;
//
//  private String city;
//
//  private String district;

}
