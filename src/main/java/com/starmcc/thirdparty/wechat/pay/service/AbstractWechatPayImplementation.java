package com.starmcc.thirdparty.wechat.pay.service;

import com.starmcc.thirdparty.wechat.pay.WechatPay;
import com.starmcc.thirdparty.wechat.pay.config.WechatPayApiConstants;
import com.starmcc.thirdparty.wechat.pay.service.callback.WechatPayCallBack;
import com.starmcc.thirdparty.wechat.pay.config.WechatPayConfig;
import com.starmcc.thirdparty.wechat.pay.entity.WechatPayInfo;
import com.starmcc.thirdparty.wechat.pay.service.exception.WechatPayInfoException;
import com.starmcc.thirdparty.wechat.pay.tools.WechatPayBaseTools;
import org.apache.commons.lang3.StringUtils;
import org.jdom.JDOMException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author qm
 * @date 2019/5/24 10:06
 * @Description 微信支付实现
 */
public abstract class AbstractWechatPayImplementation implements WechatPay {


    protected WechatPayConfig wechatPayConfig;

    protected WechatPayCallBack wechatPayCallBack;

    /**
     * 禁止实例化
     */
    private AbstractWechatPayImplementation() {
    }

    /**
     * @param appid     微信分配的小程序ID
     * @param mchId     商户号 微信商户平台-微信支付分配的商户号
     * @param mchKey    微信商户平台-支付的key
     * @param notifyUrl 通知地址	notify_url 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
     */
    public AbstractWechatPayImplementation(WechatPayConfig wechatPayConfig, WechatPayCallBack wechatPayCallBack) {
        this.wechatPayConfig = wechatPayConfig;
        this.wechatPayCallBack = wechatPayCallBack;
    }

    /**
     * 校验支付对象
     *
     * @param wechatPayInfo
     */
    private static void verifyPayInfo(WechatPayInfo wechatPayInfo) throws WechatPayInfoException {
        if (wechatPayInfo == null) {
            throw new WechatPayInfoException("[wechatPayInfo]ERROR：支付对象不能为空！");
        }
        if (StringUtils.isEmpty(wechatPayInfo.getNonceStr())) {
            throw new WechatPayInfoException("[wechatPayInfo]ERROR：支付对象[nonce_str]不能为空！");
        }
        if (StringUtils.isEmpty(wechatPayInfo.getBody())) {
            throw new WechatPayInfoException("[wechatPayInfo]ERROR：支付对象[body]不能为空！");
        }
        if (StringUtils.isEmpty(wechatPayInfo.getOutTradeNo())) {
            throw new WechatPayInfoException("[wechatPayInfo]ERROR：支付对象[out_trade_no]不能为空！");
        }
        if (StringUtils.isEmpty(wechatPayInfo.getSpbillCreateIp())) {
            throw new WechatPayInfoException("[wechatPayInfo]ERROR：支付对象[spbill_create_ip]不能为空！");
        }
    }

    @Override
    public SortedMap<String, Object> unifiedOrder(WechatPayInfo wechatPayInfo)
            throws IOException, WechatPayInfoException {
        // 校验支付对象
        verifyPayInfo(wechatPayInfo);
        // 将 wechatPayInfo 组装为 SortedMap 容器对象
        SortedMap<String, Object> payMap = WechatPayBaseTools.joinPayMap(wechatPayConfig, wechatPayInfo);
        if (payMap == null) {
            return null;
        }
        // 将组装的 SortedMap 容器对象进行ASCII码从小到大排序，并且进行MD5签名。
        payMap = WechatPayBaseTools.createSign(wechatPayConfig.getAppid(), payMap);
        // 将排序好的 SortedMap 容器对象 组装成 XML 微信规范的字符集
        String requestXML = WechatPayBaseTools.getRequestXml(payMap);
        // 将组装好的 XML 发送给微信 请求预支付接口
        String result = WechatPayBaseTools.httpsRequest(
                WechatPayApiConstants.UNIFIEDORDER, "POST", requestXML);
        if (result == null) {
            return null;
        }
        // 将微信返回XML报文信息解析成我们所需的Map对象
        Map<String, String> map = null;
        try {
            map = WechatPayBaseTools.doXMLParse(result);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 返回给前端调起支付页面，该对象
        SortedMap<String, Object> frontMap = new TreeMap<String, Object>();
        //这个applyId一定要大写 而且签名的参数和调用方法的参数值一定要统一
        frontMap.put("appId", wechatPayConfig.getAppid());
        frontMap.put("package", "prepay_id=" + map.get("prepay_id"));
        frontMap.put("signType", "MD5");
        frontMap.put("nonceStr", WechatPayBaseTools.getRandomString(32));
        frontMap.put("timeStamp", Long.toString(System.currentTimeMillis() / 1000));
        frontMap.put("paySign", WechatPayBaseTools.createSign(wechatPayConfig.getMchKey(), frontMap));
        return frontMap;
    }


    @Override
    public void parsePayCallBack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 解析微信支付回调的消息
        Map<String, String> callBackInfoMap = WechatPayBaseTools.parseCallbackInfo(request);
        // 下面这些参数需要的可以获取
        String returnCode = (String) callBackInfoMap.get("return_code");
        String outTradeNo = (String) callBackInfoMap.get("out_trade_no");
        String success = "SUCCESS";
        if (success.equalsIgnoreCase(returnCode)) {
            if (wechatPayCallBack.paySuccess(callBackInfoMap)) {
                // 调度者返回true,则表示已允许
                this.sendSuccessNotice(response);
                return;
            }
        } else {
            // 支付失败的业务逻辑
            wechatPayCallBack.payError(callBackInfoMap);
        }
    }


    /**
     * 发送成功XML通知微信
     *
     * @param response
     * @throws IOException
     */
    private void sendSuccessNotice(HttpServletResponse response) throws IOException {
        StringBuffer resXml = new StringBuffer();
        resXml.append("<xml>\n");
        resXml.append("<return_code><![CDATA[SUCCESS]]></return_code>\n");
        resXml.append("<return_msg><![CDATA[OK]]></return_msg>\n");
        resXml.append("</xml>");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(resXml.toString().getBytes());
    }

}
