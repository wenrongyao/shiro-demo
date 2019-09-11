package com.honor.service.sys;

import com.honor.mapper.sys.SysPermissionMapper;
import com.honor.model.sys.SysPermission;
import com.honor.service.base.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rongyaowen
 * on 2019/9/10.
 */
@Service
public class SysPermissionServiceImpl extends AbstractBaseService<SysPermission> implements ISysPermissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public List<SysPermission> selectSysPermissionsBySysUserName(String userName) {
        List<SysPermission> sysPermissions = sysPermissionMapper.selectSysPermissionsBySysUserName(userName);
        return sysPermissions;
    }
}
