package com.sagur.copilot.repository;

import org.springframework.data.repository.CrudRepository;

import com.sagur.drivemate.user.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {}
