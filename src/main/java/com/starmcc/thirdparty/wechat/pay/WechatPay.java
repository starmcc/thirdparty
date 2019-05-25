package com.starmcc.thirdparty.wechat.pay;

import com.starmcc.thirdparty.wechat.pay.entity.WechatPayInfo;
import com.starmcc.thirdparty.wechat.pay.service.exception.WechatPayInfoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.SortedMap;

/**
 * @author qm
 * @date 2019/5/24 9:56
 * @Description wechat支付接口
 */
public interface WechatPay {

    /**
     * 微信预支付
     * 统一下单
     *
     * @param wechatPayInfo 支付内容对象
     * @return
     * @throws IOException
     * @throws WechatPayInfoException 支付对象参数错误
     */
    public SortedMap<String, Object> unifiedOrder(WechatPayInfo wechatPayInfo)
            throws IOException, WechatPayInfoException;


    /**
     * 微信支付回调解析
     * 请事先实现WechatPayCallBack以便内部自动解析并回调该接口方法
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     */
    public void parsePayCallBack(HttpServletRequest request, HttpServletResponse response)
            throws IOException;


}
