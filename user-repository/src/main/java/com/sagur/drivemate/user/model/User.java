package com.sagur.drivemate.user.model;

import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.sagur.product.common.utils.constants.ColumnDefinitionConstant;
import com.sagur.product.common.utils.constants.Constants;
import com.sagur.product.common.utils.enums.Gender;
import com.sagur.product.common.generic.component.model.BaseEntity;

import lombok.Data;

@Audited
@Data
@Entity
@Table(name = Constants.Tables.USER)
public class User extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = Constants.Sequences.USER)
  @SequenceGenerator(name = Constants.Sequences.USER, sequenceName = Constants.Sequences.USER, allocationSize = 1)
  @Column(name = "ID")
  private Long id;

  @Column(name = "USERNAME", unique = true, columnDefinition = ColumnDefinitionConstant.STRING_USERNAME)
  private String username;

  @JsonIgnore
  @Column(name = "PASSWORD")
  private String password;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "CONTACT_NUMBER")
  private String contactNumber;

  @Column(name = "COUNTRY_CODE")
  private String countryCode;

  public enum UserStatus {
    REGISTERED, // REGISTERED TO BE CHANGED WITH INACTIVE
    ACTIVE,
    INACTIVE,
    BLOCKED
  }

  @Column(name = "STATUS")
  @Enumerated(EnumType.STRING)
  private UserStatus status = UserStatus.ACTIVE;

  @NotAudited
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = Constants.Tables.USER_ROLE, joinColumns = {@JoinColumn(name = "USER_FK")}, inverseJoinColumns = {@JoinColumn(name = "ROLE_FK")})
  private Set<Role> roles;

  public enum UserLoginType {
    INDIVIDUAL,
    CORPORATE
  }

  @Enumerated(EnumType.STRING)
  @Column(name = "USER_LOGIN_TYPE")
  private UserLoginType userLoginType;


  @Deprecated
  @Column(name = "FULL_NAME")
  private String fullName;
//  @Deprecated
//  @Enumerated(EnumType.STRING)
//  @Column(name = "GENDER")
//  private Gender gender;
//  @Deprecated
//  @Column(name = "PIN_CODE")
//  private String pinCode;

}
