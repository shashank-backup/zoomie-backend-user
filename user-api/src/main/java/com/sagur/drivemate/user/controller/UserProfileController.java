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

import com.sagur.product.common.document.model.Document;
import com.sagur.drivemate.user.dto.UserProfileDto;
import com.sagur.product.common.web.response.dto.ResponseDTO;
import com.sagur.product.common.web.config.util.constant.SwaggerConstant;

@FeignClient(name = "UserProfileController", url = "${user-service-url}", path = "UserProfile", primary = false)
@Tag(name = "UserProfileController", description = "APIs related to UserProfile")
@OpenAPIDefinition(tags = {@Tag(name = "UserProfileController", description = "APIs related to UserProfile")})
public interface UserProfileController {

  @Deprecated
  /**
   * Save a Record.
   *
   * @param userProfileDto contains parameters which need to be stored
   * @return saved userprofile object
   */
  @PostMapping(path = "updateProfile", consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(security = {
      @SecurityRequirement(name = SwaggerConstant.AUTHORIZATION_SCOPE_DEFAULT, scopes = {"API_UserProfile_feat_updateProfile"})}, tags = {
      "UserProfileController"})
  ResponseDTO<String> updateProfile(
      @Parameter(description = "DTO of UserProfile", name = "userprofile", required = true) @RequestBody(required = true) UserProfileDto userProfileDto);

  @Deprecated
  /**
   * //   * find the userprofile by id.
   * //   *
   * //   * @param id primary identifier of the UserProfile
   * //   * @return UserProfile object as per the id
   * //
   */
  @GetMapping(path = "getProfile")
  @Operation(security = {
      @SecurityRequirement(name = SwaggerConstant.AUTHORIZATION_SCOPE_DEFAULT, scopes = {"API_UserProfile_feat_getProfile"})}, tags = {
      "UserProfileController"})
  ResponseDTO<UserProfileDto> getProfile(
      @Parameter(description = "username of user", name = "username", required = true) @RequestParam(name = "username", required = true) String username);

//  /**
//   * search the entity data supported with fiql, pagination and sorting.
//   *
//   * @param searchWrapper @ResponseBody contains supporting parameters for fiql, pagination and sorting
//   * @return List of UserProfile as per SearchWrapper
//   */
//  @PostMapping(path = "search", consumes = MediaType.APPLICATION_JSON_VALUE)
//  @Operation(security = {@SecurityRequirement(name = SwaggerConstant.AUTHORIZATION_SCOPE_DEFAULT, scopes = {"API_UserProfile_feat_search"})}, tags = {
//      "UserProfileController"})
//  ResponseDTO<List<UserProfile>> search(
//      @Parameter(description = SwaggerConstant.PARAM_DESCRIPTION_REQUESTBODY_SEARCH_WRAPPER, name = SwaggerConstant.PARAM_NAME_SEARCH_FILTER_WRAPPER) @RequestBody(required = false) SearchWrapper searchWrapper);
//
//  /**
//   * fetch the count of entity data supported with fiql.
//   *
//   * @param searchWrapper @ResponseBody contains supporting parameters for fiql, pagination and sorting
//   * @return count of UserProfile as per SearchWrapper
//   */
//  @PostMapping(path = "searchCount", consumes = MediaType.APPLICATION_JSON_VALUE)
//  @Operation(security = {
//      @SecurityRequirement(name = SwaggerConstant.AUTHORIZATION_SCOPE_DEFAULT, scopes = {"API_UserProfile_feat_searchCount"})}, tags = {
//      "UserProfileController"})
//  ResponseDTO<Long> searchCount(
//      @Parameter(description = SwaggerConstant.PARAM_DESCRIPTION_REQUESTBODY_SEARCH_WRAPPER, name = SwaggerConstant.PARAM_NAME_SEARCH_FILTER_WRAPPER) @RequestBody(required = false) SearchWrapper searchWrapper);
//
//  /**
//   * find the userprofile by id.
//   *
//   * @param id primary identifier of the UserProfile
//   * @return UserProfile object as per the id
//   */
//  @GetMapping(path = "findById/{id}")
//  @Operation(security = {@SecurityRequirement(name = SwaggerConstant.AUTHORIZATION_SCOPE_DEFAULT, scopes = {"API_UserProfile_feat_findById"})}, tags = {
//      "UserProfileController"})
//  ResponseDTO<UserProfile> findById(
//      @Parameter(description = "id of userprofile", name = "id", required = true) @PathVariable(name = "id", required = true) long id);
//
//  /**
//   * Save a Record.
//   *
//   * @param userprofile contains parameters which need to be stored
//   * @return saved userprofile object
//   */
//  @PostMapping(path = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
//  @Operation(security = {@SecurityRequirement(name = SwaggerConstant.AUTHORIZATION_SCOPE_DEFAULT, scopes = {"API_UserProfile_feat_create"})}, tags = {
//      "UserProfileController"})
//  ResponseDTO<UserProfile> save(
//      @Parameter(description = "object of UserProfile", name = "userprofile", required = true) @RequestBody(required = true) UserProfile userprofile);
//

  /**
   * Upload a Profile Image.
   *
   * @param file contains parameters which need to be stored
   * @return success message
   */
  @PostMapping(path = "uploadProfileImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @Operation(security = {@SecurityRequirement(name = SwaggerConstant.AUTHORIZATION_SCOPE_DEFAULT, scopes = {"API_Document_feat_uploadProfileImage"})}, tags = {
      "UserProfileController"})
  ResponseDTO<String> uploadProfileImage(
      @Parameter(description = "uploaded file", name = "file", required = true) @RequestParam("file") MultipartFile file,
      @Parameter(description = "fileName of the uploaded file", name = "fileName", required = false) @RequestParam(name = "fileName", required = false) String fileName,
      @Parameter(description = "type of document", name = "documentType", required = true) @RequestParam(name = "documentType", required = true) Document.DocumentType documentType,
      @Parameter(description = "sub type of document", name = "documentSubType", required = false) @RequestParam(name = "documentSubType", required = false) String documentSubType) throws
      IOException;

  /**
   * Preview a Profile Image.
   *
   * @return Stream of file
   */
  @GetMapping(path = "previewProfileImage")
  @Operation(security = {@SecurityRequirement(name = SwaggerConstant.AUTHORIZATION_SCOPE_DEFAULT, scopes = {"API_Document_feat_previewProfileImage"})}, tags = {
      "UserProfileController"})
  ResponseEntity<?> previewProfileImage() throws IOException;

  /**
   * Delete a Profile Image.
   *
   * @return success message
   */
  @GetMapping(path = "deleteProfileImage")
  @Operation(security = {@SecurityRequirement(name = SwaggerConstant.AUTHORIZATION_SCOPE_DEFAULT, scopes = {"API_Document_feat_deleteProfileImage"})}, tags = {
      "UserProfileController"})
  ResponseDTO<Boolean> deleteProfileImage() throws IOException;

}
