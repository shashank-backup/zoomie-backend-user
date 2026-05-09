package com.sagur.copilot.user.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.sagur.product.common.utils.dto.DriverVerificationRequest;
import com.sagur.product.common.utils.dto.UserProfileDto;
import com.sagur.copilot.model.User;
import com.sagur.product.common.web.response.dto.ResponseDTO;
import com.sagur.product.common.web.config.util.constant.SwaggerConstant;

@FeignClient(name = "UserController", url = "${user-service-url}", path = "User", primary = false)
@Tag(name = "UserController", description = "APIs related to User")
@OpenAPIDefinition(tags = {@Tag(name = "UserController", description = "APIs related to User")})
public interface UserController {

//  /**
//   * search the entity data supported with fiql, pagination and sorting.
//   *
//   * @param searchWrapper @ResponseBody contains supporting parameters for fiql,
//   * pagination and sorting
//   *
//   * @return List of User as per SearchWrapper
//   */
//  @PostMapping(path = "search", consumes = MediaType.APPLICATION_JSON_VALUE)
//  @Operation(security = {@SecurityRequirement(name = SwaggerConstant.AUTHORIZATION_SCOPE_DEFAULT, scopes = {"API_User_feat_search"})}, tags = {
//      "UserController"})
//  ResponseDTO<List<User>> search(
//      @Parameter(description = SwaggerConstant.PARAM_DESCRIPTION_REQUESTBODY_SEARCH_WRAPPER, name = SwaggerConstant.PARAM_NAME_SEARCH_FILTER_WRAPPER) @RequestBody(required = false) SearchWrapper searchWrapper);
//
//  /**
//   * fetch the count of entity data supported with fiql.
//   *
//   * @param searchWrapper @ResponseBody contains supporting parameters for fiql,
//   * pagination and sorting
//   *
//   * @return count of User as per SearchWrapper
//   */
//  @PostMapping(path = "searchCount", consumes = MediaType.APPLICATION_JSON_VALUE)
//  @Operation(security = {@SecurityRequirement(name = SwaggerConstant.AUTHORIZATION_SCOPE_DEFAULT, scopes = {"API_User_feat_searchCount"})}, tags = {
//      "UserController"})
//  ResponseDTO<Long> searchCount(
//      @Parameter(description = SwaggerConstant.PARAM_DESCRIPTION_REQUESTBODY_SEARCH_WRAPPER, name = SwaggerConstant.PARAM_NAME_SEARCH_FILTER_WRAPPER) @RequestBody(required = false) SearchWrapper searchWrapper);
//
//  /**
//   * find the User by id.
//   *
//   * @param id primary identifier of the User
//   *
//   * @return User object as per the id
//   */
//  @GetMapping(path = "findById/{id}")
//  @Operation(security = {@SecurityRequirement(name = SwaggerConstant.AUTHORIZATION_SCOPE_DEFAULT, scopes = {"API_User_feat_findById"})}, tags = {
//      "UserController"})
//  ResponseDTO<User> findById(
//      @Parameter(description = "id of User", name = "id", required = true) @PathVariable(name = "id", required = true) long id);
//
//  /**
//   * Save a Record.
//   *
//   * @param User contains parameters which need to be stored
//   *
//   * @return saved User object
//   */
//  @PostMapping(path = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
//  @Operation(security = {@SecurityRequirement(name = SwaggerConstant.AUTHORIZATION_SCOPE_DEFAULT, scopes = {"API_User_feat_create"})}, tags = {
//      "UserController"})
//  ResponseDTO<User> save(@Parameter(description = "object of User", name = "User", required = true) @RequestBody(required = true) User User);
//
//  /**
//   * Delete a Record.
//   *
//   * @param id primary identifier of the User
//   *
//   * @return ResponseEntity with specificied status code
//   */
//  @PostMapping(path = "deleteById/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//  @Operation(security = {@SecurityRequirement(name = SwaggerConstant.AUTHORIZATION_SCOPE_DEFAULT, scopes = {"API_User_feat_deleteById"})}, tags = {
//      "UserController"})
//  ResponseDTO<Void> deleteById(
//      @Parameter(description = "id of User", name = "id", required = true) @PathVariable(name = "id", required = true) long id);

  @GetMapping(path = "findByUsername")
  @Operation(security = {
      @SecurityRequirement(name = SwaggerConstant.AUTHORIZATION_SCOPE_DEFAULT, scopes = {"API_User_feat_findByUsername"})}, tags = {
      "UserController"})
  ResponseDTO<User> findByUsername(
      @Parameter(description = "username of user", name = "username", required = true) @RequestParam(name = "username", required = true) String username);

}
