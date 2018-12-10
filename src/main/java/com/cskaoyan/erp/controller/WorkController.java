package com.cskaoyan.erp.controller;

import com.cskaoyan.erp.bean.Device;
import com.cskaoyan.erp.bean.Process;
import com.cskaoyan.erp.bean.Product;
import com.cskaoyan.erp.bean.Work;
import com.cskaoyan.erp.service.DeviceListService;
import com.cskaoyan.erp.service.ProductService;
import com.cskaoyan.erp.service.WorkService;
import com.cskaoyan.erp.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/work")
public class WorkController {

    @Autowired
    WorkService workService;

    //查找作业
    @RequestMapping("/find")
    public String find(){
        return "/work_list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(PageModel pageModel) {
        Map<String,Object> result = new HashMap<>();

        Work work = new Work();
        List<Work> works = workService.selectWork(work,pageModel);

        result.put("rows",works);
        result.put("total",works.size());
        return result;
    }

    @RequestMapping("/get/${workId}")
    @ResponseBody
    public Work get(@PathVariable String wordId){
        return workService.findWorkById(wordId);
    }

    @RequestMapping("/get_data")
    @ResponseBody
    public List<Work> get_data(){
        return workService.findAllWork();
    }

    @RequestMapping("/search_work_by_workId")
    @ResponseBody
    public Map<String,Object> search_work_by_workId(String searchValue,PageModel pageModel){
        Map<String,Object> result = new HashMap<>();

        Work work  = new Work();
        work.setWorkId(searchValue);
        List<Work> works = workService.selectWork(work, pageModel);

        result.put("rows",works);
        result.put("total",works.size());
        return result;
    }

    @RequestMapping("/search_work_by_workProduct")
    @ResponseBody
    public Map<String,Object> search_work_by_workProduct(String searchValue,PageModel pageModel){
        Map<String,Object> result = new HashMap<>();

        Product product = new Product();
        product.setProductName(searchValue);

        Work work  = new Work();
        work.setWorkProduct(product);
        List<Work> works = workService.selectWork(work, pageModel);

        result.put("rows",works);
        result.put("total",works.size());
        return result;
    }

    @RequestMapping("/search_work_by_workDevice")
    @ResponseBody
    public Map<String,Object> search_work_by_workDevice(String searchValue,PageModel pageModel){
        Map<String,Object> result = new HashMap<>();

        Device device = new Device();
        device.setDeviceName(searchValue);

        Work work  = new Work();
        work.setWorkDevice(device);
        List<Work> works = workService.selectWork(work, pageModel);

        result.put("rows",works);
        result.put("total",works.size());
        return result;
    }

    @RequestMapping("/search_work_by_workProcess")
    @ResponseBody
    public Map<String,Object> search_work_by_workProcess(String searchValue,PageModel pageModel){
        Map<String,Object> result = new HashMap<>();

        Process process = new Process();
        process.setProcessId(searchValue);

        Work work  = new Work();
        work.setWorkProcess(process);
        List<Work> works = workService.selectWork(work, pageModel);

        result.put("rows",works);
        result.put("total",works.size());
        return result;
    }

    //增加作业
    @RequestMapping("/add_judge")
    @ResponseBody
    public void add_judge(){}

    @RequestMapping("/add")
    public String add(){
        return "work_add";
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Map<String,Object> insert(@RequestBody @Valid Work work, BindingResult bindingResult){
        Map<String,Object> result = new HashMap<>();
        if(bindingResult.hasErrors()){
            return null;
        }
        boolean ret = workService.insertWork(work);
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

    //修改作业
    @RequestMapping("/edit_judge")
    @ResponseBody
    public void edit_judge(){}

    @RequestMapping("/edit")
    public String edit(){
        return "work_edit";
    }

    @RequestMapping("/update_all")
    @ResponseBody
    public Map<String,Object> update_all(@RequestBody Work work ,BindingResult bindingResult){
        Map<String,Object> result = new HashMap<>();
        if(bindingResult.hasErrors()){
            return null;
        }
        boolean ret = workService.updateWork(work);
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

    //删除作业
    @RequestMapping("/delete_judge")
    @ResponseBody
    public void delete_judge(){}

    @RequestMapping("/delete_batch")
    @ResponseBody
    public Map<String,Object> delete_batch(@RequestBody @RequestParam("ids")String ids_str){
        Map<String,Object> result = new HashMap<>();

        String[] ids = ids_str.split(",");
        boolean ret = workService.deleteWork(ids);

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
