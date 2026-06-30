package com.sagur.drivemate.user.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sagur.drivemate.user.model.User;
import com.sagur.drivemate.user.repository.UserRepository;
import com.sagur.drivemate.user.service.RoleService;
import com.sagur.drivemate.user.service.UserService;
import com.sagur.drivemate.user.dto.UserAuthDto;
import com.sagur.product.common.utils.exceptions.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository repository;

  @Autowired
  private RoleService roleService;

  @Override
  public UserAuthDto findUserAuthByUsername(String username) {
    Optional<User> optionalUser = findByUsername(username);
    User user = optionalUser.orElseThrow(()->new UserNotFoundException(username + " does not exist"));
    UserAuthDto dto = new UserAuthDto();
    dto.setUsername(username);
    dto.setPassword(user.getPassword());
    dto.setRoles(user.getRoles().stream().map(role->role.getName().name()).toList());
    return dto;
  }

  @Override
  public Optional<User> findByUsername(String username) {
    return repository.findByUsername(username);
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return repository.findByEmail(email);
  }

  @Override
  public Optional<User> findByContactNumber(String contactNumber) {
    return repository.findByContactNumber(contactNumber);
  }

//  @Override
//  public List<Role> getAvailbaleRoles() {
//    return roleService.findAll();
//  }

  @Override
  public User createUser(User user) {
    return repository.save(user);
  }

  @Override
  public User updatePassword(User user) {
    return repository.save(user);
  }

}
