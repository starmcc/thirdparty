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
 * @date 2019/5/23 23:39
 * @Description 模板消息
 */
public abstract class AbstractMessageTemplate extends AbstractMessageCustomerServer {


    /**
     * 注入配置项
     *
     * @param wechatMiniprogramConfig   微信小程序配置
     * @param wechatMiniprogramCallBack 微信小程序获取access_token回调接口
     */
    protected AbstractMessageTemplate(WechatMiniprogramConfig wechatMiniprogramConfig,
                                      WechatMiniprogramCallBack wechatMiniprogramCallBack) {
        super(wechatMiniprogramConfig, wechatMiniprogramCallBack);
    }

    @Override
    public Map<String, Object> addTemplate(String id, int[] keywordIdList) {
        String accessToken = super.getAccessToken().getAccessToken();
        String authURL = WechatMiniprogramApiConstants.MESSAGE_ADDTEMPLATE + "?access_token=" + accessToken;
        Map<String, Object> sendGetParams = new HashMap<>(16);
        // 接口调用凭证
        sendGetParams.put("access_token", accessToken);
        // 模板标题id，可通过接口获取，也可登录小程序后台查看获取
        sendGetParams.put("id", id);
        // 开发者自行组合好的模板关键词列表，关键词顺序可以自由搭配（例如[3,5,4]或[4,5,3]），最多支持10个关键词组合
        sendGetParams.put("keyword_id_list", keywordIdList);
        // 请求
        String resultJson = WechatHttpTools.post(authURL, sendGetParams);
        return JSONObject.parseObject(resultJson);
    }

    @Override
    public Map<String, Object> deleteTemplate(String templateId) {
        String accessToken = super.getAccessToken().getAccessToken();
        String authURL = WechatMiniprogramApiConstants.MESSAGE_DELETETEMPLATE + "?access_token=" + accessToken;
        Map<String, Object> sendGetParams = new HashMap<>(16);
        // 	要删除的模板id
        sendGetParams.put("template_id", templateId);
        // 请求
        String resultJson = WechatHttpTools.post(authURL, sendGetParams);
        return JSONObject.parseObject(resultJson);
    }


    @Override
    public Map<String, Object> getTemplateLibraryById(String id) {
        String accessToken = super.getAccessToken().getAccessToken();
        String authURL = WechatMiniprogramApiConstants.MESSAGE_GETTEMPLATELIBRARYBYID + "?access_token=" + accessToken;
        Map<String, Object> sendGetParams = new HashMap<>(16);
        // 模板标题id，可通过接口获取，也可登录小程序后台查看获取
        sendGetParams.put("id", id);
        // 请求
        String resultJson = WechatHttpTools.post(authURL, sendGetParams);
        return JSONObject.parseObject(resultJson);
    }

    @Override
    public Map<String, Object> getTemplateLibraryList(int offset, int count) {
        String accessToken = super.getAccessToken().getAccessToken();
        String authURL = WechatMiniprogramApiConstants.MESSAGE_GETTEMPLATELIBRARYLIST + "?access_token=" + accessToken;
        Map<String, Object> sendGetParams = new HashMap<>(16);
        // 用于分页，表示从offset开始。从 0 开始计数。
        sendGetParams.put("offset", offset);
        // 用于分页，表示拉取count条记录。最大为 20。
        sendGetParams.put("count", count);
        // 请求
        String resultJson = WechatHttpTools.post(authURL, sendGetParams);
        return JSONObject.parseObject(resultJson);
    }


    @Override
    public Map<String, Object> getTemplateList(int offset, int count) {
        String accessToken = super.getAccessToken().getAccessToken();
        String authURL = WechatMiniprogramApiConstants.MESSAGE_GETTEMPLATELIST + "?access_token=" + accessToken;
        Map<String, Object> sendGetParams = new HashMap<>(16);
        // 用于分页，表示从offset开始。从 0 开始计数。
        sendGetParams.put("offset", offset);
        // 用于分页，表示拉取count条记录。最大为 20。
        sendGetParams.put("count", count);
        // 请求
        String resultJson = WechatHttpTools.post(authURL, sendGetParams);
        return JSONObject.parseObject(resultJson);
    }


    @Override
    public Map<String, Object> templateSend(String touser, String templateId,
                                            String page, String formId,
                                            Object data, String emphasisKeyword) {
        String accessToken = super.getAccessToken().getAccessToken();
        String authURL = WechatMiniprogramApiConstants.MESSAGE_TEMPLATESEND + "?access_token=" + accessToken;
        Map<String, Object> sendGetParams = new HashMap<>(16);
        // 接收者（用户）的 openid
        sendGetParams.put("touser", touser);
        // 所需下发的模板消息的id
        sendGetParams.put("template_id", templateId);
        // 点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转。
        if (page != null) {
            sendGetParams.put("page", page);
        }
        // 表单提交场景下，为 submit 事件带上的 formId；支付场景下，为本次支付的 prepay_id
        sendGetParams.put("form_id", formId);
        // 模板内容，不填则下发空模板。具体格式请参考示例。
        if (data != null) {
            sendGetParams.put("data", data);
        }
        // 模板需要放大的关键词，不填则默认无放大
        if (emphasisKeyword != null) {
            sendGetParams.put("emphasis_keyword", emphasisKeyword);
        }
        // 请求
        String resultJson = WechatHttpTools.post(authURL, sendGetParams);
        return JSONObject.parseObject(resultJson);
    }
}
