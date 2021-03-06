package com.cskaoyan.erp.controller;

import com.cskaoyan.erp.bean.Product;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("productController")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //增加商品
    @RequestMapping("/add_judge")
    @ResponseBody
    public Map<String,Object> add_judge(){
        Map<String,Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        boolean b = subject.isPermitted("product:add");
        if(!b){
            map.put("msg","禁止访问!");
        }
        return map;
    }

    @RequestMapping("/add")
    public String add(){
        return "product_add";
    }

    @RequestMapping("/insert")//图片还没有处理
    @ResponseBody
    public Map<String,Object> insert(@Valid Product product, HttpSession session, BindingResult bindingResult){
        Map<String,Object> result = new HashMap<String, Object>();
        if(bindingResult.hasErrors()){
            return null;
        }
        //multipartFile
        Product productById = productService.findProductById(product.getProductId());
        if(productById != null){
            result.put("status",0);
            result.put("msg","该商品id已经存在");
            result.put("data",null);
        }else {
            String imgs = (String) session.getAttribute("imgs");
            //赋值完成后将imgs清空
            session.setAttribute("imgs","");

            product.setImage(imgs);

            boolean b = productService.insertProduct(product);
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
    //修改商品
    @RequestMapping("/edit_judge")
    @ResponseBody
    public Map<String,Object> edit_judge(){
        Map<String,Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        boolean b = subject.isPermitted("product:edit");
        if(!b){
            map.put("msg","禁止访问!");
        }
        return map;
    }

    @RequestMapping("/edit")
    public String edit(){
        return "product_edit";
    }

    @RequestMapping("/update_note")
    @ResponseBody
    public Map<String,Object> update_note(@Valid Product product,BindingResult bindingResult){
        Map<String,Object> result = new HashMap<String, Object>();
        //合法性校验
        if(bindingResult.hasErrors()){
            return null;
        }
        boolean ret = productService.updateProductNote(product);
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
    public Map<String,Object> update_all( @Valid Product product,HttpSession session,BindingResult bindingResult){
        Map<String,Object> result = new HashMap<String, Object>();
        if(bindingResult.hasErrors()){
            return null;
        }

        String imgs = (String) session.getAttribute("imgs");
        String deleteImg = (String) session.getAttribute("deleteImg");

        String[] split = deleteImg.split(",");
        String image = product.getImage();
        for (String str : split) {
            if(image.startsWith(str)){
                image.replace(str,"");
            }else {
                image.replace("," + str,"");
            }
        }

        //赋值完成后将imgs清空
        session.setAttribute("imgs","");
        session.setAttribute("deleteImg","");

        product.setImage(imgs + "" + image);

        boolean b = productService.updateProduct(product);
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

    //查找商品
    @RequestMapping("/find")
    public String find(){
        return "product_list";
    }

    @RequestMapping("/list")
    @ResponseBody//这里的分页查询,注意一下
    public Map<String,Object> list(PageModel pageModel){
        Map<String,Object> result = new HashMap<String, Object>();
        Product product = new Product();
        List<Product> products = productService.findAllProduct(product, pageModel);
        result.put("rows",products);
        result.put("total",pageModel.getRecordCount());
        return result;
    }

    //获得所有商品的信息
    @RequestMapping("/get_data")
    @ResponseBody
    public List<Product> get_data(){
        return productService.findAllProduct();
    }

    @RequestMapping("/get/{productId}")
    @ResponseBody
    public Product get(@PathVariable String productId){
        Product product = productService.findProductById(productId);
        return product;
    }

    @RequestMapping("/search_product_by_productId")
    @ResponseBody
    public  Map<String,Object> search_product_by_productId(String searchValue, PageModel pageModel){
        Map<String,Object> result = new HashMap<String, Object>();
        Product product = new Product();
        product.setProductId(searchValue);
        List<Product> products = productService.findAllProduct(product, pageModel);
        result.put("rows",products);
        result.put("total",pageModel.getRecordCount());
        return result;
    }

    @RequestMapping("/search_product_by_productName")
    @ResponseBody
    public Map<String,Object> search_product_by_productName(String searchValue, PageModel pageModel){
        Map<String,Object> result = new HashMap<String, Object>();
        Product product = new Product();
        product.setProductName(searchValue);
        List<Product> products = productService.findAllProduct(product, pageModel);
        result.put("rows",products);
        result.put("total",pageModel.getRecordCount());
        return result;
    }

    @RequestMapping("/search_product_by_productType")
    @ResponseBody
    public Map<String,Object> search_product_by_productType(String searchValue, PageModel pageModel){
        Map<String,Object> result = new HashMap<String, Object>();
        Product product = new Product();
        product.setProductType(searchValue);
        List<Product> products = productService.findAllProduct(product, pageModel);
        result.put("rows",products);
        result.put("total",pageModel.getRecordCount());
        return result;
    }

    //删除商品
    @RequestMapping("/delete_judge")
    @ResponseBody
    public Map<String,Object> delete_judge(){
        Map<String,Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        boolean b = subject.isPermitted("product:delete");
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
        //验证合法性
        boolean ret = productService.deleteProduct(ids);
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
