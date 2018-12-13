package com.cskaoyan.erp.controller;

import com.cskaoyan.erp.bean.User;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.PermissionCollection;

@Controller
public class FormController {

    //配合internalResourcesViewResolver进行使用
    @RequestMapping("/{formName}")
    public String loginForm(@PathVariable String formName, HttpSession session){

        //如果通过认证
       /* if("home".equals(formName)){
            PrincipalCollection attribute =
                    (PrincipalCollection) session.getAttribute("org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY");
            User principal = (User) attribute.getPrimaryPrincipal();
            session.setAttribute("activeUser",principal);
        }*/

        // 动态跳转页面
        return formName;
    }
}
