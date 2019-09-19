package com.honor.controller;

import com.honor.model.sys.SysPermission;
import com.honor.model.sys.SysUser;
import com.honor.service.sys.ISysPermissionService;
import com.honor.service.sys.ISysUserService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.Map;

/**
 * Created by rongyaowen
 * on 2019/9/4.
 */
@Controller
@RequestMapping("/user")
@SessionAttributes({"sysUser", "isSysPermissionList", "sysPermissionList"})
public class UserController {

    @Autowired
    private ISysPermissionService sysPermissionService;
    @Autowired
    private ISysUserService sysUserService;

    /**
     * 主页面
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 登录页面
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 登录操作
     *
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping("/tologin")
    @ResponseBody
    public Map<String, Object> login(Model model, String userName, String password) {
        Map<String, Object> map = new HashedMap();
        if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)) {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            token.setRememberMe(true);
            try {
                subject.login(token);
            } catch (AuthenticationException e) {
                e.printStackTrace();
                map.put("code", -1);
                map.put("msg", "登录失败, 用户名或密码错误");
                return map;
            }
            // 获取角色对应的权限
            List<SysPermission> sysPermissionList = sysPermissionService.selectSysPermissionsBySysUserName(userName);
            if (sysPermissionList != null && sysPermissionList.size() != 0) {
                model.addAttribute("sysPermissionList", sysPermissionList);
                model.addAttribute("isSysPermissionList", true);
            } else {
                model.addAttribute("isSysPermissionList", false);
            }
            // 当前登录用户
            SysUser sysUser = new SysUser();
            sysUser.setUserName(userName);
            model.addAttribute("sysUser", sysUserService.selectOne(sysUser));
            map.put("code", 1);
            return map;
        } else {
            map.put("code", -1);
            map.put("msg", "用户名与密码不能为空");
            return map;
        }
    }

    /**
     * 用户管理
     *
     * @return
     */
    @RequestMapping("/usermanage")
    @RequiresPermissions({"用户管理"})
    public String userManage() {
        // 可以手动判断是否拥有权限，也可以通过注解配合ControllerAdvice
//        Subject subject = SecurityUtils.getSubject();
//        subject.isPermitted("用户管理");

        return "usermanage";
    }

    /**
     * 角色管理
     *
     * @return
     */
    @RequestMapping("/rolemanage")
    @RequiresPermissions({"角色管理"})
    public String roleManage() {
        return "rolemanage";
    }

    /**
     * 权限管理
     *
     * @return
     */
    @RequestMapping("/permissionmanage")
    @RequiresPermissions({"权限管理"})
    public String permissionManage() {
        return "permissionmanage";
    }

    /**
     * 密码修改
     *
     * @return
     */
    @RequestMapping("/changepassword")
    @RequiresPermissions({"密码修改"})
    public String changePassword() {
        return "changepassword";
    }

}
