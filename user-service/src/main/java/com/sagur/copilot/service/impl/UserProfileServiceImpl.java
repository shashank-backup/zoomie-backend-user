package com.sagur.copilot.service.impl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sagur.product.common.utils.constants.CommonConstant;
import com.sagur.product.common.document.dto.DocumentContext;
import com.sagur.product.common.document.model.Document;
import com.sagur.product.common.document.service.DocumentService;
import com.sagur.product.common.utils.dto.UserProfileDto;
import com.sagur.product.common.utils.exceptions.BusinessException;
import com.sagur.copilot.model.User;
import com.sagur.copilot.model.UserProfile;
import com.sagur.copilot.repository.UserProfileRepository;
import com.sagur.copilot.service.UserProfileService;
import com.sagur.copilot.service.UserService;
import com.sagur.product.common.web.config.context.UserContextProvider;
import com.sagur.product.generic.component.service.impl.AbstractService;
import com.sagur.product.common.web.file.ReferralCodeGenerator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserProfileServiceImpl extends AbstractService<UserProfile, Long> implements UserProfileService {

  /** The UserProfileRepository repository. */
  private UserProfileRepository repository;

  @Autowired
  private UserService userService;

  @Autowired
  private UserContextProvider userContextProvider;

  @Autowired
  private DocumentService documentService;

  @Autowired
  private ReferralCodeGenerator referralCodeGenerator;

  /**
   * Sets the repository.
   *
   * @param repository the new repository
   */

  @Autowired
  public void setRepository(UserProfileRepository repository) {
    super.setRepository(repository);
    this.repository = repository;
  }

  @Override
  public Optional<UserProfile> findByUsername(String username) {
    log.debug("@class UserProfileServiceImpl @method findByUsername username:{}", username);
    return repository.findByUserUsername(username);
  }

  @Override
  public UserProfileDto getProfile(String username) {
    Optional<UserProfile> userProfile = repository.findByUserUsername(username);
    if(userProfile.isEmpty()) throw new BusinessException("No Profile Exist with given username");
    return new UserProfileDto(userProfile.get());
  }

  @Override
  public UserProfile updateProfile(UserProfileDto userProfileDto) {
    Optional<UserProfile> optionalUserProfile = repository.findByUserUsername(
        userProfileDto.getUsername());//TO DO : need to get username by context due to security reason
    if(optionalUserProfile.isEmpty()) {
      throw new BusinessException("Invalid User");
    }
    //TO DO : Role should also be captured from context and validate
    UserProfile userProfile = optionalUserProfile.get();
    User user = userProfile.getUser();
    user.setEmail(userProfileDto.getEmail());
    user.setFullName(userProfileDto.getFullName());
    user.setContactNumber(userProfileDto.getContactNumber());
    user.setGender(userProfileDto.getGender());
//    user.setStreet(userProfileDto.getDistrict());
//    user.setCity(userProfileDto.getCity());
//    user.setDistrict(userProfileDto.getDistrict());
    user = userService.createUser(user);

    userProfile.setDob(userProfileDto.getDob());
    userProfile.setEmergencyContact(userProfileDto.getEmergencyContact());

    if(userProfile.getReferralCode() == null) {
      userProfile.setReferralCode(generateUniqueReferralCode(user.getUsername(), CommonConstant.REFERRAL_CODE_LENGTH));
    }

    userProfile.setUser(user);

    return repository.save(userProfile);
  }

  @Override
  public Document uploadProfileImage(MultipartFile file, String fileName, DocumentContext documentContext) throws IOException {
    String username = userContextProvider.getUsername();
    documentContext.setReferenceType(Document.DocumentReferenceType.USER);
    documentContext.setReferenceValue(username);
    return documentService.upload(file, fileName, documentContext);
  }

  @Override
  public ResponseEntity<Resource> previewProfileImage() throws IOException {
    String username = userContextProvider.getUsername();
    DocumentContext documentContext = new DocumentContext();
    documentContext.setReferenceType(Document.DocumentReferenceType.USER);
    documentContext.setReferenceValue(username);
    documentContext.setDocumentType(Document.DocumentType.PROFILE_PHOTO);
    documentContext.setDocumentSubType(Document.DocumentType.PROFILE_PHOTO.name());
    return documentService.preview(documentContext);
  }

  @Override
  public boolean deleteProfileImage() throws IOException {
    String username = userContextProvider.getUsername();
    DocumentContext documentContext = new DocumentContext();
    documentContext.setReferenceType(Document.DocumentReferenceType.USER);
    documentContext.setReferenceValue(username);
    documentContext.setDocumentType(Document.DocumentType.PROFILE_PHOTO);
    documentContext.setDocumentSubType(Document.DocumentType.PROFILE_PHOTO.name());
    return documentService.delete(documentContext);
  }

  @Override
  public boolean existsByReferralCode(String code) {
    return repository.existsByReferralCode(code);
  }

  @Override
  public String generateUniqueReferralCode(String username, int length) {
    String code;
    boolean exists;
    do {
      // Use the generator logic we discussed earlier
      code = referralCodeGenerator.generateReferralCode(username, length);

      // Check the database for collisions
      exists = repository.existsByReferralCode(code);

    } while (exists); // If collision occurs, loop and try again

    return code;
  }


}
