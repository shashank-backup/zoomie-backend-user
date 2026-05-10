package com.sagur.drivemate.user.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sagur.drivemate.user.model.Role;
import com.sagur.drivemate.user.repository.RoleRepository;
import com.sagur.drivemate.user.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

  private final RoleRepository repository;

  private final List<Role> roles = new ArrayList<>();

  public RoleServiceImpl(RoleRepository repository) {
    this.repository = repository;
    roles.addAll((Collection<? extends Role>)repository.findAll());
  }

  @Override
  public List<Role> findAll() {
    if(roles.isEmpty()) {
      roles.addAll((List<Role>)repository.findAll());
    }
    return roles;
  }

}
