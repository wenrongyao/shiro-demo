package com.honor.mapper.sys;

import com.honor.config.Mapper2;
import com.honor.model.sys.SysPermission;
import com.honor.model.sys.SysUserRole;

import java.util.List;

/**
 * Created by rongyaowen
 * on 2019/9/9.
 */
public interface SysPermissionMapper extends Mapper2<SysPermission> {
    List<SysPermission> selectSysPermissionsBySysUserName(String userName);

    List<SysPermission> selectMenusByRoleId(Integer id);
}
