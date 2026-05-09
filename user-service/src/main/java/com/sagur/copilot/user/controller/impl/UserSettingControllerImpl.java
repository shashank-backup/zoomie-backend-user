package com.sagur.copilot.user.controller.impl;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sagur.copilot.model.UserSetting;
import com.sagur.product.common.web.response.ResponseUtil;
import com.sagur.product.common.web.response.dto.ResponseDTO;
import com.sagur.copilot.service.UserSettingService;
import com.sagur.copilot.user.controller.UserSettingController;
import com.sagur.product.common.web.audit.AuditActionType;
import com.sagur.product.common.web.audit.Auditable;
import com.sagur.product.common.web.config.context.UserContext;
import com.sagur.product.common.web.config.context.UserContextProvider;

import lombok.extern.slf4j.Slf4j;

/**
 * UserSettingControllerImpl.
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/UserSetting")
@Slf4j
@Primary
public class UserSettingControllerImpl implements UserSettingController {

  private final UserSettingService service;

  private final ResponseUtil responseUtil;

  @Autowired
  private UserContextProvider userContextProvider;

  @Autowired
  public UserSettingControllerImpl(UserSettingService service, ResponseUtil responseUtil) {
    this.service = service;
    this.responseUtil = responseUtil;
  }

  @Override
  @Auditable(actionType = AuditActionType.CREATE, actionName = "UserSetting_CREATE")
  public ResponseDTO<UserSetting> save(UserSetting usersetting) {
    log.info("@class UserSettingControllerImpl @method create @param usersetting {}", usersetting);
    return responseUtil.success(service.save(usersetting));
  }

  @Override
  public ResponseDTO<UserContext> getUserContext(){
    UserContext context = userContextProvider.getUserContext();
    log.info("@class UserSettingControllerImpl @method create @param context {}", context);
    return responseUtil.success(context);
  }

  @Override
  public ResponseDTO<Map<String, String>> getHeadersInfo(HttpServletRequest request) {
    Map<String, String> map = new HashMap<>();
    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String key = headerNames.nextElement();
      String value = request.getHeader(key);
      map.put(key, value);
      log.info("@class AuthenticationFilter @method doFilterInternal request headers:{}", map);
    }
    return responseUtil.success(map);
  }



}
