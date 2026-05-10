package com.sagur.drivemate.user.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sagur.drivemate.user.dto.DriverProfileDto;
import com.sagur.drivemate.user.dto.DriverVerificationRequest;
import com.sagur.product.common.utils.exceptions.BusinessException;
import com.sagur.drivemate.user.model.User;
import com.sagur.drivemate.user.service.UserProfileService;
import com.sagur.drivemate.user.service.UserService;
import com.sagur.drivemate.user.model.Address;
import com.sagur.drivemate.user.repository.AddressRepository;
import com.sagur.drivemate.user.service.AddressService;
import com.sagur.product.generic.component.service.impl.AbstractService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AddressServiceImpl extends AbstractService<Address, Long> implements AddressService {

  /** The AddressRepository repository. */
  private AddressRepository repository;

  @Autowired
  private UserProfileService userProfileService;

  @Autowired
  private UserService userService;

  /**
   * Sets the repository.
   *
   * @param repository the new repository
   */

  @Autowired
  public void setRepository(AddressRepository repository) {
    super.setRepository(repository);
    this.repository = repository;
  }

  @Override
  public Optional<Address> findByUserId(Long userId) {
    log.debug("@class AddressServiceImpl @method findByUserId userId:{}", userId);
    return repository.findByUserId(userId);
  }

  @Override
  public DriverProfileDto findDriverProfileByUsername(String username) {
    log.debug("@class AddressServiceImpl @method findDriverProfileByUsername username:{}", username);
    Optional<Address> address = repository.findByUserUsername(username);
    // or throw an exception if preferred
    DriverProfileDto dto =address.map(DriverProfileDto::new).orElseGet(DriverProfileDto::new);
    userProfileService.findByUsername(username).ifPresent(userProfile ->{dto.setReferralCode(userProfile.getReferralCode());});
    return dto;
  }

  @Override
  public Address activateDriverUser(DriverVerificationRequest request) {
    log.debug("class AddressServiceImpl @method activateDriverUser @param request {}", request);
    Address address = repository.findByUserUsername(request.getDriverUsername()).orElse(new Address());
    User user = null;
    if(address.getId() == null) {
      user = userService.findByUsername(request.getDriverUsername()).orElseThrow(()->new BusinessException("User not found"));
    } else {
      user = address.getUser();
    }
    if(!request.getContactNumber().equals(user.getContactNumber())) throw new BusinessException("Contact number mismatch");
    user.setFullName(request.getFullName());
    user.setEmail(request.getEmail());
    user.setStatus(User.UserStatus.ACTIVE);
    user = userService.createUser(user);
    address.setUser(user);
    address.setAddressLine1(request.getAddressLine1());
    address.setAddressLine2(request.getAddressLine2());
    address.setCountry(request.getCountry());
    address.setCity(request.getCity());
    address.setState(request.getState());
    address.setPinCode(request.getPinCode());
    return repository.save(address);
  }

}
