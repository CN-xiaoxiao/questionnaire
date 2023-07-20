package com.xiaoxiao.web.user.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统用户实体类
 */
@Data
public class SysUser implements Serializable {
    private Integer userId;
    private String username;
    private String password;
    private String phone;
    private String email;
}
