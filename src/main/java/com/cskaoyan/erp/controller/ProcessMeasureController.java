package com.cskaoyan.erp.controller;

import com.cskaoyan.erp.bean.ProcessMeasureCheck;
import com.cskaoyan.erp.service.ProcessMeasureService;
import com.cskaoyan.erp.utils.PageModel;
import com.cskaoyan.erp.bean.vo.*;
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
public class ProcessMeasureController {

    @Autowired
    ProcessMeasureService processMeasureService;

    @RequestMapping("/p_measure_check/find")
    public String find(HttpSession session) {

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("pMeasureCheck:add");
        arrayList.add("pMeasureCheck:edit");
        arrayList.add("pMeasureCheck:delete");
        session.setAttribute("sysPermissionList", arrayList);
        return "p_measure_check_list";
    }


//    @RequestMapping("/technologyPlan/get_data")

    @ResponseBody
    @RequestMapping("/p_measure_check/list")
    public HashMap<String, Object> list(PageModel pageModel) {

        Integer total = processMeasureService.count();

        HashMap<String, Object> hashMap = new HashMap<String, Object>();

        if (total == 0) {
            hashMap.put("total", 0);
            hashMap.put("rows", "");
        } else {

            pageModel.setRecordCount(total);

            List<ProcessMeasureListVO> processMeasureListVOs = processMeasureService.searchByPageAndRows(pageModel);

            hashMap.put("total", total);
            hashMap.put("rows", processMeasureListVOs);
        }
        return hashMap;
    }

    @ResponseBody
    @RequestMapping("/pMeasureCheck/add_judge")
    public HashMap add_judge(@Valid ProcessMeasureCheck finalMeasureCheck, BindingResult bindingResult) {

        HashMap<String, Object> hashMap = new HashMap<String, Object>();

        hashMap.put("msg", null);
        return hashMap;

    }

    @RequestMapping("/p_measure_check/add")
    public String add() {
        return "p_measure_check_add";
    }

    @ResponseBody
    @RequestMapping("/p_measure_check/insert")
    public CheckVO insert(ProcessMeasureCheck processMeasureCheck, BindingResult bindingResult) {

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

            processMeasureService.insert(processMeasureCheck);
            checkVO.setStatus(200);
            checkVO.setMsg("OK");
        }

        return checkVO;
    }

    @ResponseBody
    @RequestMapping("/pMeasureCheck/edit_judge")
    public HashMap edit_judge(@Valid ProcessMeasureCheck processMeasureCheck, BindingResult bindingResult) {

        HashMap<String, Object> hashMap = new HashMap<String, Object>();

        hashMap.put("msg", null);
        return hashMap;

    }

    @RequestMapping("/p_measure_check/edit")
    public String edit() {
        return "p_measure_check_edit";
    }


    /*@RequestMapping("/process/get_data")*/

    @ResponseBody
    @RequestMapping("/p_measure_check/update_all")
    public CheckVO update_all(ProcessMeasureCheck processMeasureCheck, BindingResult bindingResult) {

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

            processMeasureService.update(processMeasureCheck);
            checkVO.setStatus(200);
            checkVO.setMsg("OK");
        }

        return checkVO;
    }


    @ResponseBody
    @RequestMapping("/pMeasureCheck/delete_judge")
    public HashMap delete_judge(@Valid ProcessMeasureCheck finalMeasureCheck, BindingResult bindingResult) {

        HashMap<String, Object> hashMap = new HashMap<String, Object>();

        hashMap.put("msg", null);
        return hashMap;

    }

    @ResponseBody
    @RequestMapping("/p_measure_check/delete_batch")
    public CheckVO delete_batch(String[] ids) {

        CheckVO checkVO = new CheckVO();

        processMeasureService.deleteByIds(ids);

        checkVO.setStatus(200);
        checkVO.setMsg("OK");

        return checkVO;

    }

    @ResponseBody
    @RequestMapping("/p_measure_check/search_pMeasureCheck_by_pMeasureCheckId")
    public HashMap search(String searchValue, PageModel pageModel) {

        HashMap hashMap = new HashMap();

        Integer total = processMeasureService.searchCountByPMeasureId(searchValue);
        if (total == 0) {
            hashMap.put("total", 0);
            hashMap.put("rows", "");
        } else {
            pageModel.setRecordCount(total);

            List<ProcessMeasureListVO> processMeasureListVOS = processMeasureService.searchByPMeasureId(searchValue, pageModel);

            hashMap.put("total", total);
            hashMap.put("rows", processMeasureListVOS);
        }

        return hashMap;
    }

}
