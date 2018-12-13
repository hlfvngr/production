package com.cskaoyan.erp.controller;

import com.cskaoyan.erp.bean.Permission;
import com.cskaoyan.erp.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @RequestMapping("/get_permission")
    @ResponseBody
    public Permission get_permission(String roleId){
        return permissionService.findPermissionByRoleId(roleId);
    }
}
