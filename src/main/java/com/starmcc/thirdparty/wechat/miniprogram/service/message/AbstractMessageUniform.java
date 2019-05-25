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
 * @date 2019/5/24 0:46
 * @Description 统一服务消息
 */
public abstract class AbstractMessageUniform extends AbstractMessageTemplate {

    /**
     * 注入配置项
     *
     * @param wechatMiniprogramConfig   微信小程序配置
     * @param wechatMiniprogramCallBack 微信小程序获取access_token回调接口
     */
    protected AbstractMessageUniform(WechatMiniprogramConfig wechatMiniprogramConfig,
                                     WechatMiniprogramCallBack wechatMiniprogramCallBack) {
        super(wechatMiniprogramConfig, wechatMiniprogramCallBack);
    }

    @Override
    public Map<String, Object> uniformSend(String touser, Object weappTemplateMsg, Object mpTemplateMsg) {
        String accessToken = super.getAccessToken().getAccessToken();
        String authURL = WechatMiniprogramApiConstants.MESSAGE_UNIFORMSEND + "?access_token=" + accessToken;
        Map<String, Object> sendGetParams = new HashMap<>(16);
        // 用户openid，可以是小程序的openid，也可以是mp_template_msg.appid对应的公众号的openid
        sendGetParams.put("touser", touser);
        // 小程序模板消息相关的信息，可以参考小程序模板消息接口; 有此节点则优先发送小程序模板消息
        if (weappTemplateMsg != null) {
            sendGetParams.put("weapp_template_msg", weappTemplateMsg);
        }
        // 公众号模板消息相关的信息，可以参考公众号模板消息接口；有此节点并且没有weapp_template_msg节点时，发送公众号模板消息
        sendGetParams.put("mp_template_msg", mpTemplateMsg);
        // 请求
        String resultJson = WechatHttpTools.post(authURL, sendGetParams);
        return JSONObject.parseObject(resultJson);
    }
}
