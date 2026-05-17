package com.sagur.drivemate.user.controller;

import java.io.IOException;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.sagur.drivemate.user.dto.DriverProfileDto;
import com.sagur.drivemate.user.dto.DriverVerificationRequest;
import com.sagur.product.common.web.response.dto.ResponseDTO;
import com.sagur.product.common.web.config.util.constant.SwaggerConstant;
import com.sagur.drivemate.user.model.Address;

@FeignClient(name = "AddressController", url = "${user-service-url}", path = "Address", primary = false)
@Tag(name = "AddressController", description = "APIs related to Address")
@OpenAPIDefinition(tags = {@Tag(name = "AddressController", description = "APIs related to Address")})
public interface AddressController {

//  /**
//   * search the entity data supported with fiql, pagination and sorting.
//   *
//   * @param searchWrapper @ResponseBody contains supporting parameters for fiql, pagination and sorting
//   * @return List of Address as per SearchWrapper
//   */
//  @PostMapping(path = "search", consumes = MediaType.APPLICATION_JSON_VALUE)
//  @Operation(security = {@SecurityRequirement(name = SwaggerConstant.AUTHORIZATION_SCOPE_DEFAULT, scopes = {"API_Address_feat_search"})}, tags = {
//      "AddressController"})
//  ResponseDTO<List<Address>> search(
//      @Parameter(description = SwaggerConstant.PARAM_DESCRIPTION_REQUESTBODY_SEARCH_WRAPPER, name = SwaggerConstant.PARAM_NAME_SEARCH_FILTER_WRAPPER) @RequestBody(required = false) SearchWrapper searchWrapper);
//
//  /**
//   * fetch the count of entity data supported with fiql.
//   *
//   * @param searchWrapper @ResponseBody contains supporting parameters for fiql, pagination and sorting
//   * @return count of Address as per SearchWrapper
//   */
//  @PostMapping(path = "searchCount", consumes = MediaType.APPLICATION_JSON_VALUE)
//  @Operation(security = {
//      @SecurityRequirement(name = SwaggerConstant.AUTHORIZATION_SCOPE_DEFAULT, scopes = {"API_Address_feat_searchCount"})}, tags = {
//      "AddressController"})
//  ResponseDTO<Long> searchCount(
//      @Parameter(description = SwaggerConstant.PARAM_DESCRIPTION_REQUESTBODY_SEARCH_WRAPPER, name = SwaggerConstant.PARAM_NAME_SEARCH_FILTER_WRAPPER) @RequestBody(required = false) SearchWrapper searchWrapper);
//
  /**
   * Save a Record.
   *
   * @param request contains parameters which need to be stored
   * @return saved address object
   */
  @PostMapping(path = "activateDriverUser", consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(security = {@SecurityRequirement(name = SwaggerConstant.AUTHORIZATION_SCOPE_DEFAULT, scopes = {"API_Address_feat_activateDriverUser"})}, tags = {
      "AddressController"})
  ResponseDTO<Address> activateDriverUser(
      @Parameter(description = "request dto", name = "request", required = true) @RequestBody DriverVerificationRequest request);


  @GetMapping(path = "findDriverAddressProfile")
  @Operation(security = {@SecurityRequirement(name = SwaggerConstant.AUTHORIZATION_SCOPE_DEFAULT, scopes = {"API_Address_feat_findDriverAddressProfile"})}, tags = {
      "AddressController"})
  ResponseDTO<DriverProfileDto> findDriverAddressProfile();


}
