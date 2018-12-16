package com.cskaoyan.erp.controller;

import com.cskaoyan.erp.bean.Manufacture;
import com.cskaoyan.erp.bean.Order;
import com.cskaoyan.erp.bean.Technology;
import com.cskaoyan.erp.service.ManufactureService;
import com.cskaoyan.erp.service.OrderService;
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
@RequestMapping("/manufacture")
public class ManufactureController {

    @Autowired
    ManufactureService manufactureService;

    //查找生产计划
    @RequestMapping("/find")
    public String find(){
        return "manufacture_list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(PageModel pageModel){
        Map<String,Object> result = new HashMap<>();

        Manufacture manufacture = new Manufacture();
        List<Manufacture> manufactures = manufactureService.findManufacture(manufacture,pageModel);

        result.put("rows",manufactures);
        result.put("total",pageModel.getRecordCount());
        return result;
    }

    @RequestMapping("/get_data")
    @ResponseBody
    public List<Manufacture> get_data(){
        return manufactureService.findAllManufacture();
    }

    @RequestMapping("/get/{manufactureSn}")
    @ResponseBody
    public Manufacture get(@PathVariable String manufactureSn){
        return manufactureService.findManufactureById(manufactureSn);
    }

    @RequestMapping("/search_manufacture_by_manufactureSn")
    @ResponseBody
    public Map<String,Object> search_manufacture_by_manufactureSn(String searchValue,PageModel pageModel){
        Map<String,Object> result = new HashMap<>();

        Manufacture manufacture = new Manufacture();
        manufacture.setManufactureSn(searchValue);
        List<Manufacture> manufactures = manufactureService.findManufacture(manufacture, pageModel);

        result.put("rows",manufactures);
        result.put("total",pageModel.getRecordCount());
        return result;
    }

    @RequestMapping("/search_manufacture_by_manufactureOrderId")
    @ResponseBody
    public Map<String,Object> search_manufacture_by_manufactureOrderId(String searchValue,PageModel pageModel){
        Map<String,Object> result = new HashMap<>();

        Order order = new Order();
        order.setOrderId(searchValue);

        Manufacture manufacture = new Manufacture();
        manufacture.setcOrder(order);
        List<Manufacture> manufactures = manufactureService.findManufacture(manufacture, pageModel);

        result.put("rows",manufactures);
        result.put("total",pageModel.getRecordCount());
        return result;
    }

    @RequestMapping("/search_manufacture_by_manufactureTechnologyName")
    @ResponseBody
    public Map<String,Object> search_manufacture_by_manufactureTechnologyName(String searchValue,PageModel pageModel){
        Map<String,Object> result = new HashMap<>();

        Technology technology = new Technology();
        technology.setTechnologyName(searchValue);

        Manufacture manufacture = new Manufacture();
        manufacture.setTechnology(technology);
        List<Manufacture> manufactures = manufactureService.findManufacture(manufacture, pageModel);

        result.put("rows",manufactures);
        result.put("total",pageModel.getRecordCount());
        return result;
    }

    //增加生产计划
    @RequestMapping("/add_judge")
    @ResponseBody
    public Map<String,Object> add_judge(){
        Map<String,Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        boolean b = subject.isPermitted("manufacture:add");
        if(!b){
            map.put("msg","禁止访问!");
        }
        return map;
    }

    @RequestMapping("/add")
    public String add(){
        return "manufacture_add";
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Map<String,Object> insert( @Valid Manufacture manufacture,Order cOder,Technology technology, BindingResult bindingResult){
        Map<String,Object> result = new HashMap<>();
        if(bindingResult.hasErrors()){
            return null;
        }

        Manufacture manufactureById = manufactureService.findManufactureById(manufacture.getManufactureSn());
        if(manufactureById != null){
            result.put("status",0);
            result.put("msg","该生产派工id已经存在");
            result.put("data",null);
        }else {
            manufacture.setcOrder(cOder);
            manufacture.setTechnology(technology);
            boolean b = manufactureService.insertManufacture(manufacture);
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

    //修改生产计划
    @RequestMapping("/edit_judge")
    @ResponseBody
    public Map<String,Object> edit_judge(){
        Map<String,Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        boolean b = subject.isPermitted("manufacture:edit");
        if(!b){
            map.put("msg","禁止访问!");
        }
        return map;
    }

    @RequestMapping("/edit")
    public String edit(){
        return "manufacture_edit";
    }

    @RequestMapping("/update_all")
    @ResponseBody
    public Map<String,Object> update_all( @Valid Manufacture manufacture,Order cOder,Technology technology,BindingResult bindingResult){
        Map<String,Object> result = new HashMap<>();
        if(bindingResult.hasErrors()){
            return null;
        }

        manufacture.setcOrder(cOder);
        manufacture.setTechnology(technology);
        boolean b = manufactureService.updateManufacture(manufacture);
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

    //删除生产计划
    @RequestMapping("/delete_judge")
    @ResponseBody
    public Map<String,Object> delete_judge(){
        Map<String,Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        boolean b = subject.isPermitted("manufacture:delete");
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
        boolean b = manufactureService.deleteManufacture(ids);
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
