package com.sagur.copilot.user.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.sagur.copilot.enums.OtpType;
import com.sagur.copilot.model.User;
import com.sagur.copilot.response.dto.ResponseDTO;
import com.sagur.copilot.web.config.util.constant.SwaggerConstant;

@FeignClient(name = "OtpDetailController", url = "${user-service-url}", path = "OtpDetail", primary = false)
@Tag(name = "OtpDetailController", description = "APIs related to User")
@OpenAPIDefinition(tags = {@Tag(name = "OtpDetailController", description = "APIs related to OtpDetail")})
public interface OtpDetailController {

  @PostMapping(path = "generateOtp")
  @Operation(security = {
      @SecurityRequirement(name = SwaggerConstant.AUTHORIZATION_SCOPE_DEFAULT, scopes = {"API_OtpDetail_feat_generateOtp"})}, tags = {
      "OtpDetailController"})
  ResponseDTO<Integer> generateOtp(
      @Parameter(description = "length of otp", name = "otpLength", required = true) @RequestParam(name = "otpLength", required = true) Integer otpLength);

}
