package com.cskaoyan.erp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;


import com.cskaoyan.erp.bean.Department;
import com.cskaoyan.erp.bean.Employee;
import com.cskaoyan.erp.service.EmployeeService;
import com.cskaoyan.erp.service.DepartmentService;
import com.cskaoyan.erp.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller("employeeController")
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	private DepartmentService departmentService;

	@RequestMapping("/add_judge")
	@ResponseBody
	public void add_judge(){}

	@RequestMapping("/add")
	public String add() {
		return "employee_add";
	}



	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(@Valid Employee employee, MultipartFile multipartFile, BindingResult bindingResult){
		Map<String,Object> result = new HashMap<String, Object>();
		if(bindingResult.hasErrors()){
			return null;
		}
		//multipartFile
		boolean b = employeeService.insertEmployee(employee);
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

	//修改雇员
	@RequestMapping("/edit_judge")
	@ResponseBody
	public void edit_judge(){}

	@RequestMapping("/edit")
	public String edit() {
		return "employee_edit";
	}


	@RequestMapping("/update_note")
	@ResponseBody
	public Map<String,Object> update_note(@Valid Employee employee,BindingResult bindingResult){
		Map<String,Object> result = new HashMap<String, Object>();
		//合法性校验
		if(bindingResult.hasErrors()){
			return null;
		}
		boolean ret = employeeService.updateEmployeeNote(employee);
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

	@RequestMapping(value="/update_all")
	@ResponseBody
	public Map<String,Object> update_all( @Valid Employee employee,MultipartFile multipartFile,BindingResult bindingResult){
		Map<String,Object> result = new HashMap<String, Object>();
		if(bindingResult.hasErrors()){
			return null;
		}
		//multipartFile
		boolean b = employeeService.updateEmployee(employee);
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


	@RequestMapping("/find")
	public String find() {
		return "employee_list";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String,Object> list(PageModel pageModel){
		Map<String,Object> result = new HashMap<String, Object>();
		Employee employee = new Employee();
		List<Employee> employees = employeeService.findAllEmployee(employee, pageModel);
		result.put("rows",employees);
		result.put("total",employees.size());
		return result;
	}

	@RequestMapping("/get/{empId}")
	@ResponseBody
	public Employee get(@PathVariable String empId){
		Employee employee = employeeService.findEmployeeById(empId);
		return employee;
	}


	//根据员工id查找
	@RequestMapping("/search_employee_by_employeeId")
	@ResponseBody
	public  Map<String,Object> search_employee_by_employeeId(String searchValue, PageModel pageModel){
		Map<String,Object> result = new HashMap<String, Object>();
		Employee employee = new Employee();
		employee.setEmpId(searchValue);
		List<Employee> employees = employeeService.findAllEmployee(employee, pageModel);
		result.put("rows",employees);
		result.put("total",employees.size());
		return result;
	}

	//根据员工姓名查找
	@RequestMapping("/search_employee_by_employeeName")
	@ResponseBody
	public Map<String,Object> search_employee_by_employeeName(String searchValue, PageModel pageModel){
		Map<String,Object> result = new HashMap<String, Object>();
		Employee employee = new Employee();
		employee.setEmpName(searchValue);
		List<Employee> employees = employeeService.findAllEmployee(employee, pageModel);
		result.put("rows",employees);
		result.put("total",employees.size());
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
		result.put("total",departments.size());
		return result;
	}

	//获得所有商品的信息
	@RequestMapping("/get_data")
	@ResponseBody
	public List<Employee> get_data(){
		return employeeService.findAllEmployee();
	}

	//删除员工
	@RequestMapping("/delete_judge")
	@ResponseBody
	public void delete_judge(){}

	@RequestMapping("/delete_batch")
	@ResponseBody
	public Map<String,Object> delete_batch(@RequestBody @RequestParam("ids") String ids_str){
		Map<String,Object> result = new HashMap<String, Object>();
		String[] ids = ids_str.split(",");
		//验证合法性
		boolean ret = employeeService.deleteEmployee(ids);
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
