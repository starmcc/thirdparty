package com.starmcc.thirdparty.wechat.pay.service.exception;

/**
 * @author qm
 * @date 2019/5/24 14:39
 * @Description 支付对象异常
 */
public class WechatPayInfoException extends Exception{

    public WechatPayInfoException() {
        super();
    }

    public WechatPayInfoException(String message) {
        super(message);
    }

    public WechatPayInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    public WechatPayInfoException(Throwable cause) {
        super(cause);
    }
}
