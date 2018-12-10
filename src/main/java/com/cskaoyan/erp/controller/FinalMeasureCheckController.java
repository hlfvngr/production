package com.cskaoyan.erp.controller;

import com.cskaoyan.erp.bean.FinalMeasureCheck;
import com.cskaoyan.erp.service.FinalMeasureCheckService;
import com.cskaoyan.erp.utils.PageModel;
import com.cskaoyan.erp.bean.vo.CheckVO;
import com.cskaoyan.erp.bean.vo.FinalMeasureListVO;
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
public class FinalMeasureCheckController {

    @Autowired
    FinalMeasureCheckService finalMeasureCheckService;

    @RequestMapping("/measure/find")
    public String find(HttpSession session) {

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("fMeasureCheck:add");
        arrayList.add("fMeasureCheck:edit");
        arrayList.add("fMeasureCheck:delete");
        session.setAttribute("sysPermissionList", arrayList);
        return "measurement_list";
    }

    @ResponseBody
    @RequestMapping("/measure/list")
    public HashMap<String, Object> list(PageModel pageModel) {

        Integer total = finalMeasureCheckService.count();

        HashMap<String, Object> hashMap = new HashMap<String, Object>();

        if (total == 0) {
            hashMap.put("total", 0);
            hashMap.put("rows", "");
        } else {

            pageModel.setRecordCount(total);

            ArrayList<FinalMeasureListVO> finalMeasureListVOs = finalMeasureCheckService.findByPageAndRows(pageModel);

            hashMap.put("total", total);
            hashMap.put("rows", finalMeasureListVOs);
        }
        return hashMap;
    }

    @ResponseBody
    @RequestMapping("/fMeasureCheck/add_judge")
    public HashMap add_judge(@Valid FinalMeasureCheck finalMeasureCheck, BindingResult bindingResult) {

        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        
        hashMap.put("msg", null);
        return hashMap;

    }

    @RequestMapping("/measure/add")
    public String add() {
        return "measurement_add";
    }

    @ResponseBody
    @RequestMapping("/measure/insert")
    public CheckVO insert(FinalMeasureCheck finalMeasureCheck, BindingResult bindingResult) {

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

            finalMeasureCheckService.insert(finalMeasureCheck);
            checkVO.setStatus(200);
            checkVO.setMsg("OK");
        }

        return checkVO;
    }

    @ResponseBody
    @RequestMapping("/fMeasureCheck/edit_judge")
    public HashMap edit_judge(@Valid FinalMeasureCheck finalMeasureCheck, BindingResult bindingResult) {

        HashMap<String, Object> hashMap = new HashMap<String, Object>();

        hashMap.put("msg", null);
        return hashMap;

    }

    @RequestMapping("/measure/edit")
    public String edit() {
        return "measurement_edit";
    }

    @ResponseBody
    @RequestMapping("/measure/update_all")
    public CheckVO update_all(FinalMeasureCheck finalMeasureCheck, BindingResult bindingResult) {

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

            finalMeasureCheckService.update(finalMeasureCheck);
            checkVO.setStatus(200);
            checkVO.setMsg("OK");
        }

        return checkVO;
    }

    @ResponseBody
    @RequestMapping("/fMeasureCheck/delete_judge")
    public HashMap delete_judge(@Valid FinalMeasureCheck finalMeasureCheck, BindingResult bindingResult) {

        HashMap<String, Object> hashMap = new HashMap<String, Object>();

        hashMap.put("msg", null);
        return hashMap;

    }

    @ResponseBody
    @RequestMapping("/measure/delete_batch")
    public CheckVO delete_batch(String[] ids) {

        CheckVO checkVO = new CheckVO();

        finalMeasureCheckService.deleteByIds(ids);

        checkVO.setStatus(200);
        checkVO.setMsg("OK");

        return checkVO;

    }

    @ResponseBody
    @RequestMapping("/measure/search_fMeasureCheck_by_fMeasureCheckId")
    public HashMap search(String searchValue, PageModel pageModel) {

        HashMap hashMap = new HashMap();

        Integer total = finalMeasureCheckService.searchCountByMeasureId(searchValue);
        if (total == 0) {
            hashMap.put("total", 0);
            hashMap.put("rows", "");
        } else {
            pageModel.setRecordCount(total);

            List<FinalMeasureListVO> finalMeasureListVOs = finalMeasureCheckService.searchByMeasureId(searchValue, pageModel);

            hashMap.put("total", total);
            hashMap.put("rows", finalMeasureListVOs);
        }

        return hashMap;
    }

    @ResponseBody
    @RequestMapping("/measure/search_fMeasureCheck_by_orderId")
    public HashMap searchByPName(String searchValue, PageModel pageModel) {

        HashMap hashMap = new HashMap();

        Integer total = finalMeasureCheckService.searchCountByOrderId(searchValue);
        if (total == 0) {
            hashMap.put("total", 0);
            hashMap.put("rows", "");
        } else {
            pageModel.setRecordCount(total);

            List<FinalMeasureListVO> finalMeasureListVOs = finalMeasureCheckService.searchByOrderId(searchValue, pageModel);

            hashMap.put("total", total);
            hashMap.put("rows", finalMeasureListVOs);
        }

        return hashMap;
    }
}
