package com.sagur.drivemate.user.dto;

import com.sagur.drivemate.user.model.User;
import com.sagur.drivemate.user.model.Address;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DriverProfileDto {

  private String fullName;
  private String email;
  private String username;
  private String contactNumber;
  private String addressLine1;
  private String addressLine2;
  private String city;
  private String state;
  private String country;
  private String pinCode;
  private User.UserStatus driverStatus = User.UserStatus.REGISTERED;
  private String referralCode;

  public DriverProfileDto(Address address) {
    User user = address.getUser();
    if(user != null) {
      this.fullName = user.getFullName();
      this.email = user.getEmail();
      this.username = user.getUsername();
      this.contactNumber = user.getContactNumber();
      this.driverStatus = user.getStatus();
    }
    this.addressLine1 = address.getAddressLine1();
    this.addressLine2 = address.getAddressLine2();
    this.city = address.getCity();
    this.state = address.getState();
    this.country = address.getCountry();
    this.pinCode = address.getPinCode();
  }
}
