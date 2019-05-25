package com.starmcc.thirdparty.wechat.pay.entity;

import com.starmcc.thirdparty.wechat.pay.tools.WechatPayBaseTools;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author qm
 * @date 2019/1/15 17:35
 * @Description 微信支付信息实体
 */
public class WechatPayInfo {
    /**
     * 设备号
     * 自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
     */
    private String deviceInfo;

    /**
     * 随机字符串
     * 长度要求在32位以内。推荐随机数生成算法
     */
    private String nonceStr = WechatPayBaseTools.getRandomString(32);
    /**
     * 商品描述
     * 商品简单描述，该字段请按照规范传递，具体请见参数规定
     */
    private String body;

    /**
     * 商品详情
     * 商品详细描述，对于使用单品优惠的商户，该字段必须按照规范上传，详见“单品优惠参数说明”
     */
    private String detail;
    /**
     * 附加数据
     * 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
     */
    private String attach;
    /**
     * 商户订单号
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一。详见商户订单号
     */
    private String outTradeNo;

    /**
     * 标价币种
     * 符合ISO 4217标准的三位字母代码，默认人民币：CNY，详细列表请参见货币类型
     */
    private String feeType = "CNY";

    /**
     * 标价金额
     * 订单总金额，单位为分，详见支付金额
     */
    private int totalFee = 1;

    /**
     * 终端IP
     * 支持IPV4和IPV6两种格式的IP地址。调用微信支付API的机器IP
     */
    private String spbillCreateIp = InetAddress.getLocalHost().getHostAddress();

    /**
     * 交易起始时间
     * 订单生成时间，格式为yyyyMMddHHmmss，
     * 如2009年12月25日9点10分10秒表示为20091225091010。
     * 其他详见时间规则
     */
    private String timeStart;
    /**
     * 交易结束时间
     * 订单失效时间，格式为yyyyMMddHHmmss，
     * 如2009年12月27日9点10分10秒表示为20091227091010。
     * 订单失效时间是针对订单号而言的，
     * 由于在请求支付的时候有一个必传参数prepay_id只有两小时的有效期，
     * 所以在重入时间超过2小时的时候需要重新请求下单接口获取新的prepay_id。
     * 其他详见时间规则
     * 建议：最短失效时间间隔大于1分钟
     */
    private String timeExpire;

    /**
     * 订单优惠标记
     * 订单优惠标记，使用代金券或立减优惠功能时需要的参数，说明详见代金券或立减优惠
     */
    private String goodsTag;

    /**
     * 商品ID
     * trade_type=NATIVE时，此参数必传。此参数为二维码中包含的商品ID，商户自行定义。
     */
    private String productId;

    /**
     * 上传此参数no_credit--可限制用户不能使用信用卡支付
     */
    private String limitPay;

    /**
     * 用户标识 trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。
     */
    private String openid;

    /**
     * 电子发票入口开放标识
     * Y，传入Y时，支付成功消息和支付详情页将出现开票入口。
     * 需要在微信支付商户平台或微信公众平台开通电子发票功能，传此字段才可生效。
     */
    private String receipt;

    /**
     * 场景信息
     * 该字段常用于线下活动时的场景信息上报，
     * 支持上报实际门店信息，商户也可以按需求自己上报相关信息。
     * 该字段为JSON对象数据，
     * 对象格式为
     * {"store_info":{"id": "门店ID","name": "名称","area_code": "编码","address": "地址" }}
     */
    private String sceneInfo;

    public WechatPayInfo() throws UnknownHostException {
    }


    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getLimitPay() {
        return limitPay;
    }

    public void setLimitPay(String limitPay) {
        this.limitPay = limitPay;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getSceneInfo() {
        return sceneInfo;
    }

    public void setSceneInfo(String sceneInfo) {
        this.sceneInfo = sceneInfo;
    }

}
