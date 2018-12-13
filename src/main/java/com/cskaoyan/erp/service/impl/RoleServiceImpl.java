package com.cskaoyan.erp.service.impl;

import com.cskaoyan.erp.bean.Role;
import com.cskaoyan.erp.mapper.RoleMapper;
import com.cskaoyan.erp.service.RoleService;
import com.cskaoyan.erp.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> getAllRole() {
        return  roleMapper.selectAllRole();
    }

    @Override
    public List<Role> findRole(Role role, PageModel pageModel) {
        Map<String,Object> map = new HashMap<>();
        map.put("roleId",role.getRoleId());
        map.put("roleName",role.getRoleName());
        int count = roleMapper.count(map);
        if(count == 0){
            return null;
        }
        pageModel.setRecordCount(count);
        int offset = pageModel.getFirstLimitParam();
        int limit = pageModel.getRows();
        map.put("offset",offset);
        map.put("limit",limit);
        return roleMapper.selectByPage(map);
    }

    @Override
    public boolean insert(Role role) {
        return roleMapper.insert(role) != 0;
    }

    @Override
    public boolean updateRole(Role role) {
        return roleMapper.updateByPrimaryKey(role) != 0;
    }

    @Override
    public boolean delete(String[] ids) {
        for (int i = 0; i < ids.length; i++) {
            int ret = roleMapper.deleteByPrimaryKey(ids[i]);
            if(ret == 0){
                return false;
            }
        }
        return true;
    }

    @Override
    public Role findRoleById(String roleId) {
        return roleMapper.selectRoleById(roleId);
    }
}
