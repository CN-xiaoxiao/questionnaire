package com.xiaoxiao.web.user.controller;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoxiao.utils.ResultUtils;
import com.xiaoxiao.utils.ResultVo;
import com.xiaoxiao.utils.SaltMD5Util;
import com.xiaoxiao.web.user.entity.SysUser;
import com.xiaoxiao.web.user.entity.UserParm;
import com.xiaoxiao.web.user.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理 控制器
 */
@RestController
@RequestMapping("/api/user")
public class SysUserController {
    @Autowired
    private ISysUserService sysUserService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public ResultVo login(@RequestBody SysUser sysUser) {
        String username = sysUser.getUsername();
        String password = sysUser.getPassword();

        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
            return ResultUtils.error("用户名或密码不能为空！",403);
        }

//        sysUser.setPassword(SaltMD5Util.generateSaltPassword(password));
        SysUser user = sysUserService.findUserByUsername(sysUser);

        if (user == null) {
            return ResultUtils.error("登录失败！用户名或密码错误！",403);
        }

        boolean passwordFlag = SaltMD5Util.verifySaltPassword(password, user.getPassword());

        if (!passwordFlag) {
            return ResultUtils.error("登录失败！用户名或密码错误",403);
        }

        return ResultUtils.success("登录成功！", user.getUserId());
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/getInfo")
    public ResultVo getUserInfo(Integer userId) {
        SysUser user = sysUserService.findUserByUserId(userId);

        return ResultUtils.success("查询成功!", user.getUsername());
    }

    /**
     * 新增用户
     */
    @PostMapping
    public ResultVo add(@RequestBody SysUser sysUser) {
        // 密码加密
        sysUser.setPassword(SaltMD5Util.generateSaltPassword(sysUser.getPassword()));
        boolean save = sysUserService.save(sysUser);

        if (save) {
            return ResultUtils.success("新增用户成功！");
        }

        return ResultUtils.error("新增用户失败！");
    }

    /**
     * 编辑用户
     */
    @PutMapping
    public ResultVo edit(@RequestBody SysUser sysUser) {
        boolean flag = sysUserService.updateById(sysUser);

        if (flag) {
            return ResultUtils.success("编辑用户成功！");
        }

        return ResultUtils.error("编辑用户失败！");
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{userId}")
    public ResultVo delete(@PathVariable("userId") Integer userId) {
        boolean flag = sysUserService.removeById(userId);

        if (flag) {
            return ResultUtils.success("删除用户成功！");
        }

        return ResultUtils.error("删除用户失败！");
    }

    /**
     * 列表查询
     */
//    @GetMapping("/list")
//    public ResultVo getList(UserParm userParm) {
//        PageInfo<SysUser> userPageInfo = sysUserService.selectUserInfoList(userParm);
//
//        return ResultUtils.success("查询成功", userPageInfo);
//    }

    /**
     *
     * @param userParm
     * @return
     */
    @GetMapping("/list")
    public ResultVo getListByCondition(UserParm userParm) {
        PageInfo<SysUser> userPageInfo = sysUserService.selectUserInfoListByCondition(userParm);

        return ResultUtils.success("查询成功", userPageInfo);
    }
}
