package com.cskaoyan.erp.controller;

import java.util.List;

import javax.validation.Valid;


import com.cskaoyan.erp.bean.Department;
import com.cskaoyan.erp.service.DepartmentService;
import com.cskaoyan.erp.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller("departmentController")
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;

	//增加部门
	@RequestMapping("/add_judge")
	@ResponseBody
	public void add_judge(){}

	@RequestMapping("/add")
	public String add() {
		return "department_add";
	}

	@RequestMapping(value="/insert")
	@ResponseBody
	public String insert(@Valid Department department, MultipartFile multipartFile, BindingResult bindingResult) {

		if(bindingResult.hasErrors()){
			return "department_add";
		}
		else{
			boolean b = departmentService.insertDepartment(department);
			if(b){
				return "department_list";
			}else {
				return "department_add";
			}
		}
	}

	//修改部门
	@RequestMapping("/edit_judge")
	@ResponseBody
	public void edit_judge(){}

	@RequestMapping("/edit")
	public String edit() {
		return "department_edit";
	}




	@RequestMapping("/update_note")
	public String update_note(@RequestBody Department department){
		//合法性校验
		boolean ret = departmentService.updateDepartmentNote(department);
		if(ret){
			return "department_list";
		}else {
			return "forward:/update_note";
		}
	}

	@RequestMapping("/update_all")
	@ResponseBody
	public String update_all(@Valid Department department,MultipartFile multipartFile,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return "department_edit";
		}else {
			boolean b = departmentService.updateDepartment(department);
			if(b){
				return "department_list";
			}else {
				return "department_edit";
			}
		}
	}


	@RequestMapping("/find")
	public String find() {
		return "department_list";
	}



	@RequestMapping("/list")
	@ResponseBody
	public List<Department> list(PageModel pageModel){
		Department department = new Department();
		return departmentService.findAllDepartment(department,pageModel);
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
	public  List<Department> search_department_by_departmentId(String searchValue, PageModel pageModel){
		Department department = new Department();
		department.setDepartmentId(searchValue);
		List<Department> departments = departmentService.findAllDepartment(department, pageModel);
		return departments;
	}


	//根据部门名称查找
	@RequestMapping("/search_department_by_departmentName")
	@ResponseBody
	public  List<Department> search_department_by_departmentName(String searchValue, PageModel pageModel){
		Department department = new Department();
		department.setDepartmentName(searchValue);
		List<Department> departments = departmentService.findAllDepartment(department, pageModel);
		return departments;
	}


//	@RequestMapping("/get_data")
//	@ResponseBody
//	public List<Department> getData() throws Exception{
//		return departmentService.find();
//	}
//



//	@RequestMapping(value="/update_note")
//	@ResponseBody
//	private CustomResult updateNote(@Valid Department department, BindingResult bindingResult) throws Exception {
//		if(bindingResult.hasErrors()){
//			FieldError fieldError = bindingResult.getFieldError();
//			return CustomResult.build(100, fieldError.getDefaultMessage());
//		}
//		return departmentService.updateNote(department);
//	}
//
//	@RequestMapping(value="/delete")
//	@ResponseBody
//	private CustomResult delete(String id) throws Exception {
//		CustomResult result = departmentService.delete(id);
//		return result;
//	}

	//删除部门
	@RequestMapping("/delete_judge")
	@ResponseBody
	public void delete_judge(){}


	@RequestMapping("/delete_batch")
	@ResponseBody
	public String delete_batch(@RequestBody @RequestParam("ids") String ids_str){
		String[] ids = ids_str.split(",");
		//验证合法性
		boolean ret = departmentService.deleteDepartment(ids);
		if(ret){
			return "department_list";
		}else {
			return "department_list";
		}
	}
}
