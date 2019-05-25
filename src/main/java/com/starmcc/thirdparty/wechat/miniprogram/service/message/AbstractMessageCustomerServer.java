package com.starmcc.thirdparty.wechat.miniprogram.service.message;


import com.alibaba.fastjson.JSONObject;
import com.starmcc.thirdparty.wechat.miniprogram.config.WechatMiniprogramApiConstants;
import com.starmcc.thirdparty.wechat.miniprogram.config.WechatMiniprogramConfig;
import com.starmcc.thirdparty.wechat.miniprogram.http.WechatHttpTools;
import com.starmcc.thirdparty.wechat.miniprogram.service.analysis.AbstractAnalysisVisitTrend;
import com.starmcc.thirdparty.wechat.miniprogram.service.callback.WechatMiniprogramCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qm
 * @date 2019/5/23 23:10
 * @Description 客服消息接口实现
 */
public abstract class AbstractMessageCustomerServer extends AbstractAnalysisVisitTrend implements MessageService {

    /**
     * 注入配置项
     *
     * @param wechatMiniprogramConfig   微信小程序配置
     * @param wechatMiniprogramCallBack 微信小程序获取access_token回调接口
     */
    protected AbstractMessageCustomerServer(WechatMiniprogramConfig wechatMiniprogramConfig,
                                            WechatMiniprogramCallBack wechatMiniprogramCallBack) {
        super(wechatMiniprogramConfig, wechatMiniprogramCallBack);
    }

    @Override
    public Map<String, Object> setTyping(String touser, String command) {
        String accessToken = super.getAccessToken().getAccessToken();
        String authURL = WechatMiniprogramApiConstants.MESSAGE_SETTYPING + "?access_token=" + accessToken;
        Map<String, Object> sendGetParams = new HashMap<>(16);
        // 用户的 OpenID
        sendGetParams.put("touser", touser);
        // 命令 Typing对用户下发"正在输入"状态  CancelTyping取消对用户的"正在输入"状态
        sendGetParams.put("command", command);
        // 请求
        String resultJson = WechatHttpTools.post(authURL, sendGetParams);
        return JSONObject.parseObject(resultJson);
    }

    @Override
    public Map<String, Object> customerServiceSend(String touser, String msgtype,
                                                   String content, Object image,
                                                   Object link, Object miniprogrampage) {
        String accessToken = super.getAccessToken().getAccessToken();
        String authURL = WechatMiniprogramApiConstants.MESSAGE_CUSTOMERSERVICESEND + "?access_token=" + accessToken;
        Map<String, Object> sendGetParams = new HashMap<>(16);
        // 用户的 OpenID
        sendGetParams.put("touser", touser);
        // 消息类型
        sendGetParams.put("msgtype", msgtype);
        // 文本消息内容，msgtype="text" 时必填
        if (content != null) {
            sendGetParams.put("content", content);
        }
        // 图片消息，msgtype="image" 时必填
        if (image != null) {
            sendGetParams.put("image", image);
        }
        // 图片消息，msgtype="link" 时必填
        if (link != null) {
            sendGetParams.put("link", link);
        }
        // 小程序卡片，msgtype="miniprogrampage" 时必填
        if (miniprogrampage != null) {
            sendGetParams.put("miniprogrampage", miniprogrampage);
        }
        // 请求
        String resultJson = WechatHttpTools.post(authURL, sendGetParams);
        return JSONObject.parseObject(resultJson);
    }
}
