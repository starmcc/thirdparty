package com.starmcc.thirdparty.http.headers;

/**
 * Cache-Control 枚举类
 *
 * @Author: qm
 * @Date: 2019/6/24 15:45
 */
public enum CacheControl {
    /**
     *必须先与服务器确认返回的响应是否被更改，
     * 然后才能使用该响应来满足后续对同一个网址的请求。
     * 因此，如果存在合适的验证令牌 (ETag)，
     * no-cache 会发起往返通信来验证缓存的响应，
     * 如果资源未被更改，可以避免下载。
     */
    NO_CACHE("no-cache"),
    /**
     *所有内容都不会被缓存到缓存或 Internet 临时文件中
     */
    NO_STORE("no-store");

    private String value;

    public String getValue(){
        return this.value;
    }

    private CacheControl(String value){
        this.value = value;
    }

}
