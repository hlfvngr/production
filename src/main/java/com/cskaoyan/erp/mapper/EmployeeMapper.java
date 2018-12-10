package com.cskaoyan.erp.mapper;

import com.cskaoyan.erp.bean.Employee;
import com.cskaoyan.erp.bean.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    @Delete({
            "delete from employee",
            "where emp_id = #{empId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String empId);


    int insert(Employee record);

    @Select({
            "select",
            "emp_id, emp_name, sex, id_code, birthday, join_date, status, education, degree, ",
            "major, graduate_school, education_form, department_id",
            "from employee",
            "where emp_id = #{empId,jdbcType=VARCHAR}"
    })
    @ResultMap("com.cskaoyan.erp.mapper.EmployeeMapper.BaseResultMap")
    Employee selectByPrimaryKey(String empId);

    int updateByPrimaryKey(Employee record);

    List<Employee> selectAllEmployee();

    int count(Map<String,Object> map);

    List<Employee> selectByPage(Map<String, Object> params);
}