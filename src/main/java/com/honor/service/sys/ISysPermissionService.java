package com.honor.service.sys;

import com.honor.model.sys.SysPermission;
import com.honor.service.base.IBaseService;

import java.util.List;

/**
 * Created by rongyaowen
 * on 2019/9/10.
 */
public interface ISysPermissionService extends IBaseService<SysPermission> {

    /**
     * 根据用户名获取权限
     *
     * @param userName
     * @return
     */
    List<SysPermission> selectSysPermissionsBySysUserName(String userName);
}
