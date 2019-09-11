package com.honor.realm;

import com.honor.mapper.sys.SysPermissionMapper;
import com.honor.mapper.sys.SysRoleMapper;
import com.honor.mapper.sys.SysUserMapper;
import com.honor.mapper.sys.SysUserRoleMapper;
import com.honor.model.sys.SysPermission;
import com.honor.model.sys.SysRole;
import com.honor.model.sys.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by rongyaowen
 * on 2019/9/4.
 * 自定义Realm
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    /**
     * 授权==>执行isPermitted()或注解( @RequiresPermissions()/@RequiresRoles())进入
     * 这个方法将角色和权限加载到info对象中
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        //根据用户名查询出用户记录
        Example sysUserExample = new Example(SysUser.class);
        Example.Criteria criteria = sysUserExample.createCriteria();
        criteria.andEqualTo("userName", userName);
        SysUser user = sysUserMapper.selectOneByExample(sysUserExample);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<SysRole> roleList = sysRoleMapper.selectRolesByUserId(user.getId());

        Set<String> roles = new HashSet<>();
        if (roleList.size() > 0) {
            for (SysRole role : roleList) {
                roles.add(role.getRoleName());
                //根据角色id查询所有资源
                List<SysPermission> menuList = sysPermissionMapper.selectMenusByRoleId(role.getId());
                for (SysPermission menu : menuList) {
                    info.addStringPermission(menu.getPermissionName()); // 添加权限
                }
            }
        }
        info.setRoles(roles);
        return info;
    }

    /**
     * 认证==》subject.login(token)
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        Example sysUserExample = new Example(SysUser.class);
        Example.Criteria criteria = sysUserExample.createCriteria();
        criteria.andEqualTo("userName", userName);
        SysUser user = sysUserMapper.selectByExample(sysUserExample).get(0);
        if (user != null) {
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), "xxx");
            return authcInfo;
        } else {
            return null;
        }
    }

}
