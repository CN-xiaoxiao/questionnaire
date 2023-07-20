package com.xiaoxiao.web.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoxiao.web.user.mapper.SysUserMapper;
import com.xiaoxiao.web.user.entity.SysUser;
import com.xiaoxiao.web.user.entity.UserParm;
import com.xiaoxiao.web.user.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements ISysUserService {
    @Autowired
    SysUserMapper userMapper;

    @Override
    public boolean save(SysUser sysUser) {
        return userMapper.save(sysUser);
    }

    @Override
    public boolean updateById(SysUser sysUser) {
        return userMapper.updateById(sysUser);
    }

    @Override
    public boolean removeById(Integer userId) {
        return userMapper.removeUserById(userId);
    }

    @Override
    public SysUser findUserByUsername(SysUser sysUser) {

        return userMapper.findUserByUsername(sysUser.getUsername());
    }

    @Override
    public SysUser findUserByUserId(Integer userId) {
        return userMapper.findUserByUserId(userId);
    }

    @Override
    public PageInfo<SysUser> selectUserInfoList(UserParm userParm) {
        //1.分页的开始页的分页设置,分页一定要至少传递这两个参数
        PageHelper.startPage(userParm.getCurrentPage(), userParm.getPageSize());
        //2.紧跟分页设置的后的第一个select查询会被分页查询
        List<SysUser> users = userMapper.selectAllByPage();
        //3.PageInfo参数navigatepage（导航页，显示的页码）：默认显示5个连续页,页码导航连续显示的页数5
        return new PageInfo<SysUser>(users,10);

    }
    @Override
    public PageInfo<SysUser> selectUserInfoListByCondition(UserParm userParm) {
        //1.分页助手开始分页
        PageHelper.startPage(userParm.getCurrentPage(), userParm.getPageSize());
        //2.调用dao层的select查询方法，第一个select方法会被分页
        List<SysUser> sysUsers = userMapper.selectByPageAndCondition(userParm);
        //3。封装分页结果到PageInfo中
        return new PageInfo<>(sysUsers, 10);
    }
}
