package com.cskaoyan.erp.controller;

import com.cskaoyan.erp.bean.Permission;
import com.cskaoyan.erp.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping("/update_by_roleid")
    @ResponseBody
    public Map<String,Object> update_by_roleId(String roleId,String permission){
        Map<String,Object> result = new HashMap<>();
        Permission perm = new Permission();
        perm.setSysRoleId(roleId);
        perm.setSysPermissionId(permission);
        boolean b = permissionService.updateByRoleId(perm);
        if(b){
            result.put("msg","OK");
            result.put("data",null);
            result.put("status",200);
        }else {
            result.put("msg","fail");
            result.put("data",null);
            result.put("status",100);
        }
        return result;
    }
}
