package com.starmcc.thirdparty.wechat.miniprogram.service.message;

import com.alibaba.fastjson.JSONObject;
import com.starmcc.thirdparty.wechat.miniprogram.config.WechatMiniprogramApiConstants;
import com.starmcc.thirdparty.wechat.miniprogram.config.WechatMiniprogramConfig;
import com.starmcc.thirdparty.wechat.miniprogram.http.WechatHttpTools;
import com.starmcc.thirdparty.wechat.miniprogram.service.callback.WechatMiniprogramCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qm
 * @date 2019/5/24 0:54
 * @Description 动态消息
 */
public abstract class AbstractMessageUpdatable extends AbstractMessageUniform {

    /**
     * 注入配置项
     *
     * @param wechatMiniprogramConfig   微信小程序配置
     * @param wechatMiniprogramCallBack 微信小程序获取access_token回调接口
     */
    protected AbstractMessageUpdatable(WechatMiniprogramConfig wechatMiniprogramConfig,
                                       WechatMiniprogramCallBack wechatMiniprogramCallBack) {
        super(wechatMiniprogramConfig, wechatMiniprogramCallBack);
    }

    @Override
    public Map<String, Object> createActivityId() {
        String accessToken = super.getAccessToken().getAccessToken();
        String authURL = WechatMiniprogramApiConstants.MESSAGE_CREATEACTIVITYID + "?access_token=" + accessToken;
        // 请求
        String resultJson = WechatHttpTools.post(authURL, null);
        return JSONObject.parseObject(resultJson);
    }

    @Override
    public Map<String, Object> setUpdatableMsg(String activityId, int targetState, Object templateInfo) {
        String accessToken = super.getAccessToken().getAccessToken();
        String authURL = WechatMiniprogramApiConstants.MESSAGE_SETUPDATABLEMSG + "?access_token=" + accessToken;
        Map<String, Object> sendGetParams = new HashMap<>(16);
        // 动态消息的 ID，通过 updatableMessage.createActivityId 接口获取
        sendGetParams.put("activity_id", activityId);
        // 动态消息修改后的状态（具体含义见后文）
        sendGetParams.put("target_state", targetState);
        // 动态消息对应的模板信息
        sendGetParams.put("template_info", templateInfo);
        // 请求
        String resultJson = WechatHttpTools.post(authURL, sendGetParams);
        return JSONObject.parseObject(resultJson);
    }
}
