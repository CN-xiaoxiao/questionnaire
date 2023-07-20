package com.xiaoxiao.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.xiaoxiao.*.*.mapper")
public class MybatisConfig {

}
