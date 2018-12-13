package com.cskaoyan.erp.mapper;

import com.cskaoyan.erp.bean.Role;

import java.util.List;
import java.util.Map;

public interface RoleMapper {

     List<Role> selectAllRole();

    int count(Map<String, Object> map);

    List<Role> selectByPage(Map<String, Object> map);

    int insert(Role role);

    int updateByPrimaryKey(Role role);

    int deleteByPrimaryKey(String id);

    Role selectRoleById(String roleId);
}
