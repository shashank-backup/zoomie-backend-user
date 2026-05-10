package com.sagur.drivemate.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sagur.drivemate.user.model.UserSetting;
import com.sagur.copilot.repository.UserSettingRepository;
import com.sagur.drivemate.user.service.UserSettingService;
import com.sagur.product.generic.component.service.impl.AbstractService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserSettingServiceImpl extends AbstractService<UserSetting, Long> implements UserSettingService {

  /** The UserSettingRepository repository. */
  private UserSettingRepository repository;

  /**
   * Sets the repository.
   *
   * @param repository the new repository
   */

  @Autowired
  public void setRepository(UserSettingRepository repository) {
    super.setRepository(repository);
    this.repository = repository;
  }

}
