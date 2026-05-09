package com.sagur.copilot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import com.sagur.product.common.utils.constants.Constants;
import com.sagur.product.common.utils.enums.RoleName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = Constants.Tables.ROLE)
@NoArgsConstructor
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = Constants.Sequences.ROLE)
  @SequenceGenerator(name = Constants.Sequences.ROLE, sequenceName = Constants.Sequences.ROLE, allocationSize = 1)
  @Column(name = "ID")
  private Long id;

  @Column(name = "NAME")
  @Enumerated(EnumType.STRING)
  private RoleName name;

  public Role(RoleName roleName) {
    this.name = roleName;
  }
}
