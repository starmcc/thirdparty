package com.starmcc.thirdparty.http.headers;

/**
 * ContentType 枚举类
 *
 * @Author: qm
 * @Date: 2019/6/24 15:39
 */
public enum ContentType {

    /**
     * application/x-www-form-urlencoded
     */
    APPLICATION_X_WWW_FROM_URLENCODED("application/x-www-form-urlencoded"),
    MULTIPART_FORM_DATA("multipart/form-data"),
    APPLICATION_JSON("application/json;charset=utf-8"),
    TEXT_XML("text/xml");

    private String value;

    public String getValue(){
        return this.value;
    }

    private ContentType(String value){
        this.value = value;
    }

}
