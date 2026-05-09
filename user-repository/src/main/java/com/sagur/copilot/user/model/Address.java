package com.sagur.copilot.user.model;

import java.io.Serial;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import org.hibernate.envers.Audited;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.sagur.product.common.utils.constants.Prefix;
import com.sagur.copilot.model.User;
import com.sagur.product.generic.component.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Audited
@Table(name = Prefix.Table.COMMON + "ADDRESS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@EqualsAndHashCode(callSuper = false)
public class Address extends BaseEntity {

  @Serial
  private static final long serialVersionUID = 1L;

  /** Primary key of Address. **/
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Prefix.Sequence.NAME + Prefix.Table.COMMON + "ADDRESS")
  @SequenceGenerator(name = Prefix.Sequence.NAME + Prefix.Table.COMMON + "ADDRESS", sequenceName = Prefix.Sequence.NAME + Prefix.Table.COMMON + "ADDRESS", allocationSize = 1)
  @Column(name = "ID")
  private Long id;

  @Column(name = "ADDRESS_LINE_1")
  private String addressLine1;

  @Column(name = "ADDRESS_LINE_2")
  private String addressLine2;

  @Column(name = "CITY")
  private String city;

  @Column(name = "STATE")
  private String state;

  @Column(name = "PIN_CODE")
  private String pinCode;

  @Column(name = "COUNTRY")
  private String country;

  @ManyToOne
  @JoinColumn(name = "USER_FK")
  private User user;


}
