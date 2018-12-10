package com.cskaoyan.erp.controller;

import java.util.List;

import javax.validation.Valid;


import com.cskaoyan.erp.bean.*;
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
	public String insert(@Valid Employee employee, MultipartFile multipartFile, BindingResult bindingResult){

		if(bindingResult.hasErrors()){
			return "employee_add";
		}else {
			//multipartFile
			boolean b = employeeService.insertEmployee(employee);
			if(b){
				return "employee_list";
			}else {
				return "employee_add";
			}
		}
	}

	@RequestMapping("/edit_judge")
	@ResponseBody
	public void edit_judge(){}

	@RequestMapping("/edit")
	public String edit() {
		return "employee_edit";
	}


	@RequestMapping("/update_note")
	@ResponseBody
	public String update_note(@Valid Employee employee){
		//合法性校验
		boolean ret = employeeService.updateEmployeeNote(employee);
		if(ret){
			return "employee_list";
		}else {
			return "forward:/update_note";
		}
	}

	@RequestMapping(value="/update_all")
	@ResponseBody
	public String update_all(@RequestBody @Valid Employee employee, MultipartFile multipartFile, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return "employee_edit";
		}else {
			boolean b = employeeService.updateEmployee(employee);
			if(b){
				return "employee_list";
			}else {
				return "employee_edit";
			}
		}
	}


	@RequestMapping("/find")
	public String find() {
		return "employee_list";
	}

	@RequestMapping("/list")
	@ResponseBody
	public List<Employee> list(PageModel pageModel){
		Employee employee = new Employee();
		return employeeService.findAllEmployee(employee,pageModel);
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
	public  List<Employee> search_employee_by_employeeId(String searchValue, PageModel pageModel){
		Employee employee = new Employee();
		employee.setEmpId(searchValue);
		List<Employee> employees = employeeService.findAllEmployee(employee, pageModel);
		return employees;
	}

	//根据员工姓名查找
	@RequestMapping("/search_employee_by_employeeName")
	@ResponseBody
	public  List<Employee> search_employee_by_employeeName(String searchValue, PageModel pageModel){
		Employee employee = new Employee();
		employee.setEmpName(searchValue);
		List<Employee> employees = employeeService.findAllEmployee(employee, pageModel);
		return employees;
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

	//删除员工
	@RequestMapping("/delete_judge")
	@ResponseBody
	public void delete_judge(){}

	@RequestMapping("/delete_batch")
	@ResponseBody
	public String delete_batch(@RequestParam("ids") String ids_str){
		String[] ids = ids_str.split(",");
		//验证合法性
		boolean ret = employeeService.deleteEmployee(ids);
		if(ret){
			return "employee_list";
		}else {
			return "employee_list";
		}
	}





}
