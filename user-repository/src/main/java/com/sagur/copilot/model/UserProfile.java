package com.sagur.copilot.model;

import java.io.Serial;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import org.hibernate.envers.Audited;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.sagur.copilot.constants.Prefix;
import com.sagur.product.generic.component.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Audited
@Table(name = Prefix.Table.COMMON + "USER_PROFILE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@EqualsAndHashCode(callSuper = false)
public class UserProfile extends BaseEntity {

  @Serial
  private static final long serialVersionUID = 1L;

  /** Primary key of UserProfile. **/
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Prefix.Sequence.NAME + Prefix.Table.COMMON + "USER_PROFILE")
  @SequenceGenerator(name = Prefix.Sequence.NAME + Prefix.Table.COMMON + "USER_PROFILE", sequenceName = Prefix.Sequence.NAME + Prefix.Table.COMMON + "USER_PROFILE", allocationSize = 1)
  @Column(name = "ID")
  private Long id;

  @OneToOne
  @JoinColumn(name = "USER_FK")
  private User user;

  @Column(name = "PROFILE_NAME")
  private String profileName;

//  @Column(name = "PROFILE_IMAGE") // need to fetch from document table db
//  private String profileImage;

  @Column(name = "DOB")
  private LocalDate dob;

  @Column(name = "EMERGENCY_CONTACT")
  private String emergencyContact;

  @Deprecated // referral code entity introduced separately to manage referral code generation and mapping with user
  @Column(name = "REFERRAL_CODE", length = 10, unique = true)
  private String referralCode;

}
