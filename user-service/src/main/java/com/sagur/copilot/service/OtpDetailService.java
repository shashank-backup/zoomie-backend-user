package com.sagur.copilot.service;

import com.sagur.copilot.enums.OtpType;

public interface OtpDetailService {

  int generateOtp(String username, OtpType otpType);

  int generateOtp(int length);

  boolean verifyOtp(String username, Integer otp, OtpType otpType);
}
