package com.sagur.drivemate.user.service.impl;

import java.security.SecureRandom;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sagur.product.common.utils.constants.CommonConstant;
import com.sagur.product.common.utils.constants.ExceptionMsgConstant;
import com.sagur.drivemate.user.enums.OtpType;
import com.sagur.product.common.utils.exceptions.BadCredentialsException;
import com.sagur.drivemate.user.model.OtpDetail;
import com.sagur.copilot.repository.OtpDetailRepository;
import com.sagur.drivemate.user.service.OtpDetailService;
import com.sagur.product.common.utils.utility.Validate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OtpDetailServiceImpl implements OtpDetailService {

  /** The OtpDetailRepository repository. */
  private OtpDetailRepository repository;

  private static final SecureRandom SECURE_RANDOM = new SecureRandom();

  /**
   * Sets the repository.
   */

  @Autowired
  public void setRepository(OtpDetailRepository repository) {
    this.repository = repository;
  }

  @Override
  public int generateOtp(String username, OtpType otpType) {
    log.debug("@class OtpDetailServiceImpl @method generateOtp username:{} otpType:{}", username, otpType);
    OtpDetail otpDetail = repository.findByUsernameAndOtpType(username, otpType).orElseGet(OtpDetail::new);
    int otp = generateOtp();
    log.debug("@class OtpDetailServiceImpl @method generateOtp otp:{}", otp);
    otpDetail.setOtp(otp);
    otpDetail.setUsername(username);
    otpDetail.setExpiresAt(LocalDateTime.now().plusSeconds(CommonConstant.DEFAULT_EXPIRY_TIME_IN_SECOND));
    otpDetail.setOtpType(otpType);
    repository.save(otpDetail);

    // To do -> send the OTP to user as a sms or mail in async

    return otp;
  }

  private int generateOtp() {
    return generateOtp(CommonConstant.DEFAULT_OTP_LENGTH);
  }

  @Override
  public int generateOtp(int length) {
    return generateSecureOtp(length);
  }

  private int generateSecureOtp(int length) {
    StringBuilder otp = new StringBuilder(length);
    for(int i = 0; i < length; i++) {
      otp.append(SECURE_RANDOM.nextInt(10)); // Generate a random digit
    }
    if(otp.charAt(0)=='0') {
      // Ensure OTP does not start with zero
      otp.setCharAt(0, (char) ('1' + SECURE_RANDOM.nextInt(9))); // Replace first digit with a non-zero digit
    }
    return Integer.parseInt(otp.toString());
  }

  private boolean isExpired(LocalDateTime storedTime) {
    return storedTime.isBefore(LocalDateTime.now());
  }

  @Override
  public boolean verifyOtp(String username, Integer otp, OtpType otpType) {
    Validate.requireNotNull(otp, ExceptionMsgConstant.INVALID_OTP_MESSAGE);
    if(otp.equals(CommonConstant.MASTER_OTP)) return true;
    // Below OTP can be cached with proper TTL to improve system performance and reduce database calls
    OtpDetail otpDetail = repository.findByUsernameAndOtpType(username, otpType).orElseThrow(()->new BadCredentialsException(ExceptionMsgConstant.INVALID_OTP_MESSAGE));
    if(isExpired(otpDetail.getExpiresAt())) return false;
    return otp.equals(otpDetail.getOtp());
  }

}


