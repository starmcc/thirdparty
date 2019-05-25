package com.starmcc.thirdparty.wechat.miniprogram.config;

/**
 * @author qm
 * @date 2019/5/23 10:50
 * @Description 微信小程序配置表
 */
public class WechatMiniprogramConfig {
    /**
     * 小程序appid
     */
    private String appid;
    /**
     * 小程序秘钥secret
     */
    private String secret;

    private WechatMiniprogramConfig() {
    }

    public WechatMiniprogramConfig(String appid, String secret) {
        this.appid = appid;
        this.secret = secret;
    }

    public String getAppid() {
        return appid;
    }

    public String getSecret() {
        return secret;
    }
}
