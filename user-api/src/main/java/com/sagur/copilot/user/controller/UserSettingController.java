package com.sagur.copilot.user.controller;

import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.sagur.copilot.model.UserSetting;
import com.sagur.copilot.response.dto.ResponseDTO;
import com.sagur.copilot.web.config.context.UserContext;
import com.sagur.copilot.web.config.util.constant.SwaggerConstant;

@FeignClient(name = "UserSettingController", url = "${user-service-url}", path = "UserSetting", primary = false)
@Tag(name = "UserSettingController", description = "APIs related to UserSetting")
@OpenAPIDefinition(tags = {@Tag(name = "UserSettingController", description = "APIs related to UserSetting")})
public interface UserSettingController {

  /**
   * Save a Record.
   *
   * @param usersetting contains parameters which need to be stored
   * @return saved usersetting object
   */
  @PostMapping(path = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(security = {@SecurityRequirement(name = SwaggerConstant.AUTHORIZATION_SCOPE_DEFAULT, scopes = {"API_UserSetting_feat_create"})}, tags = {
      "UserSettingController"})
  ResponseDTO<UserSetting> save(
      @Parameter(description = "object of UserSetting", name = "usersetting", required = true) @RequestBody(required = true) UserSetting usersetting);

  @GetMapping(path = "getUserContext")
  @Operation(security = {@SecurityRequirement(name = SwaggerConstant.AUTHORIZATION_SCOPE_DEFAULT, scopes = {"API_UserSetting_feat_getUserContext"})}, tags = {
      "UserSettingController"})
  ResponseDTO<UserContext> getUserContext();

  @GetMapping(path = "getHeadersInfo")
  @Operation(security = {@SecurityRequirement(name = SwaggerConstant.AUTHORIZATION_SCOPE_DEFAULT, scopes = {"API_UserSetting_feat_getHeadersInfo"})}, tags = {
      "UserSettingController"})
  ResponseDTO<Map<String, String>> getHeadersInfo(HttpServletRequest request);

}
