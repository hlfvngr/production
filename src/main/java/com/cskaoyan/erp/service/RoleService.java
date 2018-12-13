package com.cskaoyan.erp.service;

import com.cskaoyan.erp.bean.Role;
import com.cskaoyan.erp.utils.PageModel;

import java.util.List;

public interface RoleService {

    List<Role> getAllRole();

    List<Role> findRole(Role role, PageModel pageModel);

    boolean insert(Role role);

    boolean updateRole(Role role);

    boolean delete(String[] ids);

    Role findRoleById(String roleId);
}
