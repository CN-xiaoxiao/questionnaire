package com.xiaoxiao.web.user.mapper;

import com.xiaoxiao.web.user.entity.SysUser;
import com.xiaoxiao.web.user.entity.UserParm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户的数据访问层
 */
@Mapper
public interface SysUserMapper {
    SysUser findUserByUsernameAndPwd(@Param("username") String username, @Param("password") String password);

    SysUser findUserByUserId(Integer userId);

    boolean save(SysUser sysUser);

    List<SysUser> selectAllByPage();

    List<SysUser> selectByPageAndCondition(UserParm userParm);

    boolean updateById(SysUser sysUser);

    boolean removeUserById(Integer userId);

    SysUser findUserByUsername(String username);
}
