package com.sagur.drivemate.user.service;

import java.util.Optional;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sagur.drivemate.user.dto.DriverProfileDto;
import com.sagur.drivemate.user.dto.DriverVerificationRequest;
import com.sagur.drivemate.user.model.Address;
import com.sagur.product.generic.component.service.GenericService;

public interface AddressService extends GenericService<Address, Long> {

  Optional<Address> findByUserId(Long userId);

  @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
  DriverProfileDto findDriverAddressProfile();

  Address activateDriverUser(DriverVerificationRequest request);
}
