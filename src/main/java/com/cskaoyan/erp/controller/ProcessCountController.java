package com.cskaoyan.erp.controller;

import com.cskaoyan.erp.bean.ProcessCountCheck;
import com.cskaoyan.erp.service.ProcessCountService;
import com.cskaoyan.erp.utils.PageModel;
import com.cskaoyan.erp.bean.vo.CheckVO;
import com.cskaoyan.erp.bean.vo.ProcessCountListVO;
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
 * Created by Administrator on 2018/12/8
 */
@Controller
public class ProcessCountController {

    @Autowired
    ProcessCountService processCountService;

    @RequestMapping("/p_count_check/find")
    public String find(HttpSession session) {

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("pCountCheck:add");
        arrayList.add("pCountCheck:edit");
        arrayList.add("pCountCheck:delete");
        session.setAttribute("sysPermissionList", arrayList);
        return "p_count_check_list";
    }


    @ResponseBody
    @RequestMapping("/p_count_check/list")
    public HashMap<String, Object> list(PageModel pageModel) {

        Integer total = processCountService.count();

        HashMap<String, Object> hashMap = new HashMap<String, Object>();

        if (total == 0) {
            hashMap.put("total", 0);
            hashMap.put("rows", "");
        } else {

            pageModel.setRecordCount(total);

            List<ProcessCountListVO> processCountListVOS = processCountService.searchByPageAndRows(pageModel);

            hashMap.put("total", total);
            hashMap.put("rows", processCountListVOS);
        }
        return hashMap;
    }

    @ResponseBody
    @RequestMapping("/pCountCheck/add_judge")
    public HashMap add_judge(@Valid ProcessCountCheck processCountCheck, BindingResult bindingResult) {

        HashMap<String, Object> hashMap = new HashMap<String, Object>();

        hashMap.put("msg", null);
        return hashMap;

    }

    @RequestMapping("/p_count_check/add")
    public String add() {
        return "p_count_check_add";
    }

    @ResponseBody
    @RequestMapping("/p_count_check/insert")
    public CheckVO insert(ProcessCountCheck processCountCheck, BindingResult bindingResult) {

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

            processCountService.insert(processCountCheck);
            checkVO.setStatus(200);
            checkVO.setMsg("OK");
        }

        return checkVO;
    }

    @ResponseBody
    @RequestMapping("/pCountCheck/edit_judge")
    public HashMap edit_judge(@Valid ProcessCountCheck processCountCheck, BindingResult bindingResult) {

        HashMap<String, Object> hashMap = new HashMap<String, Object>();

        hashMap.put("msg", null);
        return hashMap;

    }

    @RequestMapping("/p_count_check/edit")
    public String edit() {
        return "p_count_check_edit";
    }


    @ResponseBody
    @RequestMapping("/p_count_check/update_all")
    public CheckVO update_all(ProcessCountCheck processCountCheck, BindingResult bindingResult) {

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

            processCountService.update(processCountCheck);
            checkVO.setStatus(200);
            checkVO.setMsg("OK");
        }

        return checkVO;
    }


    @ResponseBody
    @RequestMapping("/pCountCheck/delete_judge")
    public HashMap delete_judge(@Valid ProcessCountCheck processCountCheck, BindingResult bindingResult) {

        HashMap<String, Object> hashMap = new HashMap<String, Object>();

        hashMap.put("msg", null);
        return hashMap;

    }

    @ResponseBody
    @RequestMapping("/p_count_check/delete_batch")
    public CheckVO delete_batch(String[] ids) {

        CheckVO checkVO = new CheckVO();

        processCountService.deleteByIds(ids);

        checkVO.setStatus(200);
        checkVO.setMsg("OK");

        return checkVO;

    }

    @ResponseBody
    @RequestMapping("/p_count_check/search_pCountCheck_by_pCountCheckId")
    public HashMap search(String searchValue, PageModel pageModel) {

        HashMap hashMap = new HashMap();

        Integer total = processCountService.searchCountByPCountId(searchValue);
        if (total == 0) {
            hashMap.put("total", 0);
            hashMap.put("rows", "");
        } else {
            pageModel.setRecordCount(total);

            List<ProcessCountListVO> processCountListVOS = processCountService.searchByPCountId(searchValue, pageModel);

            hashMap.put("total", total);
            hashMap.put("rows", processCountListVOS);
        }

        return hashMap;
    }
}
