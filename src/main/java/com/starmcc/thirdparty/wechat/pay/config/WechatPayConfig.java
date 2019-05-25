package com.starmcc.thirdparty.wechat.pay.config;

/**
 * @author qm
 * @date 2019/5/24 10:27
 * @Description 小程序支付配置表
 */
public class WechatPayConfig {

    /**
     * 微信分配的小程序ID
     */
    private String appid;
    /**
     * 商户号 微信商户平台-微信支付分配的商户号
     */
    private String mchId;
    /**
     * 微信商户平台-支付的key
     */
    private String mchKey;
    /**
     * 通知地址	notify_url 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
     */
    private String notifyUrl;
    /**
     * 支付类型
     * JSAPI--JSAPI支付（或小程序支付）、
     * NATIVE--Native支付、
     * APP--app支付，
     * MWEB--H5支付，
     * 不同trade_type决定了调起支付的方式，请根据支付产品正确上传
     */
    private WechatTradeType wechatTradeType;

    private WechatPayConfig() {

    }

    public WechatPayConfig(String appid,
                           String mchId,
                           String mchkey,
                           String notifyUrl,
                           WechatTradeType wechatTradeType) {
        this.appid = appid;
        this.mchId = mchId;
        this.mchKey = mchkey;
        this.notifyUrl = notifyUrl;
        this.wechatTradeType = wechatTradeType;
    }

    public WechatTradeType getWechatTradeType() {
        return wechatTradeType;
    }


    public String getAppid() {
        return appid;
    }


    public String getMchId() {
        return mchId;
    }


    public String getMchKey() {
        return mchKey;
    }


    public String getNotifyUrl() {
        return notifyUrl;
    }


    public enum WechatTradeType {
        /**
         * JSAPI--JSAPI支付（或小程序支付）
         */
        JSAPI,
        /**
         * NATIVE--Native支付
         */
        NATIVE,

        /**
         * APP--app支付
         */
        APP,

        /**
         * MWEB--H5支付
         */
        MWEB;

        private WechatTradeType() {
        }


        /**
         * 获取对应的常量
         *
         * @param name 名称
         * @return
         */
        public static WechatTradeType getType(String name) {
            WechatTradeType[] values = WechatTradeType.values();
            for (WechatTradeType value : values) {
                if (value.name().equals(name)) {
                    return value;
                }
            }
            return null;
        }

    }

}
