package com.sagur.copilot.service;

import java.util.List;
import java.util.Optional;

import com.sagur.product.common.utils.dto.DriverVerificationRequest;
import com.sagur.product.common.utils.dto.UserAuthDto;
import com.sagur.product.common.utils.dto.UserProfileDto;
import com.sagur.copilot.model.Role;
import com.sagur.copilot.model.User;

public interface UserService {

  UserAuthDto findUserAuthByUsername(String username);

  Optional<User> findByUsername(String username);

  Optional<User> findByEmail(String email);

//  List<Role> getAvailbaleRoles();

  Optional<User> findByContactNumber(String contactNumber);

  User createUser(User user);

}
