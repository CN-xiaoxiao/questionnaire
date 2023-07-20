package com.xiaoxiao.web.user.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserParm implements Serializable {
    // 当前第几页
    private Integer currentPage;
    // 页容量
    private Integer pageSize;
    // 查询条件 用户名
    private String username;
}
