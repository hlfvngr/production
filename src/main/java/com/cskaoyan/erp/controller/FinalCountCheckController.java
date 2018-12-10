package com.cskaoyan.erp.controller;

import com.cskaoyan.erp.bean.FinalCountCheck;
import com.cskaoyan.erp.service.FinalCountCheckService;
import com.cskaoyan.erp.utils.PageModel;
import com.cskaoyan.erp.bean.vo.CheckVO;
import com.cskaoyan.erp.bean.vo.FinalCountListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/12/6
 */
@Controller
public class FinalCountCheckController {

    @Autowired
    FinalCountCheckService finalCountCheckService;

    @RequestMapping("/f_count_check/find")
    public String find(HttpSession session) {

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("fCountCheck:add");
        arrayList.add("fCountCheck:edit");
        arrayList.add("fCountCheck:delete");
        session.setAttribute("sysPermissionList", arrayList);
        return "f_count_check_list";
    }

    @ResponseBody
    @RequestMapping("/f_count_check/list")
    public HashMap<String, Object> list(PageModel pageModel) {

        Integer total = finalCountCheckService.count();

        HashMap<String, Object> hashMap = new HashMap<String, Object>();

        if (total == 0) {
            hashMap.put("total", 0);
            hashMap.put("rows", "");
        } else {

            pageModel.setRecordCount(total);

            ArrayList<FinalCountListVO> finalCountListVOs = finalCountCheckService.findByPageAndRows(pageModel);

            hashMap.put("total", total);
            hashMap.put("rows", finalCountListVOs);
        }
        return hashMap;
    }

    @ResponseBody
    @RequestMapping("/fCountCheck/add_judge")
    public HashMap add_judge(@Valid FinalCountCheck finalCountCheck, BindingResult bindingResult) {

        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        
        hashMap.put("msg", null);
        return hashMap;

    }

    @RequestMapping("/f_count_check/add")
    public String add() {
        return "f_count_check_add";
    }

    @ResponseBody
    @RequestMapping("/f_count_check/insert")
    public CheckVO insert(FinalCountCheck finalCountCheck, BindingResult bindingResult) {

        CheckVO checkVO = new CheckVO();

        if (bindingResult.hasFieldErrors()) {

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            String defaultMessage = null;

            for (FieldError f : fieldErrors) {
                if (f != null) {
                    defaultMessage = f.getDefaultMessage();
                    break;
                }
            }
            checkVO.setMsg(defaultMessage);

        } else {

            finalCountCheckService.insert(finalCountCheck);
            checkVO.setStatus(200);
            checkVO.setMsg("OK");
        }

        return checkVO;
    }

    @ResponseBody
    @RequestMapping("/fCountCheck/edit_judge")
    public HashMap edit_judge(@Valid FinalCountCheck finalCountCheck, BindingResult bindingResult) {

        HashMap<String, Object> hashMap = new HashMap<String, Object>();

        hashMap.put("msg", null);
        return hashMap;

    }

    @RequestMapping("/f_count_check/edit")
    public String edit() {
        return "f_count_check_edit";
    }

    @ResponseBody
    @RequestMapping("/f_count_check/update_all")
    public CheckVO update_all(FinalCountCheck finalCountCheck, BindingResult bindingResult) {

        CheckVO checkVO = new CheckVO();

        if (bindingResult.hasFieldErrors()) {

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            String defaultMessage = null;

            for (FieldError f : fieldErrors) {
                if (f != null) {
                    defaultMessage = f.getDefaultMessage();
                    break;
                }
            }
            checkVO.setMsg(defaultMessage);

        } else {

            finalCountCheckService.update(finalCountCheck);
            checkVO.setStatus(200);
            checkVO.setMsg("OK");
        }

        return checkVO;
    }

    @ResponseBody
    @RequestMapping("/fCountCheck/delete_judge")
    public HashMap delete_judge(@Valid FinalCountCheck finalCountCheck, BindingResult bindingResult) {

        HashMap<String, Object> hashMap = new HashMap<String, Object>();

        hashMap.put("msg", null);
        return hashMap;

    }

    @ResponseBody
    @RequestMapping("/f_count_check/delete_batch")
    public CheckVO delete_batch(String[] ids) {

        CheckVO checkVO = new CheckVO();

        finalCountCheckService.deleteByIds(ids);

        checkVO.setStatus(200);
        checkVO.setMsg("OK");

        return checkVO;

    }

    @ResponseBody
    @RequestMapping("/f_count_check/search_fCountCheck_by_fCountCheckId")
    public HashMap search(String searchValue, PageModel pageModel) {

        HashMap hashMap = new HashMap();

        Integer total = finalCountCheckService.searchCountByCountId(searchValue);
        if (total == 0) {
            hashMap.put("total", 0);
            hashMap.put("rows", "");
        } else {
            pageModel.setRecordCount(total);

            List<FinalCountListVO> finalCountListVOs = finalCountCheckService.searchByCountId(searchValue, pageModel);

            hashMap.put("total", total);
            hashMap.put("rows", finalCountListVOs);
        }

        return hashMap;
    }

    @ResponseBody
    @RequestMapping("/f_count_check/search_fCountCheck_by_orderId")
    public HashMap searchByOId(String searchValue, PageModel pageModel) {

        HashMap hashMap = new HashMap();

        Integer total = finalCountCheckService.searchCountByOrderId(searchValue);
        if (total == 0) {
            hashMap.put("total", 0);
            hashMap.put("rows", "");
        } else {
            pageModel.setRecordCount(total);

            List<FinalCountListVO> finalCountListVOs = finalCountCheckService.searchByOrderId(searchValue, pageModel);

            hashMap.put("total", total);
            hashMap.put("rows", finalCountListVOs);
        }

        return hashMap;
    }
}
