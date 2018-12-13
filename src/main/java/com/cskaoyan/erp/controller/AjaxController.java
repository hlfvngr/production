package com.cskaoyan.erp.controller;

import com.cskaoyan.erp.bean.User;
import com.cskaoyan.erp.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AjaxController {

    @Autowired
    UserService userService;

    /*@Autowired
    SecurityManager securityManager;*/

    @RequestMapping("/ajaxLogin")
    @ResponseBody
    public Map<String,Object> login(@Valid User user, BindingResult bindingResult){
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword().toCharArray());
        /* SecurityUtils.setSecurityManager(securityManager);*/
        Subject subject = SecurityUtils.getSubject();
        Map<String, Object> map = new HashMap<>();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            map.put("msg","account_error");
            return map;
        }
        Session session = subject.getSession();
        PrincipalCollection attribute =
                (PrincipalCollection) session.getAttribute("org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY");
        User principal = (User) attribute.getPrimaryPrincipal();
        /*User userby = userService.findUserByUsername(user.getUsername());*/
        session.setAttribute("activeUser",principal);
        List<String> permissions = userService.findPermissionsByUsername(user.getUsername());
        session.setAttribute("sysPermissionList",permissions);
        map.put("msg","success");
        return map;
    }

}
