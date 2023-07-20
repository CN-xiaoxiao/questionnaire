package com.xiaoxiao.login.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import com.xiaoxiao.utils.ResultUtils;
import com.xiaoxiao.utils.ResultVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 小程序登录的控制器
 */
@RestController
@RequestMapping("/wxapi/login")
public class LoginController {

    /**
     * 小程序登录
     * @param code
     * @return
     */
    @RequestMapping("/wxLogin")
    public ResultVo wxLogin(@RequestParam("code") String code) {
        Map<String, String> map = new HashMap<>();
        map.put("appid", "wxe683389ddbf28a54");
        map.put("secret", "6bbe7b9a1cb3619cb29a2d073f741426");
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");

        // 发送请求到微信接口服务端，获取openid
        String body = HttpRequest.get("https://api.weixin.qq.com/sns/jscode2session").form(map).body();
        JSONObject jsonObject = JSON.parseObject(body);
        return ResultUtils.success("获取成功！", jsonObject);
    }
}
