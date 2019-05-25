package com.starmcc.thirdparty.wechat.pay.service.callback;

import java.util.Map;

/**
 * @author qm
 * @date 2019/5/24 21:30
 * @Description 微信支付回调接口 - 成功 - 失败
 */
public interface WechatPayCallBack {

    /**
     * 支付成功
     *
     * @param resultMap 微信返回的全部消息,已将XML转换成Map对象
     * @return 如果返回false,则不通知微信已收到支付成功通知！
     */
    boolean paySuccess(Map<String, String> resultMap);

    /**
     * 支付失败
     *
     * @param resultMap 微信返回的全部消息,已将XML转换成Map对象
     */
    void payError(Map<String, String> resultMap);

}
