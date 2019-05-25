package com.starmcc.thirdparty.wechat.miniprogram.service.auth;

import com.alibaba.fastjson.JSONObject;
import com.starmcc.thirdparty.wechat.miniprogram.config.WechatMiniprogramApiConstants;
import com.starmcc.thirdparty.wechat.miniprogram.WechatMiniprogram;
import com.starmcc.thirdparty.wechat.miniprogram.config.WechatMiniprogramConfig;
import com.starmcc.thirdparty.wechat.miniprogram.entity.AccessTokenResult;
import com.starmcc.thirdparty.wechat.miniprogram.http.WechatHttpTools;
import com.starmcc.thirdparty.wechat.miniprogram.service.callback.WechatMiniprogramCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qm
 * @date 2019/5/23 21:40
 * @Description 首层-access——token全局共享
 */
public abstract class AbstractEntrance implements WechatMiniprogram {

    /**
     * 配置
     */
    protected WechatMiniprogramConfig wechatMiniprogramConfig;
    private WechatMiniprogramCallBack wechatMiniprogramCallBack;

    /**
     * 注入配置项
     *
     * @param wechatMiniprogramConfig   微信小程序配置
     * @param wechatMiniprogramCallBack 微信小程序获取access_token回调接口
     */
    protected AbstractEntrance(WechatMiniprogramConfig wechatMiniprogramConfig,
                               WechatMiniprogramCallBack wechatMiniprogramCallBack) {
        this.wechatMiniprogramConfig = wechatMiniprogramConfig;
        this.wechatMiniprogramCallBack = wechatMiniprogramCallBack;
    }

    @Override
    public AccessTokenResult getAccessToken() {
        // 从调度者获取缓存中的token
        AccessTokenResult accessTokenResult = wechatMiniprogramCallBack.getCacheAccessToken();
        if (accessTokenResult != null && !"".equals(accessTokenResult.getAccessToken())) {
            return accessTokenResult;
        }
        Map<String, Object> sendGetParams = new HashMap<>(16);
        // 	填写 client_credential
        sendGetParams.put("grant_type", "client_credential");
        // 小程序唯一凭证，即 AppID，可在「微信公众平台 - 设置 - 开发设置」页中获得。（需要已经成为开发者，且帐号没有异常状态）
        sendGetParams.put("appid", wechatMiniprogramConfig.getAppid());
        // 小程序唯一凭证密钥，即 AppSecret，获取方式同 appid
        sendGetParams.put("secret", wechatMiniprogramConfig.getSecret());
        // 请求
        String resultJson = WechatHttpTools.get(WechatMiniprogramApiConstants.AUTH_GETACCESSTOKEN, sendGetParams);
        accessTokenResult = JSONObject.parseObject(resultJson, AccessTokenResult.class);
        // 回调新的accesstoken给调度者
        wechatMiniprogramCallBack.callBackAccessToken(accessTokenResult);
        return accessTokenResult;
    }

}
