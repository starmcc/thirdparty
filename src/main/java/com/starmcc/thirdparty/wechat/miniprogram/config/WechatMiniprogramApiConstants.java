package com.starmcc.thirdparty.wechat.miniprogram.config;

/**
 * @Author: qm
 * @Date: 2019/5/26 2:15
 * @Description: 微信Api接口常量
 * 微信小程序API文档地址：https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/login.html
 */
public class WechatMiniprogramApiConstants {

    /**
     * 获取小程序全局唯一后台接口调用凭据（access_token）。
     */
    public static final String AUTH_GETACCESSTOKEN = "https://api.weixin.qq.com/cgi-bin/token";
    /**
     * 通过 wx.login() 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程。
     */
    public static final String AUTH_CODE2SESSION = "https://api.weixin.qq.com/sns/jscode2session";
    /**
     * 通过 wx.login() 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程。
     */
    public static final String AUTH_GETPAIDUNIONID = "https://api.weixin.qq.com/wxa/getpaidunionid";
    /**
     * 获取用户小程序访问分布数据
     */
    public static final String ANALYSIS_GETUSERPORTRAIT = "https://api.weixin.qq.com/datacube/getweanalysisappiduserportrait";
    /**
     * 获取用户小程序访问分布数据
     */
    public static final String ANALYSIS_GETVISITDISTRIBUTION = "https://api.weixin.qq.com/datacube/getweanalysisappidvisitdistribution";
    /**
     * 访问页面。目前只提供按 page_visit_pv 排序的 top200。
     */
    public static final String ANALYSIS_GETVISITPAGE = "https://api.weixin.qq.com/datacube/getweanalysisappidvisitpage";
    /**
     * 获取用户访问小程序数据概况
     */
    public static final String ANALYSIS_GETDAILYSUMMARY = "https://api.weixin.qq.com/datacube/getweanalysisappiddailysummarytrend";
    /**
     * 获取用户访问小程序月留存
     */
    public static final String ANALYSIS_GETMONTHLYRETAIN = "https://api.weixin.qq.com/datacube/getweanalysisappidmonthlyretaininfo";
    /**
     * 获取用户访问小程序周留存
     */
    public static final String ANALYSIS_GETWEEKLYRETAIN = "https://api.weixin.qq.com/datacube/getweanalysisappidweeklyretaininfo";
    /**
     * 获取用户访问小程序日留存
     */
    public static final String ANALYSIS_GETDAILYRETAIN = "https://api.weixin.qq.com/datacube/getweanalysisappiddailyretaininfo";
    /**
     * 获取用户访问小程序数据月趋势
     */
    public static final String ANALYSIS_GETMONTHLYVISITTREND = "https://api.weixin.qq.com/datacube/getweanalysisappidmonthlyvisittrend";
    /**
     * 获取用户访问小程序数据周趋势
     */
    public static final String ANALYSIS_GETWEEKLYVISITTREND = "https://api.weixin.qq.com/datacube/getweanalysisappidweeklyvisittrend";
    /**
     * 获取用户访问小程序数据日趋势
     */
    public static final String ANALYSIS_GETDAILYVISITTREND = "https://api.weixin.qq.com/datacube/getweanalysisappiddailyvisittrend";
    /**
     * 下发客服当前输入状态给用户。详见 客服消息输入状态
     */
    public static final String MESSAGE_SETTYPING = "https://api.weixin.qq.com/cgi-bin/message/custom/typing";
    /**
     * 发送客服消息给用户。详细规则见 发送客服消息
     */
    public static final String MESSAGE_CUSTOMERSERVICESEND = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
    /**
     * 组合模板并添加至帐号下的个人模板库
     */
    public static final String MESSAGE_ADDTEMPLATE = "https://api.weixin.qq.com/cgi-bin/wxopen/template/add";
    /**
     * 删除帐号下的某个模板
     */
    public static final String MESSAGE_DELETETEMPLATE = "https://api.weixin.qq.com/cgi-bin/wxopen/template/del";
    /**
     * 获取模板库某个模板标题下关键词库
     */
    public static final String MESSAGE_GETTEMPLATELIBRARYBYID = "https://api.weixin.qq.com/cgi-bin/wxopen/template/library/get";
    /**
     * 获取小程序模板库标题列表
     */
    public static final String MESSAGE_GETTEMPLATELIBRARYLIST = "https://api.weixin.qq.com/cgi-bin/wxopen/template/library/list";
    /**
     * 获取帐号下已存在的模板列表
     */
    public static final String MESSAGE_GETTEMPLATELIST = "https://api.weixin.qq.com/cgi-bin/wxopen/template/list";
    /**
     * 发送模板消息
     */
    public static final String MESSAGE_TEMPLATESEND = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send";
    /**
     * 下发小程序和公众号统一的服务消息
     */
    public static final String MESSAGE_UNIFORMSEND = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send";
    /**
     * 创建被分享动态消息的 activity_id。详见动态消息。
     */
    public static final String MESSAGE_CREATEACTIVITYID = "https://api.weixin.qq.com/cgi-bin/message/wxopen/activityid/create";
    /**
     * 修改被分享的动态消息。详见动态消息。
     */
    public static final String MESSAGE_SETUPDATABLEMSG = "https://api.weixin.qq.com/cgi-bin/message/wxopen/updatablemsg/send";

    /**
     * 禁止实例化
     */
    private WechatMiniprogramApiConstants(){

    }
}
