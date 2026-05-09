package com.sagur.copilot.user.controller.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sagur.product.common.document.dto.DocumentContext;
import com.sagur.product.common.document.model.Document;
import com.sagur.product.common.utils.dto.UserProfileDto;
import com.sagur.product.common.web.response.ResponseUtil;
import com.sagur.product.common.web.response.dto.ResponseDTO;
import com.sagur.copilot.service.UserProfileService;
import com.sagur.copilot.user.controller.UserProfileController;
import com.sagur.product.common.web.audit.AuditActionType;
import com.sagur.product.common.web.audit.Auditable;

import lombok.extern.slf4j.Slf4j;

/**
 * UserProfileControllerImpl.
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/UserProfile")
@Slf4j
@Primary
public class UserProfileControllerImpl implements UserProfileController {

  private final UserProfileService service;

  private final ResponseUtil responseUtil;

  @Autowired
  public UserProfileControllerImpl(UserProfileService service, ResponseUtil responseUtil) {
    this.service = service;
    this.responseUtil = responseUtil;
  }

//  @Override
//  public ResponseDTO<List<UserProfile>> search(SearchWrapper searchWrapper) {
//    log.debug("@class UserProfileControllerImpl @method search @param searchWrapper {}", searchWrapper);
//    return responseUtil.success(service.search(searchWrapper));
//  }
//
//  @Override
//  public ResponseDTO<Long> searchCount(SearchWrapper searchWrapper) {
//    log.debug("@class UserProfileControllerImpl @method searchCount @param searchWrapper {}", searchWrapper);
//    return responseUtil.success(service.searchCount(searchWrapper));
//  }
//
//  @Override
//  public ResponseDTO<UserProfile> findById(long id) {
//    log.debug("@class UserProfileControllerImpl @method searchCount @param id {}", id);
//    return responseUtil.success(service.findById(id).orElseThrow(NoDataFoundException::new));
//  }
//
//  @Override
//  @Auditable(actionType = AuditActionType.CREATE, actionName = "UserProfile_CREATE")
//  public ResponseDTO<UserProfile> save(UserProfile userprofile) {
//    log.info("@class UserProfileControllerImpl @method create @param userprofile {}", userprofile);
//    return responseUtil.success(service.save(userprofile));
//  }

  @Auditable(actionType = AuditActionType.CREATE, actionName = "UserProfile_UPDATE")
  @Override
  public ResponseDTO<String> updateProfile(UserProfileDto userProfileDto){
    log.info("@class UserProfileControllerImpl @method updateProfile @param userProfileDto {}", userProfileDto);
    service.updateProfile(userProfileDto);
    return responseUtil.success("Profile Updated Successfully");
  }

  @Override
  public ResponseDTO<UserProfileDto> getProfile(String username){
    log.info("@class UserProfileControllerImpl @method getProfile @param username {}", username);
    return responseUtil.success(service.getProfile(username));
  }

  @Override
  public ResponseDTO<String> uploadProfileImage(MultipartFile file, String fileName, Document.DocumentType documentType, String documentSubType) throws
      IOException {
    log.info("@class UserProfileControllerImpl @method ProfileImage fileName:{}, documentType:{}, documentSubType:{}, contentType:{}, size:{}", fileName, documentType, documentSubType, file.getContentType(), file.getSize());
    DocumentContext documentContext = new DocumentContext();
    documentContext.setDocumentType(Document.DocumentType.PROFILE_PHOTO);
    documentContext.setDocumentSubType(Document.DocumentType.PROFILE_PHOTO.name());
    service.uploadProfileImage(file, fileName, documentContext);
    return responseUtil.success(null, "Profile Image Updated Successfully");
  }

  @Override
  public ResponseEntity<?> previewProfileImage() throws IOException {
    log.info("@class UserProfileControllerImpl @method previewProfileImage");
    return service.previewProfileImage();
  }

  @Override
  public ResponseDTO<Boolean> deleteProfileImage() throws IOException {
    log.info("@class UserProfileControllerImpl @method deleteProfileImage");
    return responseUtil.success(service.deleteProfileImage(), "Profile Image Deleted Successfully");
  }


}
