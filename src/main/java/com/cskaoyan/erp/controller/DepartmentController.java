package com.cskaoyan.erp.controller;

import com.cskaoyan.erp.bean.Department;
import com.cskaoyan.erp.service.DepartmentService;
import com.cskaoyan.erp.utils.PageModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;


@Controller("departmentController")
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;

	//增加部门
	@RequestMapping("/add_judge")
	@ResponseBody
	public Map<String,Object> add_judge(){
		Map<String,Object> map = new HashMap<>();
		Subject subject = SecurityUtils.getSubject();
		boolean b = subject.isPermitted("department:add");
		if(!b){
			map.put("msg","禁止访问!");
		}
		return map;
	}

	@RequestMapping("/add")
	public String add() {
		return "department_add";
	}

	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Department department, MultipartFile multipartFile, BindingResult bindingResult){
		Map<String,Object> result = new HashMap<String, Object>();
		if(bindingResult.hasErrors()){
			return null;
		}
		//multipartFile
		boolean b = departmentService.insertDepartment(department);
		if(b){
			result.put("status",200);
			result.put("msg","OK");
			result.put("data",null);
		}else {
			result.put("status",100);
			result.put("msg","fail");
			result.put("data",null);
		}

		return result;
	}

	//修改部门
	@RequestMapping("/edit_judge")
	@ResponseBody
	@RequiresPermissions("department:edit")
	public Map<String,Object> edit_judge(){
		Map<String,Object> map = new HashMap<>();
		Subject subject = SecurityUtils.getSubject();
		boolean b = subject.isPermitted("department:edit");
		if(!b){
			map.put("msg","禁止访问!");
		}
		return map;
	}

	@RequestMapping("/edit")
	public String edit() {
		return "department_edit";
	}




	@RequestMapping("/update_note")
	@ResponseBody
	public Map<String,Object> update_note(@Valid Department department, BindingResult bindingResult){
		Map<String,Object> result = new HashMap<String, Object>();
		//合法性校验
		if(bindingResult.hasErrors()){
			return null;
		}
		boolean ret = departmentService.updateDepartmentNote(department);
		if(ret){
			result.put("status",200);
			result.put("msg","OK");
			result.put("data",null);
		}else {
			result.put("status",100);
			result.put("msg","fail");
			result.put("data",null);
		}
		return result;
	}

	@RequestMapping("/update_all")
	@ResponseBody
	public Map<String,Object> update_all( @Valid Department department,MultipartFile multipartFile,BindingResult bindingResult){
		Map<String,Object> result = new HashMap<String, Object>();
		if(bindingResult.hasErrors()){
			return null;
		}
		//multipartFile
		boolean b = departmentService.updateDepartment(department);
		if(b){
			result.put("status",200);
			result.put("msg","OK");
			result.put("data",null);
		}else {
			result.put("status",100);
			result.put("msg","fail");
			result.put("data",null);
		}
		return result;
	}

	//查找部门
	@RequestMapping("/find")
	public String find() {
		return "department_list";
	}



	@RequestMapping("/list")
	@ResponseBody
	public Map<String,Object> list(PageModel pageModel){
		Map<String,Object> result = new HashMap<String, Object>();
		Department department = new Department();
		List<Department> departments = departmentService.findAllDepartment(department, pageModel);
		result.put("rows",departments);
		result.put("total",pageModel.getRecordCount());
		return result;
	}


	@RequestMapping("/get/{departmentId}")
	@ResponseBody
	public Department get(@PathVariable String departmentId){
		Department department = departmentService.findDepartmentById(departmentId);
		return department;
	}



	//根据部门id查找
	@RequestMapping("/search_department_by_departmentId")
	@ResponseBody
	public Map<String,Object> search_department_by_departmentId(String searchValue, PageModel pageModel){
		Map<String,Object> result = new HashMap<String, Object>();
		Department department = new Department();
		department.setDepartmentId(searchValue);
		List<Department> departments = departmentService.findAllDepartment(department, pageModel);
		result.put("rows",departments);
		result.put("total",pageModel.getRecordCount());
		return result;
	}


	//根据部门名称查找
	@RequestMapping("/search_department_by_departmentName")
	@ResponseBody
	public Map<String,Object> search_department_by_departmentName(String searchValue, PageModel pageModel){
		Map<String,Object> result = new HashMap<String, Object>();
		Department department = new Department();
		department.setDepartmentName(searchValue);
		List<Department> departments = departmentService.findAllDepartment(department, pageModel);
		result.put("rows",departments);
		result.put("total",pageModel.getRecordCount());
		return result;
	}


	//获得所有商品的信息
	@RequestMapping("/get_data")
	@ResponseBody
	public List<Department> get_data(){
		return departmentService.findAllDepartment();
	}



	//删除部门
	@RequestMapping("/delete_judge")
	@ResponseBody
	public Map<String,Object> delete_judge(){
		Map<String,Object> map = new HashMap<>();
		Subject subject = SecurityUtils.getSubject();
		boolean b = subject.isPermitted("department:delete");
		if(!b){
			map.put("msg","禁止访问!");
		}
		return map;
	}


	@RequestMapping("/delete_batch")
	@ResponseBody
	public Map<String,Object> delete_batch(@RequestBody @RequestParam("ids") String ids_str){
		Map<String,Object> result = new HashMap<String, Object>();
		String[] ids = ids_str.split(",");
		//验证合法性
		boolean ret = departmentService.deleteDepartment(ids);
		if(ret){
			result.put("status",200);
			result.put("msg","OK");
			result.put("data",null);
		}else {
			result.put("status",100);
			result.put("msg","fail");
			result.put("data",null);
		}
		return result;
	}
}
