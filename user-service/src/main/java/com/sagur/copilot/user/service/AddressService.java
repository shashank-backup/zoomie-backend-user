package com.sagur.copilot.user.service;

import java.util.Optional;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sagur.copilot.dto.DriverProfileDto;
import com.sagur.copilot.dto.DriverVerificationRequest;
import com.sagur.copilot.user.model.Address;
import com.sagur.product.generic.component.service.GenericService;

public interface AddressService extends GenericService<Address, Long> {

  Optional<Address> findByUserId(Long userId);

  @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
  DriverProfileDto findDriverProfileByUsername(String username);

  Address activateDriverUser(DriverVerificationRequest request);
}
