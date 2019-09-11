package com.honor.mapper.sys;

import com.honor.config.Mapper2;
import com.honor.model.sys.SysRole;
import com.honor.model.sys.SysUserRole;

import java.util.List;

/**
 * Created by rongyaowen
 * on 2019/9/9.
 */
public interface SysRoleMapper extends Mapper2<SysRole> {
    List<SysRole> selectRolesByUserId(Integer id);
}
