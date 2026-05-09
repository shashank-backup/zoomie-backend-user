package com.sagur.copilot.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sagur.product.common.document.dto.DocumentContext;
import com.sagur.product.common.document.model.Document;
import com.sagur.product.common.utils.dto.UserProfileDto;
import com.sagur.copilot.model.UserProfile;
import com.sagur.product.generic.component.service.GenericService;

public interface UserProfileService extends GenericService<UserProfile, Long> {

  Optional<UserProfile> findByUsername(String username);

  UserProfileDto getProfile(String username);

  UserProfile updateProfile(UserProfileDto userProfileDto);

  @Transactional
  Document uploadProfileImage(MultipartFile file, String fileName, DocumentContext documentContext) throws IOException;

  @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
  ResponseEntity<Resource> previewProfileImage() throws IOException;

  boolean deleteProfileImage() throws IOException;

  boolean existsByReferralCode(String code);

  String generateUniqueReferralCode(String username, int length);
}
