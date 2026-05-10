package com.sagur.drivemate.user.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sagur.product.common.utils.exceptions.UserNotFoundException;
import com.sagur.drivemate.user.model.User;
import com.sagur.product.common.web.response.ResponseUtil;
import com.sagur.product.common.web.response.dto.ResponseDTO;
import com.sagur.drivemate.user.service.UserService;
import com.sagur.drivemate.user.controller.UserController;

import lombok.extern.slf4j.Slf4j;

/**
 * UsersControllerImpl.
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/User")
@Slf4j
@Primary
public class UserControllerImpl implements UserController {

  private final UserService service;

  private final ResponseUtil responseUtil;

  @Autowired
  public UserControllerImpl(UserService service, ResponseUtil responseUtil) {
    this.service = service;
    this.responseUtil = responseUtil;
  }

//  @Override
//  public ResponseDTO<List<User>> search(SearchWrapper searchWrapper) {
//    log.debug("@class UsersControllerImpl @method search @param searchWrapper {}", searchWrapper);
//    return responseUtil.success(service.search(searchWrapper));
//  }
//
//  @Override
//  public ResponseDTO<Long> searchCount(SearchWrapper searchWrapper) {
//    log.debug("@class UsersControllerImpl @method searchCount @param searchWrapper {}", searchWrapper);
//    return responseUtil.success(service.searchCount(searchWrapper));
//  }
//
//  @Override
//  public ResponseDTO<User> findById(long id) {
//    log.debug("@class UsersControllerImpl @method searchCount @param id {}", id);
//    return responseUtil.success(service.findById(id).orElseThrow(NoDataFoundException::new));
//  }
//
//  @Override
//  @Auditable(actionType = AuditActionType.CREATE, actionName = "Users_CREATE")
//  public ResponseDTO<User> save(User user) {
//    log.info("@class UsersControllerImpl @method create @param users {}", user);
//    return responseUtil.success(service.save(user));
//  }
//

  @Override
  public ResponseDTO<User> findByUsername(String username) {
    log.info("@class UserControllerImpl @method findByUsername @param username {}", username);
    return responseUtil.successNew(service.findByUsername(username).orElseThrow(UserNotFoundException::new));
  }

}
