package com.cskaoyan.erp.mapper;

import com.cskaoyan.erp.bean.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface DepartmentMapper {
    @Delete({
            "delete from department",
            "where department_id = #{departmentId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String departmentId);

    int insert(Department record);

    int insertSelective(Department record);

    @Select({
            "select",
            "department_id, department_name, note",
            "from department",
            "where department_id = #{departmentId,jdbcType=VARCHAR}"
    })
    @ResultMap("com.cskaoyan.erp.mapper.DepartmentMapper.BaseResultMap")
    Department selectByPrimaryKey(String departmentId);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);


    List<Department> selectAllDepartment();

    int count(Map<String,Object> map);

    List<Department> selectByPage(Map<String, Object> params);
}