package com.cskaoyan.erp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cskaoyan.erp.bean.Department;
import com.cskaoyan.erp.bean.Employee;
import com.cskaoyan.erp.mapper.DepartmentMapper;
import com.cskaoyan.erp.mapper.EmployeeMapper;

import com.cskaoyan.erp.service.EmployeeService;
import com.cskaoyan.erp.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeMapper employeeMapper;


	public List<Employee> findAllEmployee() {
		return employeeMapper.selectAllEmployee();
	}

	public List<Employee> findAllEmployee(Employee employee, PageModel pageModel) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("employeeId",employee.getEmpId());
		map.put("employeeName",employee.getEmpName());
		map.put("department", employee.getDepartment());
		int count = employeeMapper.count(map);
		if(count == 0){
			return null;
		}
		pageModel.setRecordCount(count);
		int offset = pageModel.getFirstLimitParam();
		int limit = pageModel.getRows();
		map.put("limit",limit);
		map.put("offset",offset);
		return employeeMapper.selectByPage(map);
	}

	public boolean insertEmployee(Employee employee) {
		return employeeMapper.insert(employee) != 0;
	}

	public boolean updateEmployee(Employee employee) {
		return employeeMapper.updateByPrimaryKey(employee) != 0;
	}

	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
	public boolean deleteEmployee(String[] ids) {
		for (int i = 0; i < ids.length; i++) {
			int ret = employeeMapper.deleteByPrimaryKey(ids[i]);
			if(ret == 0){
				return false;
			}
		}
		return true;
	}

	public boolean updateEmployeeNote(Employee employee) {
		return employeeMapper.updateByPrimaryKey(employee) != 0;
	}

	public Employee findEmployeeById(String empId) {
		return employeeMapper.selectByPrimaryKey(empId);
	}
}
