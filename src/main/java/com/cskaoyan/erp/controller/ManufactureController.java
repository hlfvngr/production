package com.cskaoyan.erp.controller;

import com.cskaoyan.erp.bean.Manufacture;
import com.cskaoyan.erp.bean.Order;
import com.cskaoyan.erp.bean.Technology;
import com.cskaoyan.erp.service.ManufactureService;
import com.cskaoyan.erp.service.OrderService;
import com.cskaoyan.erp.utils.PageModel;
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
        result.put("total",manufactures.size());
        return result;
    }

    @RequestMapping("/search_manufacture_by_manufactureSn")
    @ResponseBody
    public Map<String,Object> search_manufacture_by_manufactureSn(String searchValue,PageModel pageModel){
        Map<String,Object> result = new HashMap<>();

        Manufacture manufacture = new Manufacture();
        manufacture.setManufactureSn(searchValue);
        List<Manufacture> manufactures = manufactureService.findManufacture(manufacture, pageModel);

        result.put("rows",manufactures);
        result.put("total",manufactures.size());
        return result;
    }

    @RequestMapping("/search_manufacture_by_manufactureOrderId")
    @ResponseBody
    public Map<String,Object> search_manufacture_by_manufactureOrderId(String searchValue,PageModel pageModel){
        Map<String,Object> result = new HashMap<>();

        Order order = new Order();
        order.setOrderId(searchValue);

        Manufacture manufacture = new Manufacture();
        manufacture.setOrder(order);
        List<Manufacture> manufactures = manufactureService.findManufacture(manufacture, pageModel);

        result.put("rows",manufactures);
        result.put("total",manufactures.size());
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
        result.put("total",manufactures.size());
        return result;
    }

    //增加生产计划
    @RequestMapping("/add_judge")
    @ResponseBody
    public void add_judge(){}

    @RequestMapping("/add")
    public String add(){
        return "manufacture_add";
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Map<String,Object> insert(@RequestBody @Valid Manufacture manufacture, BindingResult bindingResult){
        Map<String,Object> result = new HashMap<>();
        if(bindingResult.hasErrors()){
            return null;
        }
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
        return result;

    }

    //修改生产计划
    @RequestMapping("/edit_judge")
    @ResponseBody
    public void edit_judge(){}

    @RequestMapping("/edit")
    public String edit(){
        return "manufacture_edit";
    }

    @RequestMapping("/update_all")
    @ResponseBody
    public Map<String,Object> update_all(@RequestBody @Valid Manufacture manufacture,BindingResult bindingResult){
        Map<String,Object> result = new HashMap<>();
        if(bindingResult.hasErrors()){
            return null;
        }
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
    public void delete_judge(){}

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
