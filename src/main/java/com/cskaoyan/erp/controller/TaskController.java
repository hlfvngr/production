package com.cskaoyan.erp.controller;

import com.cskaoyan.erp.bean.Manufacture;
import com.cskaoyan.erp.bean.Task;
import com.cskaoyan.erp.bean.Work;
import com.cskaoyan.erp.service.TaskService;
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
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    //查找生产派工
    @RequestMapping("/find")
    public String find(){
        return "task_list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(PageModel pageModel){
        Map<String,Object> result = new HashMap<>();
        Task task = new Task();
        List<Task> tasks = taskService.findAllTask(task, pageModel);

        result.put("rows",tasks);
        result.put("total",pageModel.getRecordCount());
        return result;
    }

    @RequestMapping("/get/{taskId}")
    @ResponseBody
    public Task get(@PathVariable String taskId){
        return taskService.findTaskById(taskId);
    }

    @RequestMapping("/get_data")
    @ResponseBody
    public List<Task> get_data(){
        return taskService.findAllTask();
    }

    @RequestMapping("/search_task_by_taskId")
    @ResponseBody
    public Map<String,Object> search_task_by_taskId(String searchValue, PageModel pageModel){
        Map<String,Object> result = new HashMap<>();

        Task task = new Task();
        task.setTaskId(searchValue);
        List<Task> tasks = taskService.findAllTask(task, pageModel);

        result.put("rows",tasks);
        result.put("total",pageModel.getRecordCount());
        return result;
    }

    @RequestMapping("/search_task_by_taskWorkId")
    @ResponseBody
    public Map<String,Object> search_task_by_taskWorkId(String searchValue, PageModel pageModel){
        Map<String,Object> result = new HashMap<>();

        Work work = new Work();
        work.setWorkId(searchValue);

        Task task = new Task();
        task.setWork(work);
        List<Task> tasks = taskService.findAllTask(task, pageModel);

        result.put("rows",tasks);
        result.put("total",pageModel.getRecordCount());
        return result;
    }

    @RequestMapping("/search_task_by_taskManufactureSn")
    @ResponseBody
    public Map<String,Object> search_task_by_taskManufactureSn(String searchValue, PageModel pageModel){
        Map<String,Object> result = new HashMap<>();

        Manufacture manufacture = new Manufacture();
        manufacture.setManufactureSn(searchValue);

        Task task = new Task();
        task.setManufacture(manufacture);
        List<Task> tasks = taskService.findAllTask(task, pageModel);

        result.put("rows",tasks);
        result.put("total",pageModel.getRecordCount());
        return result;
    }

    //增加生产派工
    @RequestMapping("/add_judge")
    @ResponseBody
    public Map<String,Object> add_judge(){
        Map<String,Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        boolean b = subject.isPermitted("task:add");
        if(!b){
            map.put("msg","禁止访问!");
        }
        return map;
    }

    @RequestMapping("/add")
    public String add(){
        return "task_add";
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Map<String,Object> insert( @Valid Task task,Manufacture manufacture,Work work, BindingResult bindingResult){
        Map<String,Object> result = new HashMap<>();
        if(bindingResult.hasErrors()){
            return null;
        }
        Task taskById = taskService.findTaskById(task.getTaskId());
        if(taskById != null){
            result.put("status",0);
            result.put("msg","该生产计划id已经存在");
            result.put("data",null);
        }else {
            task.setManufacture(manufacture);
            task.setWork(work);
            boolean b = taskService.insertTask(task);
            if(b){
                result.put("status",200);
                result.put("msg","OK");
                result.put("data",null);
            }else {
                result.put("status",100);
                result.put("msg","fail");
                result.put("data",null);
            }
        }

        return result;
    }

    //修改生产派工
    @RequestMapping("/edit_judge")
    @ResponseBody
    public Map<String,Object> edit_judge(){
        Map<String,Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        boolean b = subject.isPermitted("task:edit");
        if(!b){
            map.put("msg","禁止访问!");
        }
        return map;
    }

    @RequestMapping("/edit")
    public String edit(){
        return "task_edit";
    }

    @RequestMapping("/update_all")
    @ResponseBody
    public Map<String,Object> update_all( @Valid Task task,Manufacture manufacture,Work work, BindingResult bindingResult){
        Map<String,Object> result = new HashMap<>();

        if(bindingResult.hasErrors()){
            return null;
        }
        task.setManufacture(manufacture);
        task.setWork(work);
        boolean b = taskService.updateTask(task);
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

    //删除生产派工
    @RequestMapping("/delete_judge")
    @ResponseBody
    public Map<String,Object> delete_judge(){
        Map<String,Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        boolean b = subject.isPermitted("task:delete");
        if(!b){
            map.put("msg","禁止访问!");
        }
        return map;
    }


    @RequestMapping("/delete_batch")
    @ResponseBody
    public Map<String,Object> delete_batch(@RequestBody @RequestParam("ids") String ids_str){
        Map<String,Object> result = new HashMap<>();

        String[] ids = ids_str.split(",");
        boolean b = taskService.deleteTask(ids);
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
}
