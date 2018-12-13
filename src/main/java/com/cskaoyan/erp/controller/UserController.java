package com.cskaoyan.erp.controller;

import com.cskaoyan.erp.bean.Role;
import com.cskaoyan.erp.bean.User;
import com.cskaoyan.erp.service.UserService;
import com.cskaoyan.erp.utils.PageModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/role")
    public String role(){
        return "user_role_edit";
    }

    //查找用户
    @RequestMapping("/find")
    public String find(){
        return "user_list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(PageModel pageModel){
        Map<String,Object> result = new HashMap<>();

        User user = new User();
        List<User> users = userService.findUser(user,pageModel);

        result.put("rows",users);
        result.put("total",pageModel.getRecordCount());
        return result;
    }

    @RequestMapping("/search_user_by_userId")
    @ResponseBody
    public Map<String,Object> search_user_by_userId(User user ,String searchValue,PageModel pageModel){
        Map<String,Object> result = new HashMap<>();
        user.setId(searchValue);
        List<User> users = userService.findUser(user,pageModel);

        result.put("rows",users);
        result.put("total",pageModel.getRecordCount());
        return result;
    }

    @RequestMapping("/search_user_by_userName")
    @ResponseBody
    public Map<String,Object> search_user_by_userName(User user ,String searchValue,PageModel pageModel){
        Map<String,Object> result = new HashMap<>();
        user.setUsername(searchValue);
        List<User> users = userService.findUser(user,pageModel);

        result.put("rows",users);
        result.put("total",pageModel.getRecordCount());
        return result;
    }

    @RequestMapping("/search_user_by_roleName")
    @ResponseBody
    public Map<String,Object> search_user_by_roleName(User user ,String searchValue,PageModel pageModel){
        Map<String,Object> result = new HashMap<>();
        user.setRoleName(searchValue);
        List<User> users = userService.findUser(user,pageModel);

        result.put("rows",users);
        result.put("total",pageModel.getRecordCount());
        return result;
    }

    //增加用户
    @RequestMapping("/add_judge")
    @ResponseBody
    public Map<String,Object> add_judge(){
        Map<String,Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        boolean b = subject.isPermitted("user:add");
        if(!b){
            map.put("msg","禁止访问!");
        }
        return map;
    }

    @RequestMapping("/add")
    public String add(){
        return "user_add";
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Map<String,Object> insert(@Valid User user, BindingResult bindingResult){
        Map<String,Object> result = new HashMap<>();
        if(bindingResult.hasErrors()){
           return null;
        }
        //需要判断数据库中是否存在相同id的用户
        User userById = userService.findUserById(user.getId());
        if(userById != null){
            result.put("status",0);
            result.put("msg","新增用户id已经存在");
            result.put("data",null);
        }else {
            boolean b = userService.insert(user);
            if(b){
                result.put("status",200);
                result.put("msg","新增用户信息成功");
                result.put("data",null);
            }else {
                result.put("status",100);
                result.put("msg","新增用户信息失败");
                result.put("data",null);
            }
        }
        return result;
    }

    //修改用户
    @RequestMapping("/edit_judge")
    @ResponseBody
    public Map<String,Object> edit_judge(){
        Map<String,Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        boolean b = subject.isPermitted("user:edit");
        if(!b){
            map.put("msg","禁止访问!");
        }
        return map;
    }

    @RequestMapping("/edit")
    public String edit(){
        return "user_edit";
    }

    @RequestMapping("/update_all")
    @ResponseBody
    public Map<String,Object> update_all(@Valid User user, BindingResult bindingResult){
        Map<String,Object> result = new HashMap<>();
        if(bindingResult.hasErrors()) {
            return null;
        }
        //需要判断数据库中是否存在相同id的用户
        boolean b = userService.update(user);
        if(b){
            result.put("status",200);
            result.put("msg","修改用户信息成功");
            result.put("data",null);
        }else {
            result.put("status",100);
            result.put("msg","修改用户信息失败");
            result.put("data",null);
        }
        return result;
    }

    //删除用户
    @RequestMapping("/delete_judge")
    @ResponseBody
    public Map<String,Object> delete_judge(){
        Map<String,Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        boolean b = subject.isPermitted("user:delete");
        if(!b){
            map.put("msg","禁止访问!");
        }
        return map;
    }

    @RequestMapping("/delete_batch")
    @ResponseBody
    public Map<String,Object> delete_batch(@RequestBody @RequestParam("ids") String ids_str) {
        Map<String,Object> result = new HashMap<>();

        String[] ids = ids_str.split(",");
        boolean b = userService.deleteUser(ids);
        if(b){
            result.put("status",200);
            result.put("msg","删除用户信息成功");
            result.put("data",null);
        }else {
            result.put("status",100);
            result.put("msg","删除用户信息失败");
            result.put("data",null);
        }
        return result;
    }

}
