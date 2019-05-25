package com.starmcc.thirdparty.wechat.miniprogram.service.auth;

import com.alibaba.fastjson.JSONObject;
import com.starmcc.thirdparty.wechat.miniprogram.config.WechatMiniprogramApiConstants;
import com.starmcc.thirdparty.wechat.miniprogram.config.WechatMiniprogramConfig;
import com.starmcc.thirdparty.wechat.miniprogram.entity.Code2SessionResult;
import com.starmcc.thirdparty.wechat.miniprogram.http.WechatHttpTools;
import com.starmcc.thirdparty.wechat.miniprogram.service.callback.WechatMiniprogramCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qm
 * @date 2019/5/23 11:28
 * @Description 登录、用户信息、接口调用凭证
 */
public abstract class AbstractAuth extends AbstractEntrance {


    /**
     * 注入配置项
     *
     * @param wechatMiniprogramConfig   微信小程序配置
     * @param wechatMiniprogramCallBack 微信小程序获取access_token回调接口
     */
    protected AbstractAuth(WechatMiniprogramConfig wechatMiniprogramConfig,
                           WechatMiniprogramCallBack wechatMiniprogramCallBack) {
        super(wechatMiniprogramConfig, wechatMiniprogramCallBack);
    }

    @Override
    public Code2SessionResult code2Session(String jsCode) {
        Map<String, Object> sendGetParams = new HashMap<>(16);
        sendGetParams.put("appid", wechatMiniprogramConfig.getAppid());
        sendGetParams.put("secret", wechatMiniprogramConfig.getSecret());
        sendGetParams.put("js_code", jsCode);
        sendGetParams.put("grant_type", "authorization_code");
        // 请求
        String resultJson = WechatHttpTools.get(WechatMiniprogramApiConstants.AUTH_CODE2SESSION, sendGetParams);
        return JSONObject.parseObject(resultJson, Code2SessionResult.class);
    }


    @Override
    public Map<String, Object> getPaidUnionId(String openid, String transactionId, String mchId, String outTradeNo) {
        String accessToken = this.getAccessToken().getAccessToken();
        String authURL = WechatMiniprogramApiConstants.AUTH_GETPAIDUNIONID + "?access_token=" + accessToken;
        Map<String, Object> sendGetParams = new HashMap<>(16);
        // 支付用户唯一标识
        sendGetParams.put("openid", openid);
        if (transactionId != null) {
            // 微信支付订单号
            sendGetParams.put("transaction_id", transactionId);
        }
        if (mchId != null) {
            // 微信支付分配的商户号，和商户订单号配合使用
            sendGetParams.put("mch_id", mchId);
        }
        if (outTradeNo != null) {
            // 微信支付商户订单号，和商户号配合使用
            sendGetParams.put("out_trade_no", outTradeNo);
        }
        // 请求
        String resultJson = WechatHttpTools.get(authURL, sendGetParams);
        return JSONObject.parseObject(resultJson);
    }

}
