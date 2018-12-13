package com.cskaoyan.erp.service;

import com.cskaoyan.erp.bean.Role;
import com.cskaoyan.erp.bean.User;
import com.cskaoyan.erp.utils.PageModel;

import java.util.List;

public interface UserService {

    User findUserByUsername(String principal);

    List<String> findRolesByUsername(String principal);

    List<String> findPermissionsByUsername(String principal);

    List<User> findUser(User user, PageModel pageModel);

    boolean insert(User user);

    boolean update(User user);

    boolean deleteUser(String[] ids);

    User findUserById(String id);
}
