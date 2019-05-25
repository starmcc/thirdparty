package com.starmcc.thirdparty.wechat.miniprogram.service.callback;

import com.starmcc.thirdparty.wechat.miniprogram.entity.AccessTokenResult;

/**
 * @Author: qm
 * @Date: 2019/5/26 1:44
 * @Description: 微信小程序access_token全局缓存回调接口
 */
public interface WechatMiniprogramCallBack {

    /**
     * 从缓存中获取access_token对象
     * @return 返回access_token 返回null则表示需要获取新的access_token对象
     */
    AccessTokenResult getCacheAccessToken();

    /**
     * 底层获取了新的access_token时回调该接口
     * @param accessTokenResult 新的accessTokenResult对象
     */
    void callBackAccessToken(AccessTokenResult accessTokenResult);

}
