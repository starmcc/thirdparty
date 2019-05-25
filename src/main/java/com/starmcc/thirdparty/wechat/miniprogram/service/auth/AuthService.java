package com.starmcc.thirdparty.wechat.miniprogram.service.auth;

import com.starmcc.thirdparty.wechat.miniprogram.entity.Code2SessionResult;

import java.util.Map;

/**
 * Copyright © 2019qm工作室. All rights reserved.
 *
 * @author qm
 * @date 2019/5/23 20:40
 * @Description 授权认证接口
 */
public interface AuthService {

    /**
     * 登录凭证校验。
     * 通过 wx.login() 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程。
     * 更多使用方法详见 小程序登录。
     * https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/login.html
     *
     * @param jsCode 登录时获取的 code
     * @return Code2SessionResult 小程序授权返回消息
     */
    Code2SessionResult code2Session(String jsCode);

    /**
     * 用户支付完成后，获取该用户的 UnionId，无需用户授权。本接口支持第三方平台代理查询。
     * 注意：调用前需要用户完成支付，且在支付后的五分钟内有效。
     *
     * @param openid        支付用户唯一标识
     * @param transactionId 微信支付订单号
     * @param mchId         微信支付分配的商户号，和商户订单号配合使用
     * @param outTradeNo    微信支付商户订单号，和商户号配合使用
     * @return
     */
    Map<String,Object> getPaidUnionId(String openid, String transactionId, String mchId, String outTradeNo);

}
