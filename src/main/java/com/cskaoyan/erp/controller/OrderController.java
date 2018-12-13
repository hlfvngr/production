package com.cskaoyan.erp.controller;

import com.cskaoyan.erp.bean.Customer;
import com.cskaoyan.erp.bean.Order;
import com.cskaoyan.erp.bean.Product;
import com.cskaoyan.erp.service.CustomService;
import com.cskaoyan.erp.service.OrderService;
import com.cskaoyan.erp.service.ProductService;
import com.cskaoyan.erp.utils.PageModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("orderController")
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    //增加订单
    @RequestMapping("/add_judge")
    @ResponseBody
    public Map<String,Object> add_judge(){
        Map<String,Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        boolean b = subject.isPermitted("order:add");
        if(!b){
            map.put("msg","禁止访问!");
        }
        return map;
    }

    @RequestMapping("/add")
    public String add(){
        return "order_add";
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Map<String,Object> insert(@Valid Order order, Customer customer,Product product,
                                     MultipartFile multipartFile, BindingResult bindingResult){
        Map<String,Object> result = new HashMap<String, Object>();
        if(bindingResult.hasErrors()){
            return null;
        }
            //图片的处理
        order.setCustom(customer);
        order.setProduct(product);
        Order orderById = orderService.findOrderById(order.getOrderId());
        if(orderById != null){
            result.put("status",0);
            result.put("msg","该订单号已经存在!,请重新插入");
            result.put("data",null);
        }else {
            boolean ret = orderService.insertOrder(order);
            if(ret){
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



    //查找订单
    @RequestMapping("/get/${orderId}")
    @ResponseBody
    public Order get(@PathVariable String orderId){
        return orderService.findOrderById(orderId);
    }

    @RequestMapping("/get_data")
    @ResponseBody
    public List<Order> get_data(){
        return orderService.findAllOrder();
    }

    @RequestMapping("/find")
    public String find(){
        return "order_list";
    }

    @RequestMapping("/list")
    @ResponseBody//这里的分页查询,注意一下
    public Map<String,Object>  list(PageModel pageModel){
        Map<String,Object> result = new HashMap<String, Object>();

        Order order = new Order();
        List<Order> orders = orderService.findAllOrder(order, pageModel);

        result.put("rows",orders);
        result.put("total",pageModel.getRecordCount());
        return result;
    }

    @RequestMapping("/search_product_by_orderId")
    @ResponseBody
    public  Map<String,Object>  search_product_by_orderId(String searchValue, PageModel pageModel){
        Map<String,Object> result = new HashMap<String, Object>();

        Order order = new Order();
        order.setOrderId(searchValue);
        List<Order> orders = orderService.findAllOrder(order, pageModel);

        result.put("rows",orders);
        result.put("total",pageModel.getRecordCount());
        return result;
    }

    @RequestMapping("/search_product_by_orderCustom")
    @ResponseBody
    public   Map<String,Object> search_product_by_orderCustom(String searchValue, PageModel pageModel) {
        Map<String, Object> result = new HashMap<String, Object>();

        Customer customer = new Customer();
        customer.setCustomName(searchValue);

        Order order = new Order();
        order.setCustom(customer);
        List<Order> orders = orderService.findAllOrder(order, pageModel);

        result.put("rows", orders);
        result.put("total", pageModel.getRecordCount());
        return result;
    }

    @RequestMapping("/search_product_by_orderProduct")
    @ResponseBody//联合查询更好
    public  Map<String,Object> search_product_by_orderProduct(String searchValue, PageModel pageModel){
        Map<String,Object> result = new HashMap<String, Object>();

        Product product =new Product();
        product.setProductName(searchValue);

        Order order = new Order();
        order.setProduct(product);
        List<Order> orders = orderService.findAllOrder(order, pageModel);

        result.put("rows",orders);
        result.put("total",pageModel.getRecordCount());
        return result;
    }

    //更新订单
    @RequestMapping("/edit_judge")
    @ResponseBody
    public Map<String,Object> edit_judge(){
        Map<String,Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        boolean b = subject.isPermitted("order:edit");
        if(!b){
            map.put("msg","禁止访问!");
        }
        return map;
    }

    @RequestMapping("/edit")
    public String edit(){
        return "order_edit";
    }

    @RequestMapping("/update_note")
    @ResponseBody
    public Map<String,Object> update_note(@Valid Order order, Customer customer,Product product, BindingResult bindingResult){
        Map<String,Object> result = new HashMap<String, Object>();

        if(bindingResult.hasErrors()){
            return null;
        }
        order.setCustom(customer);
        order.setProduct(product);
        boolean ret = orderService.updateOrderNote(order);
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
    public Map<String,Object>  update_all( @Valid Order order,  Customer customer,Product product,
                                          MultipartFile multipartFile, BindingResult bindingResult){
        Map<String,Object> result = new HashMap<String, Object>();

        if(bindingResult.hasErrors()){
            return null;
        }
        order.setCustom(customer);
        order.setProduct(product);
        boolean ret = orderService.updateOrder(order);
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
    //删除订单
    @RequestMapping("/delete_judge")
    @ResponseBody
    public Map<String,Object> delete_judge(){
        Map<String,Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        boolean b = subject.isPermitted("order:delete");
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
        boolean ret = orderService.deleteOrder(ids);

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
