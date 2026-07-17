package com.sagur.drivemate.user.service;

import java.util.Optional;

import com.sagur.drivemate.user.model.User;
import com.sagur.drivemate.user.dto.UserAuthDto;

public interface UserService {

  UserAuthDto findUserAuthByUsername(String username);

  Optional<User> findByUsername(String username);

  Optional<User> findByEmail(String email);

  Optional<User> findByContactNumber(String contactNumber);

  User createUser(User user);

  void updatePassword(User user);

  void blockUser(String username);

  void unblockUser(String username);
}
