package com.starmcc.thirdparty.wechat.miniprogram.entity;

/**
 * @author qm
 * @date 2019/5/23 15:51
 * @Description 微信小程序授权返回实体
 */
public class AccessTokenResult {
    /**
     * 	获取到的凭证
     */
    private String accessToken;
    /**
     * 凭证有效时间，单位：秒。目前是7200秒之内的值。
     */
    private String expiresIn;
    /**
     * 	错误码
     */
    private Integer errcode;
    /**
     * 	错误信息
     */
    private String errmsg;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
