package com.sagur.drivemate.user.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sagur.drivemate.user.model.Address;
import com.sagur.product.generic.component.repository.GenericJpaRepository;

@Repository
public interface AddressRepository extends GenericJpaRepository<Address, Long> {

  Optional<Address> findByUserId(Long userId);

  /**
   * FROM User u LEFT JOIN UserProfile up ON up.user = u LEFT JOIN Address a ON a.user = u WHERE u.id = :id
   */
  @Query("SELECT a FROM Address a JOIN a.user u WHERE u.username = :username")
  Optional<Address> findByUserUsername(String username);

}
