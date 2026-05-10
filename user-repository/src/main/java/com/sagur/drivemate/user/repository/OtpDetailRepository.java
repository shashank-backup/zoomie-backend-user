package com.sagur.drivemate.user.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sagur.drivemate.user.enums.OtpType;
import com.sagur.drivemate.user.model.OtpDetail;

@Repository
public interface OtpDetailRepository extends CrudRepository<OtpDetail, Long> {

  Optional<OtpDetail> findByUsernameAndOtpType(String username, OtpType otpType);

}
