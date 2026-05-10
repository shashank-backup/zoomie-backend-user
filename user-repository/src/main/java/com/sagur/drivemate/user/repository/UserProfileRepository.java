package com.sagur.copilot.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.sagur.drivemate.user.model.UserProfile;
import com.sagur.product.generic.component.repository.GenericJpaRepository;

@Repository
public interface UserProfileRepository extends GenericJpaRepository<UserProfile, Long> {

//  @Query("select up from UserProfile up where up.user.username =:username")
  Optional<UserProfile> findByUserUsername(String username);

  boolean existsByReferralCode(String referralCode);
}
