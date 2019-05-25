package com.starmcc.thirdparty.wechat.miniprogram.service;

import com.starmcc.thirdparty.wechat.miniprogram.config.WechatMiniprogramConfig;
import com.starmcc.thirdparty.wechat.miniprogram.service.callback.WechatMiniprogramCallBack;
import com.starmcc.thirdparty.wechat.miniprogram.service.message.AbstractMessageUpdatable;

/**
 * @author qm
 * @date 2019/5/23 21:02
 * @Description 小程序入口实例化
 */
public class WechatMiniprogramInstance extends AbstractMessageUpdatable {


    /**
     * 注入配置项
     *
     * @param wechatMiniprogramConfig   微信小程序配置
     * @param wechatMiniprogramCallBack 微信小程序获取access_token回调接口
     */
    public WechatMiniprogramInstance(WechatMiniprogramConfig wechatMiniprogramConfig,
                                     WechatMiniprogramCallBack wechatMiniprogramCallBack) {
        super(wechatMiniprogramConfig, wechatMiniprogramCallBack);
    }
}
