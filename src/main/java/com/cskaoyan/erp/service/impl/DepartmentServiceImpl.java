package com.cskaoyan.erp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.cskaoyan.erp.bean.Department;
import com.cskaoyan.erp.mapper.DepartmentMapper;
import com.cskaoyan.erp.service.DepartmentService;
import com.cskaoyan.erp.utils.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentMapper departmentMapper;

	public List<Department> findAllDepartment() {
		return departmentMapper.selectAllDepartment();
	}

	public List<Department> findAllDepartment(Department department, PageModel pageModel) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("departmentId",department.getDepartmentId());
		map.put("departmentName",department.getDepartmentName());
		int count = departmentMapper.count(map);
		if(count == 0){
			return null;
		}
		pageModel.setRecordCount(count);
		int offset = pageModel.getFirstLimitParam();
		int limit = pageModel.getRows();
		map.put("limit",limit);
		map.put("offset",offset);
		return departmentMapper.selectByPage(map);
	}

	public boolean insertDepartment(Department department) {
		return departmentMapper.insert(department) != 0;
	}

	public boolean updateDepartment(Department department) {
		return departmentMapper.updateByPrimaryKey(department) != 0;
	}

	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
	public boolean deleteDepartment(String[] ids) {
		for (int i = 0; i < ids.length; i++) {
			int ret = departmentMapper.deleteByPrimaryKey(ids[i]);
			if(ret == 0){
				return false;
			}
		}
		return true;
	}

	public boolean updateDepartmentNote(Department department) {
		return departmentMapper.updateByPrimaryKey(department) != 0;
	}

	public Department findDepartmentById(String departmentId) {
		return departmentMapper.selectByPrimaryKey(departmentId);
	}
}
