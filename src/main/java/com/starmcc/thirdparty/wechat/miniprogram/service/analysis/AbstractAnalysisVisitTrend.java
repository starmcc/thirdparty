package com.starmcc.thirdparty.wechat.miniprogram.service.analysis;

import com.alibaba.fastjson.JSONArray;
import com.starmcc.thirdparty.wechat.miniprogram.config.WechatMiniprogramApiConstants;
import com.starmcc.thirdparty.wechat.miniprogram.config.WechatMiniprogramConfig;
import com.starmcc.thirdparty.wechat.miniprogram.http.WechatHttpTools;
import com.starmcc.thirdparty.wechat.miniprogram.service.callback.WechatMiniprogramCallBack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qm
 * @date 2019/5/23 17:34
 * @Description 访问趋势
 */
public abstract class AbstractAnalysisVisitTrend extends AbstractAnalysisRetain {


    /**
     * 注入配置项
     *
     * @param wechatMiniprogramConfig   微信小程序配置
     * @param wechatMiniprogramCallBack 微信小程序回调函数
     */
    protected AbstractAnalysisVisitTrend(WechatMiniprogramConfig wechatMiniprogramConfig,
                                         WechatMiniprogramCallBack wechatMiniprogramCallBack) {
        super(wechatMiniprogramConfig, wechatMiniprogramCallBack);
    }

    @Override
    public List<Map> getMonthlyVisitTrend(String beginDate, String endDate) {
        String accessToken = super.getAccessToken().getAccessToken();
        String authURL = WechatMiniprogramApiConstants.ANALYSIS_GETMONTHLYVISITTREND + "?access_token=" + accessToken;
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
    public List<Map> getWeeklyVisitTrend(String beginDate, String endDate) {
        String accessToken = super.getAccessToken().getAccessToken();
        String authURL = WechatMiniprogramApiConstants.ANALYSIS_GETWEEKLYVISITTREND + "?access_token=" + accessToken;
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
    public List<Map> getDailyVisitTrend(String beginDate, String endDate) {
        String accessToken = super.getAccessToken().getAccessToken();
        String authURL = WechatMiniprogramApiConstants.ANALYSIS_GETDAILYVISITTREND + "?access_token=" + accessToken;
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
