package com.cskaoyan.erp.service.impl;

import com.cskaoyan.erp.bean.Permission;
import com.cskaoyan.erp.mapper.PermissionMapper;
import com.cskaoyan.erp.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl  implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public Permission findPermissionByRoleId(String roleId) {
        return permissionMapper.selectPermissionByRoleId(roleId);
    }
}

