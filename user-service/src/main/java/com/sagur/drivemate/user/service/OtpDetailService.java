package com.sagur.drivemate.user.service;

import com.sagur.drivemate.user.enums.OtpType;

public interface OtpDetailService {

  int generateOtp(String username, OtpType otpType);

  int generateOtp(int length);

  boolean verifyOtp(String username, Integer otp, OtpType otpType);
}
