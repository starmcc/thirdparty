package com.starmcc.thirdparty.wechat.pay.service;

import com.starmcc.thirdparty.wechat.pay.config.WechatPayConfig;
import com.starmcc.thirdparty.wechat.pay.service.callback.WechatPayCallBack;

/**
 * @author qm
 * @date 2019/5/25 15:34
 * @Description 微信支付入口
 */
public class WechatPayInstance extends AbstractWechatPayImplementation {

    /**
     * 入口配置
     * @param wechatPayConfig
     * @param wechatPayCallBack
     */
    public WechatPayInstance(WechatPayConfig wechatPayConfig, WechatPayCallBack wechatPayCallBack) {
        super(wechatPayConfig, wechatPayCallBack);
    }
}
