package com.starmcc.thirdparty.wechat.miniprogram.service.analysis;

import com.alibaba.fastjson.JSONObject;
import com.starmcc.thirdparty.wechat.miniprogram.config.WechatMiniprogramApiConstants;
import com.starmcc.thirdparty.wechat.miniprogram.config.WechatMiniprogramConfig;
import com.starmcc.thirdparty.wechat.miniprogram.http.WechatHttpTools;
import com.starmcc.thirdparty.wechat.miniprogram.service.callback.WechatMiniprogramCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qm
 * @date 2019/5/23 16:24
 * @Description 数据分析
 */
public abstract class AbstractAnalysisRetain extends AbstractAnalysis implements AnalysisService {


    /**
     * 注入配置项
     *
     * @param wechatMiniprogramConfig   微信小程序配置
     * @param wechatMiniprogramCallBack 微信小程序获取access_token回调接口
     */
    protected AbstractAnalysisRetain(WechatMiniprogramConfig wechatMiniprogramConfig,
                                     WechatMiniprogramCallBack wechatMiniprogramCallBack) {
        super(wechatMiniprogramConfig, wechatMiniprogramCallBack);
    }

    @Override
    public Map<String, Object> getMonthlyRetain(String beginDate, String endDate) {
        String accessToken = super.getAccessToken().getAccessToken();
        String authURL = WechatMiniprogramApiConstants.ANALYSIS_GETMONTHLYRETAIN + "?access_token=" + accessToken;
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
    public Map<String, Object> getWeeklyRetain(String beginDate, String endDate) {
        String accessToken = super.getAccessToken().getAccessToken();
        String authURL = WechatMiniprogramApiConstants.ANALYSIS_GETWEEKLYRETAIN + "?access_token=" + accessToken;
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
    public Map<String, Object> getDailyRetain(String beginDate, String endDate) {
        String accessToken = super.getAccessToken().getAccessToken();
        String authURL = WechatMiniprogramApiConstants.ANALYSIS_GETDAILYRETAIN + "?access_token=" + accessToken;
        Map<String, Object> sendGetParams = new HashMap<>(16);
        // 开始日期，为自然月第一天。格式为 yyyymmdd
        sendGetParams.put("begin_date", beginDate);
        // 结束日期，为自然月最后一天，限定查询一个月数据。格式为 yyyymmdd
        sendGetParams.put("end_date", endDate);
        // 请求
        String resultJson = WechatHttpTools.post(authURL, sendGetParams);
        return JSONObject.parseObject(resultJson);
    }
}
