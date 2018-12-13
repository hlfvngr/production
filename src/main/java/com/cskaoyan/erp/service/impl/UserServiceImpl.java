package com.cskaoyan.erp.service.impl;


import com.cskaoyan.erp.bean.User;
import com.cskaoyan.erp.mapper.UserMapper;
import com.cskaoyan.erp.service.UserService;
import com.cskaoyan.erp.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("uerService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    public User findUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    public List<String> findRolesByUsername(String username) {
        return userMapper.selectRolesByUsername(username);
    }

    public List<String> findPermissionsByUsername(String username) {
        //通过用户名找到用户权限字符串
        List<String> permissions = new ArrayList<>();
        String permissionStr = userMapper.selectPermissionStrByUsername(username);
        if(permissionStr == null || permissionStr.isEmpty()){
            return null;
        }
        //分割字符串进行查找
        String[] permIds = permissionStr.split(",");
        for(String permId : permIds){
             String perCode = userMapper.selectPermissionByPermId(permId);
             permissions.add(perCode);
        }
        return permissions;
    }

    @Override
    public List<User> findUser(User user, PageModel pageModel) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",user.getId());
        map.put("username",user.getUsername());
        map.put("roleName",user.getRoleName());

        int count = userMapper.count(map);
        if(count == 0){
            return null;
        }
        pageModel.setRecordCount(count);
        int offset = pageModel.getFirstLimitParam();
        int limit = pageModel.getRows();
        map.put("limit",limit);
        map.put("offset",offset);

        return userMapper.selectUserByPage(map);
    }

    @Override
    public boolean insert(User user) {
        //在这里只需用户,对用户与角色的关系表进行操作 2张
        return userMapper.addUser(user) != 0 && userMapper.addRole(user.getId(),user.getRoleId()) != 0;
    }

    @Override
    public boolean update(User user) {
        return userMapper.updateUser(user) != 0 && userMapper.updateRole(user.getId(),user.getRoleId()) != 0;
    }

    @Override
    public boolean deleteUser(String[] ids) {
        for (int i = 0; i < ids.length; i++) {
            int ret = userMapper.deleteByPrimaryKey(ids[i]);
            int ret1 = userMapper.deleteRole(ids[i]);
            if(ret == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public User findUserById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
