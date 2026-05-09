package com.sagur.copilot.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sagur.product.common.utils.dto.DriverVerificationRequest;
import com.sagur.product.common.utils.dto.UserAuthDto;
import com.sagur.product.common.utils.exceptions.BusinessException;
import com.sagur.product.common.utils.exceptions.UserNotFoundException;
import com.sagur.copilot.model.User;
import com.sagur.copilot.repository.UserRepository;
import com.sagur.copilot.service.RoleService;
import com.sagur.copilot.service.UserService;

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

}
