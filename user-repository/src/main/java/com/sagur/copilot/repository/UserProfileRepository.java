package com.sagur.copilot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sagur.copilot.model.UserProfile;
import com.sagur.product.generic.component.repository.GenericJpaRepository;

@Repository
public interface UserProfileRepository extends GenericJpaRepository<UserProfile, Long> {

//  @Query("select up from UserProfile up where up.user.username =:username")
  Optional<UserProfile> findByUserUsername(String username);

  boolean existsByReferralCode(String referralCode);
}
