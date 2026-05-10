package com.sagur.copilot.repository;

import org.springframework.stereotype.Repository;

import com.sagur.drivemate.user.model.UserSetting;
import com.sagur.product.generic.component.repository.GenericJpaRepository;

@Repository
public interface UserSettingRepository extends GenericJpaRepository<UserSetting, Long> {

}
