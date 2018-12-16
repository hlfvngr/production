package com.cskaoyan.erp.controller;

import com.cskaoyan.erp.bean.Role;
import com.cskaoyan.erp.bean.User;
import com.cskaoyan.erp.service.RoleService;
import com.cskaoyan.erp.utils.PageModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping("/permission")
    public String permission(){
        return "role_permission";
    }

    @RequestMapping("/get_data")
    @ResponseBody
    public List<Role> get_data(){
        return roleService.getAllRole();
    }

    //查找权限
    @RequestMapping("/find")
    public String find(){
        return "role_list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(PageModel pageModel){
        Map<String,Object> result = new HashMap<>();

        Role role = new Role();
        List<Role> roles = roleService.findRole(role,pageModel);

        result.put("rows",roles);
        result.put("total",pageModel.getRecordCount());
        return result;
    }

    @RequestMapping("/get/{roleId}")
    @ResponseBody
    public Role get(@PathVariable String roleId){
        return roleService.findRoleById(roleId);
    }

    @RequestMapping("/search_role_by_roleId")
    @ResponseBody
    public Map<String,Object> search_role_by_roleId(String searchValue,PageModel pageModel){
        Map<String,Object> result = new HashMap<>();

        Role role = new Role();
        role.setRoleId(searchValue);
        List<Role> roles = roleService.findRole(role,pageModel);

        result.put("rows",roles);
        result.put("total",pageModel.getRecordCount());
        return result;
    }

    @RequestMapping("/search_role_by_roleName")
    @ResponseBody
    public Map<String,Object> search_role_by_roleName(String searchValue,PageModel pageModel){
        Map<String,Object> result = new HashMap<>();

        Role role = new Role();
        role.setRoleName(searchValue);
        List<Role> roles = roleService.findRole(role,pageModel);

        result.put("rows",roles);
        result.put("total",pageModel.getRecordCount());
        return result;
    }

    //增加权限
    @RequestMapping("/add_judge")
    @ResponseBody
    public Map<String,Object> add_judge(){
        Map<String,Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        boolean b = subject.isPermitted("role:add");
        if(!b){
            map.put("msg","禁止访问!");
        }
        return map;
    }

    @RequestMapping("/add")
    public String add(){
        return "role_add";
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Map<String,Object> insert(@Valid Role role, BindingResult bindingResult){
        Map<String,Object> result = new HashMap<>();
        if(bindingResult.hasErrors()){
            return null;
        }
        Role roleById = roleService.findRoleById(role.getRoleId());
        if(roleById != null){
            result.put("status",0);
            result.put("msg","新增用户id已经存在");
            result.put("data",null);
        }else{
            boolean b = roleService.insert(role);
            if(b){
                result.put("status",200);
                result.put("msg","新增权限信息成功");
                result.put("data",null);
            }else {
                result.put("status",100);
                result.put("msg","新增权限信息失败");
                result.put("data",null);
            }
        }
        return result;
    }

    //修改权限
    @RequestMapping("/edit_judge")
    @ResponseBody
    public Map<String,Object> edit_judge(){
        Map<String,Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        boolean b = subject.isPermitted("role:edit");
        if(!b){
            map.put("msg","禁止访问!");
        }
        return map;
    }

    @RequestMapping("/edit")
    public String edit(){
        return "role_edit";
    }

    @RequestMapping("/update_all")
    @ResponseBody//在查看用户时，不允许对某个角色的权限进行修改
    public Map<String,Object> update_all(){
        Map<String,Object> result = new HashMap<>();

            result.put("status",0);
            result.put("msg","此处页面不支持修改！");
            result.put("data",null);

        return result;
    }

    //删除权限
    @RequestMapping("/delete_judge")
    @ResponseBody
    public Map<String,Object> delete_judge(){
        Map<String,Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        boolean b = subject.isPermitted("role:delete");
        if(!b){
            map.put("msg","禁止访问!");
        }
        return map;
    }

    @RequestMapping("/delete_batch")
    @ResponseBody
    public Map<String ,Object>delete_batch(@RequestBody @RequestParam("ids") String ids_str){
        Map<String,Object> result = new HashMap<>();
        String[] ids = ids_str.split(",");
        boolean b = roleService.delete(ids);
        if(b){
            result.put("status",200);
            result.put("msg","删除权限信息成功");
            result.put("data",null);
        }else {
            result.put("status",100);
            result.put("msg","删除权限信息失败");
            result.put("data",null);
        }
        return result;
    }
}
