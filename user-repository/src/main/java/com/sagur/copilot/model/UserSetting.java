package com.sagur.copilot.model;

import java.io.Serial;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import org.hibernate.envers.Audited;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.sagur.copilot.constants.ColumnDefinitionConstant;
import com.sagur.copilot.constants.Prefix;
import com.sagur.product.generic.component.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Audited
@Table(name = Prefix.Table.COMMON + "USER_SETTING")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@EqualsAndHashCode(callSuper = false)
public class UserSetting extends BaseEntity {

  @Serial
  private static final long serialVersionUID = 1L;

  /** Primary key of UserSetting. **/
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Prefix.Sequence.NAME + Prefix.Table.COMMON + "USER_SETTING")
  @SequenceGenerator(name = Prefix.Sequence.NAME + Prefix.Table.COMMON + "USER_SETTING", sequenceName = Prefix.Sequence.NAME + Prefix.Table.COMMON + "USER_SETTING", allocationSize = 1)
  @Column(name = "ID")
  private Long id;

  @Column(name = "USERNAME", columnDefinition = ColumnDefinitionConstant.STRING_USERNAME)
  private String username;

  @Column(name = "PROP_KEY")
  private String propKey;

  @Column(name = "PROP_VALUE")
  private String propValue;

}
