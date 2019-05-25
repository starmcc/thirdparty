package com.starmcc.thirdparty.wechat.miniprogram.service.analysis;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.starmcc.thirdparty.wechat.miniprogram.config.WechatMiniprogramApiConstants;
import com.starmcc.thirdparty.wechat.miniprogram.config.WechatMiniprogramConfig;
import com.starmcc.thirdparty.wechat.miniprogram.http.WechatHttpTools;
import com.starmcc.thirdparty.wechat.miniprogram.service.auth.AbstractAuth;
import com.starmcc.thirdparty.wechat.miniprogram.service.callback.WechatMiniprogramCallBack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qm
 * @date 2019/5/23 17:45
 * @Description 数据分析的其他接口
 */
public abstract class AbstractAnalysis extends AbstractAuth {


    /**
     * 注入配置项
     *
     * @param wechatMiniprogramConfig   微信小程序配置
     * @param wechatMiniprogramCallBack 微信小程序获取access_token回调接口
     */
    protected AbstractAnalysis(WechatMiniprogramConfig wechatMiniprogramConfig,
                               WechatMiniprogramCallBack wechatMiniprogramCallBack) {
        super(wechatMiniprogramConfig, wechatMiniprogramCallBack);
    }

    @Override
    public Map<String, Object> getUserPortrait(String beginDate, String endDate) {
        String accessToken = super.getAccessToken().getAccessToken();
        String authURL = WechatMiniprogramApiConstants.ANALYSIS_GETUSERPORTRAIT + "?access_token=" + accessToken;
        Map<String, Object> sendGetParams = new HashMap<>(16);
        // 开始日期，为自然月第一天。格式为 yyyymmdd
        sendGetParams.put("begin_date", beginDate);
        // 结束日期，为自然月最后一天，限定查询一个月数据。格式为 yyyymmdd
        sendGetParams.put("end_date", endDate);
        // 请求
        String resultJson = WechatHttpTools.post(authURL, sendGetParams);
        return JSONObject.parseObject(resultJson);
    }

    @Override
    public List<Map> getVisitDistribution(String beginDate, String endDate) {
        String accessToken = super.getAccessToken().getAccessToken();
        String authURL = WechatMiniprogramApiConstants.ANALYSIS_GETVISITDISTRIBUTION + "?access_token=" + accessToken;
        Map<String, Object> sendGetParams = new HashMap<>(16);
        // 开始日期，为自然月第一天。格式为 yyyymmdd
        sendGetParams.put("begin_date", beginDate);
        // 结束日期，为自然月最后一天，限定查询一个月数据。格式为 yyyymmdd
        sendGetParams.put("end_date", endDate);
        // 请求
        String resultJson = WechatHttpTools.post(authURL, sendGetParams);
        return JSONArray.parseArray(resultJson, Map.class);
    }

    @Override
    public Map<String, Object> getVisitPage(String beginDate, String endDate) {
        String accessToken = super.getAccessToken().getAccessToken();
        String authURL = WechatMiniprogramApiConstants.ANALYSIS_GETVISITPAGE + "?access_token=" + accessToken;
        Map<String, Object> sendGetParams = new HashMap<>(16);
        // 开始日期，为自然月第一天。格式为 yyyymmdd
        sendGetParams.put("begin_date", beginDate);
        // 结束日期，为自然月最后一天，限定查询一个月数据。格式为 yyyymmdd
        sendGetParams.put("end_date", endDate);
        // 请求
        String resultJson = WechatHttpTools.post(authURL, sendGetParams);
        return JSONObject.parseObject(resultJson);
    }

    @Override
    public List<Map> getDailySummary(String beginDate, String endDate) {
        String accessToken = super.getAccessToken().getAccessToken();
        String authURL = WechatMiniprogramApiConstants.ANALYSIS_GETDAILYSUMMARY + "?access_token=" + accessToken;
        Map<String, Object> sendGetParams = new HashMap<>(16);
        // 开始日期，为自然月第一天。格式为 yyyymmdd
        sendGetParams.put("begin_date", beginDate);
        // 结束日期，为自然月最后一天，限定查询一个月数据。格式为 yyyymmdd
        sendGetParams.put("end_date", endDate);
        // 请求
        String resultJson = WechatHttpTools.post(authURL, sendGetParams);
        return JSONArray.parseArray(resultJson, Map.class);
    }


}
