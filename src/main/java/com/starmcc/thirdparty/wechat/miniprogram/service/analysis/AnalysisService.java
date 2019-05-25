package com.starmcc.thirdparty.wechat.miniprogram.service.analysis;

import java.util.List;
import java.util.Map;

/**
 * @author qm
 * @date 2019/5/23 20:18
 * @Description 数据分析业务接口
 */
public interface AnalysisService {

    /**
     * 获取用户访问小程序月留存
     * 请求json和返回json与天的一致，这里限定查询一个自然月的数据，
     * 时间必须按照自然月的方式输入： 如：20170201(月初), 20170228(月末)
     * @param beginDate 开始日期，为自然月第一天。格式为 yyyymmdd
     * @param endDate   结束日期，为自然月最后一天，限定查询一个月数据。格式为 yyyymmdd
     * @return
     */
    Map<String,Object> getMonthlyRetain(String beginDate, String endDate);

    /**
     * 获取用户访问小程序周留存
     * 请求json和返回json与天的一致，这里限定查询一个自然周的数据，
     * 时间必须按照自然周的方式输入： 如：20170306(周一), 20170312(周日)
     * @param beginDate 开始日期，为周一日期。格式为 yyyymmdd
     * @param endDate   结束日期，为周日日期，限定查询一周数据。格式为 yyyymmdd
     * @return
     */
    Map<String,Object> getWeeklyRetain(String beginDate, String endDate);


    /**
     * 获取用户访问小程序日留存
     * @param beginDate 开始日期。格式为 yyyymmdd
     * @param endDate   结束日期，限定查询1天数据，允许设置的最大值为昨日。格式为 yyyymmdd
     * @return
     */
    Map<String,Object> getDailyRetain(String beginDate, String endDate);


    /**
     * 获取用户访问小程序数据月趋势
     * @param beginDate 开始日期，为自然月第一天。格式为 yyyymmdd
     * @param endDate   结束日期，为自然月最后一天，限定查询一个月的数据。格式为 yyyymmdd
     * @return
     */
    List<Map> getMonthlyVisitTrend(String beginDate, String endDate);

    /**
     * 获取用户访问小程序数据周趋势
     * @param beginDate 开始日期，为周一日期。格式为 yyyymmdd
     * @param endDate   结束日期，为周日日期，限定查询一周数据。格式为 yyyymmdd
     * @return
     */
    List<Map> getWeeklyVisitTrend(String beginDate, String endDate);

    /**
     * 获取用户访问小程序数据日趋势
     * @param beginDate 开始日期。格式为 yyyymmdd
     * @param endDate   结束日期，限定查询1天数据，允许设置的最大值为昨日。格式为 yyyymmdd
     * @return
     */
    List<Map> getDailyVisitTrend(String beginDate, String endDate);


    /**
     * 获取小程序新增或活跃用户的画像分布数据。
     * 时间范围支持昨天、最近7天、最近30天。其中，
     * 新增用户数为时间范围内首次访问小程序的去重用户数，
     * 活跃用户数为时间范围内访问过小程序的去重用户数。
     * @param beginDate 开始日期。格式为 yyyymmdd
     * @param endDate   结束日期，开始日期与结束日期相差的天数限定为0/6/29，分别表示查询最近1/7/30天数据，允许设置的最大值为昨日。格式为 yyyymmdd
     * @return
     */
    Map<String,Object> getUserPortrait(String beginDate, String endDate);

    /**
     * 获取用户小程序访问分布数据
     * @param beginDate 开始日期。格式为 yyyymmdd
     * @param endDate   结束日期，限定查询 1 天数据，允许设置的最大值为昨日。格式为 yyyymmdd
     * @return
     */
    List<Map> getVisitDistribution(String beginDate, String endDate);


    /**
     * 访问页面。目前只提供按 page_visit_pv 排序的 top200。
     * @param beginDate 开始日期。格式为 yyyymmdd
     * @param endDate   结束日期，限定查询 1 天数据，允许设置的最大值为昨日。格式为 yyyymmdd
     * @return
     */
    Map<String,Object> getVisitPage(String beginDate, String endDate);

    /**
     * 获取用户访问小程序数据概况
     * @param beginDate 开始日期。格式为 yyyymmdd
     * @param endDate   结束日期，限定查询1天数据，允许设置的最大值为昨日。格式为 yyyymmdd
     * @return
     */
    List<Map> getDailySummary(String beginDate, String endDate);


}
