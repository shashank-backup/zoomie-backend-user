package com.sagur.product.common.utils.dto;

import com.sagur.product.common.utils.exceptions.BusinessException;
import com.sagur.copilot.model.User;
import com.sagur.product.common.utils.utility.StringUtil;

import lombok.Data;

@Data
public class DriverVerificationRequest {
  private String fullName;
  private String driverUsername;
  private String contactNumber;
  private String email;
  private String addressLine1;
  private String addressLine2;
  private String city;
  private String state;
  private String country;
  private String pinCode;

  public boolean valid() {
    if(StringUtil.isNotEmpty(driverUsername) && StringUtil.isNotEmpty(contactNumber) && StringUtil.isNotEmpty(email) && StringUtil.isNotEmpty(
        fullName)) return true;
    else throw new BusinessException("Invalid Driver Verification Request");
  }
}
