package com.cskaoyan.erp.controller;

import com.cskaoyan.erp.bean.UnqualifyApply;
import com.cskaoyan.erp.service.UnqualifyApplyService;
import com.cskaoyan.erp.utils.PageModel;
import com.cskaoyan.erp.bean.vo.CheckVO;
import com.cskaoyan.erp.bean.vo.UnqualifyListVO;
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
@RequestMapping("/unqualify")
public class UnqualifyApplyController {

    @Autowired
    UnqualifyApplyService unqualifyApplyService;

    @RequestMapping("/find")
    public String find(HttpSession session) {

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("unqualify:add");
        arrayList.add("unqualify:edit");
        arrayList.add("unqualify:delete");
        session.setAttribute("sysPermissionList", arrayList);
        return "unqualify_list";
    }

    @ResponseBody
    @RequestMapping("/list")
    public HashMap<String, Object> list(PageModel pageModel) {

        Integer total = unqualifyApplyService.count();

        /*ArrayList<UnqualifyApply> unqualifyApplies = unqualifyApplyService.findByPageAndRows();

        Integer limit = total - (page - 1) * rows < rows ? total - (page - 1) * rows : rows;
        List<UnqualifyApply> unqualifyApplies1 = unqualifyApplies.subList((page - 1) * 10, (page - 1) * 10 + limit);*/

        HashMap<String, Object> hashMap = new HashMap<String, Object>();

        if (total == 0) {
            hashMap.put("total", 0);
            hashMap.put("rows", "");
        } else {

            pageModel.setRecordCount(total);

            ArrayList<UnqualifyListVO> unqualifyListVo = unqualifyApplyService.findByPageAndRows(pageModel);

            hashMap.put("total", total);
            hashMap.put("rows", unqualifyListVo);
        }
        return hashMap;
    }

    @ResponseBody
    @RequestMapping("/add_judge")
    public HashMap add_judge(@Valid UnqualifyApply unqualifyApply, BindingResult bindingResult) {

        HashMap<String, Object> hashMap = new HashMap<String, Object>();

        /*if (bindingResult.hasFieldErrors()) {
            FieldError unqualifyApplyId = bindingResult.getFieldError("unqualifyApplyId");
            String defaultMessage = unqualifyApplyId.getDefaultMessage();
            hashMap = new HashMap<String, Object>();
            hashMap.put("msg", defaultMessage);
        }*/

        hashMap.put("msg", null);
        return hashMap;

    }

    @RequestMapping("/add")
    public String add() {
        return "unqualify_add";
    }

    @ResponseBody
    @RequestMapping("/insert")
    public CheckVO insert(UnqualifyApply unqualifyApply, BindingResult bindingResult) {

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

            unqualifyApplyService.insert(unqualifyApply);
            checkVO.setStatus(200);
            checkVO.setMsg("OK");
        }

        return checkVO;
    }

    @ResponseBody
    @RequestMapping("/edit_judge")
    public HashMap edit_judge(@Valid UnqualifyApply unqualifyApply, BindingResult bindingResult) {

        HashMap<String, Object> hashMap = new HashMap<String, Object>();

        /*if (bindingResult.hasFieldErrors()) {
            FieldError unqualifyApplyId = bindingResult.getFieldError("unqualifyApplyId");
            String defaultMessage = unqualifyApplyId.getDefaultMessage();
            hashMap = new HashMap<String, Object>();
            hashMap.put("msg", defaultMessage);
        }*/

        hashMap.put("msg", null);
        return hashMap;

    }

    @RequestMapping("/edit")
    public String edit() {
        return "unqualify_edit";
    }

    @ResponseBody
    @RequestMapping("/update_all")
    public CheckVO update_all(UnqualifyApply unqualifyApply, BindingResult bindingResult) {

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

            unqualifyApplyService.update(unqualifyApply);
            checkVO.setStatus(200);
            checkVO.setMsg("OK");
        }

        return checkVO;
    }

    @ResponseBody
    @RequestMapping("/delete_judge")
    public HashMap delete_judge(@Valid UnqualifyApply unqualifyApply, BindingResult bindingResult) {

        HashMap<String, Object> hashMap = new HashMap<String, Object>();

        hashMap.put("msg", null);
        return hashMap;

    }

    @ResponseBody
    @RequestMapping("/delete_batch")
    public CheckVO delete_batch(String[] ids) {

        CheckVO checkVO = new CheckVO();

        unqualifyApplyService.deleteByIds(ids);

        checkVO.setStatus(200);
        checkVO.setMsg("OK");

        return checkVO;

    }

    @ResponseBody
    @RequestMapping("/search_unqualify_by_unqualifyId")
    public HashMap search(String searchValue, PageModel pageModel) {

        HashMap hashMap = new HashMap();

        Integer total = unqualifyApplyService.searchCountByUnqualifyId(searchValue);
        if (total == 0) {
            hashMap.put("total", 0);
            hashMap.put("rows", "");
        } else {
            pageModel.setRecordCount(total);

            List<UnqualifyListVO> unqualifyListVos = unqualifyApplyService.searchByUnqualifyId(searchValue, pageModel);

            hashMap.put("total", total);
            hashMap.put("rows", unqualifyListVos);
        }

        return hashMap;
    }

    @ResponseBody
    @RequestMapping("/search_unqualify_by_productName")
    public HashMap searchByPName(String searchValue, PageModel pageModel) {

        HashMap hashMap = new HashMap();

        Integer total = unqualifyApplyService.searchCountByProductName(searchValue);
        if (total == 0) {
            hashMap.put("total", 0);
            hashMap.put("rows", "");
        } else {
            pageModel.setRecordCount(total);

            List<UnqualifyListVO> unqualifyListVos = unqualifyApplyService.searchByProductName(searchValue, pageModel);

            hashMap.put("total", total);
            hashMap.put("rows", unqualifyListVos);
        }

        return hashMap;
    }
}
