package com.sagur.drivemate.user.controller.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sagur.drivemate.user.dto.DriverProfileDto;
import com.sagur.drivemate.user.dto.DriverVerificationRequest;
import com.sagur.product.common.document.dto.DocumentContext;
import com.sagur.product.common.document.model.Document;
import com.sagur.product.common.web.audit.AuditActionType;
import com.sagur.product.common.web.audit.Auditable;
import com.sagur.product.common.web.response.ResponseUtil;
import com.sagur.product.common.web.response.dto.ResponseDTO;
import com.sagur.drivemate.user.controller.AddressController;
import com.sagur.drivemate.user.model.Address;
import com.sagur.drivemate.user.service.AddressService;

import lombok.extern.slf4j.Slf4j;

/**
 * AddressControllerImpl.
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/Address")
@Slf4j
@Primary
public class AddressControllerImpl implements AddressController {

  private final AddressService service;

  private final ResponseUtil responseUtil;

  @Autowired
  public AddressControllerImpl(AddressService service, ResponseUtil responseUtil) {
    this.service = service;
    this.responseUtil = responseUtil;
  }

//  @Override
//  public ResponseDTO<List<Address>> search(SearchWrapper searchWrapper) {
//    log.debug("@class AddressControllerImpl @method search @param searchWrapper {}", searchWrapper);
//    return responseUtil.success(service.search(searchWrapper));
//  }
//
//  @Override
//  public ResponseDTO<Long> searchCount(SearchWrapper searchWrapper) {
//    log.debug("@class AddressControllerImpl @method searchCount @param searchWrapper {}", searchWrapper);
//    return responseUtil.success(service.searchCount(searchWrapper));
//  }

  @Override
  @Auditable(actionType = AuditActionType.CREATE, actionName = "Address_activateDriverUser")
  public ResponseDTO<Address> activateDriverUser(DriverVerificationRequest request) {
    log.info("@class AddressControllerImpl @method activateDriverUser @param address {}", request);
    return responseUtil.success(service.activateDriverUser(request));
  }

  @Override
  public ResponseDTO<DriverProfileDto> findDriverProfileByUsername(String username) {
    log.info("@class AddressControllerImpl @method findDriverProfileByUsername @param address {}", username);
    return responseUtil.success(service.findDriverProfileByUsername(username));
  }



}
