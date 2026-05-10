package com.sagur.drivemate.user.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sagur.drivemate.user.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

  Optional<User> findByUsername(String username);

  Optional<User> findByEmail(String username);

  Optional<User> findByContactNumber(String contactNumber);
}
