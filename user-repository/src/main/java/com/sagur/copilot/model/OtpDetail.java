package com.sagur.copilot.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.sagur.product.common.utils.constants.ColumnDefinitionConstant;
import com.sagur.product.common.utils.constants.Constants;
import com.sagur.product.common.utils.constants.Prefix;
import com.sagur.product.common.utils.enums.OtpType;
import com.sagur.product.generic.component.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = Constants.Tables.OTP_DETAIL, uniqueConstraints = @UniqueConstraint(columnNames = {"USERNAME", "OTP_TYPE"}))
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@EqualsAndHashCode(callSuper = false)
public class OtpDetail extends BaseEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  /** Primary key of OtpDetail. **/
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Prefix.Sequence.NAME + Prefix.Table.COMMON + "OTP_DETAIL")
  @SequenceGenerator(name = Prefix.Sequence.NAME + Prefix.Table.COMMON + "OTP_DETAIL", sequenceName = Prefix.Sequence.NAME + Prefix.Table.COMMON + "OTP_DETAIL", allocationSize = 1)

  @Column(name = "ID")
  private Long id;

  @Column(name = "USERNAME", columnDefinition = ColumnDefinitionConstant.STRING_USERNAME)
  private String username;

  @Column(name = "OTP")
  private Integer otp;

  @Enumerated(EnumType.STRING)
  @Column(name = "OTP_TYPE")
  private OtpType otpType;

  @Column(name = "EXPIRES_AT")
  private LocalDateTime expiresAt;

  @Column(name = "REQUEST_DEVICE_TYPE")
  private String requestDeviceType;

  @Column(name = "REQUEST_DEVICE_IP")
  private String requestDeviceIp;

}
