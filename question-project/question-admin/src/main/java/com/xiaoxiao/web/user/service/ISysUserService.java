package com.xiaoxiao.web.user.service;

import com.github.pagehelper.PageInfo;
import com.xiaoxiao.web.user.entity.SysUser;
import com.xiaoxiao.web.user.entity.UserParm;

public interface ISysUserService {
    boolean save(SysUser sysUser);

    boolean updateById(SysUser sysUser);

    boolean removeById(Integer userId);

    PageInfo<SysUser> selectUserInfoList(UserParm userParm);

    SysUser findUserByUsername(SysUser sysUser);

    SysUser findUserByUserId(Integer userId);

    PageInfo<SysUser> selectUserInfoListByCondition(UserParm userParm);
}
