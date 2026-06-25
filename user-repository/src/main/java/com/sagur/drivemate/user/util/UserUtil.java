package com.sagur.drivemate.user.util;

import java.util.List;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.sagur.product.common.utils.enums.RoleName;
import com.sagur.product.common.utils.exceptions.BusinessException;
import com.sagur.drivemate.user.model.Role;

public class UserUtil {

  public static Role getRole(List<Role> roles, RoleName roleName) {
    if(CollectionUtils.isEmpty(roles) || roleName == null) {
      throw new BusinessException("Roles list and RoleName must not be null");
    }

    return roles.stream().filter(role->roleName.equals(role.getName())).findFirst().orElse(null);
  }

  public static boolean isRoleMatched(List<Role> roles, RoleName roleName) {
    if(CollectionUtils.isEmpty(roles) || roleName == null) {
      throw new BusinessException("Roles list and RoleName must not be null");
    }

    return roles.stream().anyMatch(role->roleName.equals(role.getName()));
  }

  public static boolean isRoleMatched(Set<Role> roles, RoleName roleName) {
    if(CollectionUtils.isEmpty(roles) || roleName == null) {
      throw new BusinessException("Roles list and RoleName must not be null");
    }

    return roles.stream().anyMatch(role->roleName.equals(role.getName()));
  }

  public static Set<RoleName> getRoleNames(Set<Role> roles) {
    if(CollectionUtils.isEmpty(roles)) {
      return java.util.Collections.emptySet();
    }
    return roles.stream().map(Role::getName).collect(java.util.stream.Collectors.toSet());
  }

  public static boolean isRoleMatched(List<String> roles, String roleName) {
    if(CollectionUtils.isEmpty(roles) || roleName == null) {
      throw new BusinessException("Roles list and RoleName must not be null");
    }
    return roles.contains(roleName);
  }

  public static boolean isRoleMatched(Set<String> roles, String roleName) {
    if(CollectionUtils.isEmpty(roles) || roleName == null) {
      throw new BusinessException("Roles list and RoleName must not be null");
    }
    return roles.contains(roleName);
  }

}
