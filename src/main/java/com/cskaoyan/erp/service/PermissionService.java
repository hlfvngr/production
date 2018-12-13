package com.cskaoyan.erp.service;

import com.cskaoyan.erp.bean.Permission;

public interface PermissionService {

    Permission findPermissionByRoleId(String roleId);
}
