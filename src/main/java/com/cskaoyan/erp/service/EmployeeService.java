package com.cskaoyan.erp.service;

import java.util.List;

import com.cskaoyan.erp.bean.Employee;
import com.cskaoyan.erp.utils.PageModel;

public interface EmployeeService {

	List<Employee> findAllEmployee();

	List<Employee> findAllEmployee(Employee employee, PageModel pageModel);

	boolean insertEmployee(Employee employee);

	boolean updateEmployee(Employee employee);

	boolean deleteEmployee(String[] ids);

	boolean updateEmployeeNote(Employee employee);

	Employee findEmployeeById(String empId);
}
