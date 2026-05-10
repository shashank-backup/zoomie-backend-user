package com.sagur.drivemate.user.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sagur.product.common.web.response.ResponseUtil;
import com.sagur.product.common.web.response.dto.ResponseDTO;
import com.sagur.drivemate.user.service.OtpDetailService;
import com.sagur.drivemate.user.controller.OtpDetailController;

import lombok.extern.slf4j.Slf4j;

/**
 * UsersControllerImpl.
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/OtpDetail")
@Slf4j
@Primary
public class OtpDetailControllerImpl implements OtpDetailController {

  private final OtpDetailService service;

  private final ResponseUtil responseUtil;

  @Autowired
  public OtpDetailControllerImpl(OtpDetailService service, ResponseUtil responseUtil) {
    this.service = service;
    this.responseUtil = responseUtil;
  }

  @Override
  public ResponseDTO<Integer> generateOtp(Integer length) {
    return responseUtil.successNew(service.generateOtp(length));
  }

}
