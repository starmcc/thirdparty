package com.starmcc.thirdparty.wechat.miniprogram.http;

import com.starmcc.thirdparty.http.TpHttpTools;

import java.util.Map;

/**
 * @Author: qm
 * @Date: 2019/5/26 0:25
 * @Description: 微信http工具封装
 */
public class WechatHttpTools {

    /**
     * 禁止实例化
     */
    private WechatHttpTools(){}

    /**
     * 发送post请求
     * @param url
     * @param params
     * @return
     */
    public static String post(String url, Map<String, Object> params) {
        return TpHttpTools.post(url, params, 3000, 3000, "UTF-8");
    }

    /**
     * 发送get请求
     * @param url
     * @param params
     * @return
     */
    public static String get(String url, Map<String, Object> params) {
        return TpHttpTools.get(url, params, 3000, 3000, "UTF-8");
    }

}
