package com.sagur.copilot.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sagur.copilot.enums.OtpType;
import com.sagur.copilot.model.OtpDetail;

@Repository
public interface OtpDetailRepository extends CrudRepository<OtpDetail, Long> {

  Optional<OtpDetail> findByUsernameAndOtpType(String username, OtpType otpType);

}
