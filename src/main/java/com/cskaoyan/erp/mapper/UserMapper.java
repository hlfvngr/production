package com.cskaoyan.erp.mapper;

import com.cskaoyan.erp.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    @Delete({
        "delete from sys_user",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    int insert(User user);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKey(User user);

    User selectUserByUsername(String username);

    List<String> selectRolesByUsername(String username);

    List<String> selectPermissionsByUsername(String username);

    int count(Map<String, Object> map);

    List<User> selectUserByPage(Map<String, Object> map);

    int addUser(User user);

    int addRole(@Param("userId") String userId,@Param("roleId") String roleId);

    int updateUser(User user);

    int updateRole(@Param("userId")String userId,@Param("roleId") String roleId);

    int deleteRole(String userId);

    String selectPermissionStrByUsername(String username);

    String selectPermissionByPermId(String perm);
}