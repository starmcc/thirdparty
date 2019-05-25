package com.starmcc.thirdparty.factory;

import com.starmcc.thirdparty.constants.ThirdpartyWechatConstants;
import com.starmcc.thirdparty.file.TpReadTools;
import com.starmcc.thirdparty.wechat.miniprogram.WechatMiniprogram;
import com.starmcc.thirdparty.wechat.miniprogram.config.WechatMiniprogramConfig;
import com.starmcc.thirdparty.wechat.miniprogram.service.WechatMiniprogramInstance;
import com.starmcc.thirdparty.wechat.miniprogram.service.callback.WechatMiniprogramCallBack;
import com.starmcc.thirdparty.wechat.pay.WechatPay;
import com.starmcc.thirdparty.wechat.pay.config.WechatPayConfig;
import com.starmcc.thirdparty.wechat.pay.service.WechatPayInstance;
import com.starmcc.thirdparty.wechat.pay.service.callback.WechatPayCallBack;

/**
 * @author qm
 * @date 2019/5/24 17:33
 * @Description 微信体系
 */
public class TpWechat {

    /**
     * 创建微信小程序实例(依赖配置文件中创建)
     * @Param wechatMiniprogramCallBack 微信小程序access_token全局缓存回调接口
     * @return 返回微信小程序实例
     */
    public static WechatMiniprogram createMiniprogram(WechatMiniprogramCallBack wechatMiniprogramCallBack) {
        TpReadTools tpReadTools = new TpReadTools();
        return new WechatMiniprogramInstance(
                new WechatMiniprogramConfig(
                        tpReadTools.get(ThirdpartyWechatConstants.WECHAT_MINIPROGRAM_APPID_NODE),
                        tpReadTools.get(ThirdpartyWechatConstants.WECHAT_MINIPROGRAM_SECRET_NODE)
                ), wechatMiniprogramCallBack);
    }

    /**
     * 创建微信小程序实例(依赖自定义配置创建)
     *
     * @param wechatMiniprogramConfig   微信小程序配置类
     * @param wechatMiniprogramCallBack 微信小程序access_token全局缓存回调接口
     * @return 返回微信小程序实例
     */
    public static WechatMiniprogram createMiniprogram(WechatMiniprogramConfig wechatMiniprogramConfig,
                                                      WechatMiniprogramCallBack wechatMiniprogramCallBack) {
        return new WechatMiniprogramInstance(wechatMiniprogramConfig, wechatMiniprogramCallBack);
    }

    /**
     * 创建微信支付实例(依赖配置文件创建)
     *
     * @param wechatPayCallBack 回调对象
     * @return
     */
    public static WechatPay createPaySample(WechatPayCallBack wechatPayCallBack) {
        TpReadTools tpReadTools = new TpReadTools();
        return new WechatPayInstance(new WechatPayConfig(
                tpReadTools.get(ThirdpartyWechatConstants.WECHAT_PAY_APPID),
                tpReadTools.get(ThirdpartyWechatConstants.WECHAT_PAY_MCHID),
                tpReadTools.get(ThirdpartyWechatConstants.WECHAT_PAY_MCHKEY),
                tpReadTools.get(ThirdpartyWechatConstants.WECHAT_PAY_NOTIFYURL),
                WechatPayConfig.WechatTradeType.getType(
                        tpReadTools.get(ThirdpartyWechatConstants.WECHAT_PAY_TRADETYPE)
                )
        ), wechatPayCallBack);
    }


    /**
     * 创建微信支付实例(依赖自定义配置创建)
     *
     * @param wechatPayConfig   微信支付配置类
     * @param wechatPayCallBack 回调对象
     * @return
     */
    public static WechatPay createPaySample(WechatPayConfig wechatPayConfig, WechatPayCallBack wechatPayCallBack) {
        return new WechatPayInstance(wechatPayConfig, wechatPayCallBack);
    }


}
