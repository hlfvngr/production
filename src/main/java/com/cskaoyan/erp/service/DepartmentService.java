package com.cskaoyan.erp.service;

import com.cskaoyan.erp.bean.Department;
import com.cskaoyan.erp.utils.PageModel;

import java.util.List;



public interface DepartmentService {

	List<Department> findAllDepartment();

	List<Department> findAllDepartment(Department department, PageModel pageModel);

	boolean insertDepartment(Department department);

	boolean updateDepartment(Department department);

	boolean deleteDepartment(String[] ids);

	boolean updateDepartmentNote(Department department);

	Department findDepartmentById(String departmentId);
}
