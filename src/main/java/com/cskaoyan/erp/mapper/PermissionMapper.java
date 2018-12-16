package com.cskaoyan.erp.mapper;

import com.cskaoyan.erp.bean.Permission;

public interface PermissionMapper {

    Permission selectPermissionByRoleId(String roleId);

    int updateByRoleId(Permission perm);
}
