package com.starmcc.thirdparty.constants;

/**
 * @author qm
 * @date 2019/5/23 12:02
 * @Description 常量表
 */
public class ThirdpartyWechatConstants {

    /**
     * appid 节点
     */
    public static final String WECHAT_MINIPROGRAM_APPID_NODE = "wechat.miniprogram.appid";
    /**
     * secret 节点
     */
    public static final String WECHAT_MINIPROGRAM_SECRET_NODE = "wechat.miniprogram.secret";

    /**
     * 调用微信支付的appid
     */
    public static final String WECHAT_PAY_APPID = "wechat.pay.appid";
    /**
     * 商户号 微信商户平台-微信支付分配的商户号
     */
    public static final String WECHAT_PAY_MCHID = "wechat.pay.mch_id";
    /**
     * 微信商户平台-支付的key
     */
    public static final String WECHAT_PAY_MCHKEY = "wechat.pay.mch_key";
    /**
     * 通知地址	notify_url 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
     */
    public static final String WECHAT_PAY_NOTIFYURL = "wechat.pay.notify_url";
    /**
     * 支付类型
     * JSAPI--JSAPI支付（或小程序支付）、
     * NATIVE--Native支付、
     * APP--app支付，
     * MWEB--H5支付，
     * 不同trade_type决定了调起支付的方式
     */
    public static final String WECHAT_PAY_TRADETYPE = "wechat.pay.trade_type";


}
