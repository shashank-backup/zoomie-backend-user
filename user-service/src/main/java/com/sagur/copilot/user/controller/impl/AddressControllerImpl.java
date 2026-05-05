package com.sagur.copilot.user.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sagur.copilot.dto.DriverProfileDto;
import com.sagur.copilot.dto.DriverVerificationRequest;
import com.sagur.copilot.web.audit.AuditActionType;
import com.sagur.copilot.web.audit.Auditable;
import com.sagur.copilot.exception.NoDataFoundException;
import com.sagur.copilot.response.ResponseUtil;
import com.sagur.copilot.response.dto.ResponseDTO;
import com.sagur.product.generic.component.rsql.SearchWrapper;
import com.sagur.copilot.user.controller.AddressController;
import com.sagur.copilot.user.model.Address;
import com.sagur.copilot.user.service.AddressService;

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
