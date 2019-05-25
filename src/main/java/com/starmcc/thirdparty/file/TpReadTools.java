package com.starmcc.thirdparty.file;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * @author qm
 * @date 2019/5/23 11:48
 * @Description 读取配置工具
 */
public class TpReadTools {

    private Properties properties;
    private String fileName;

    public TpReadTools() {
        this("thirdparty.properties");
    }

    /**
     * 指定文件名进行读取
     *
     * @param fileName
     */
    public TpReadTools(String fileName) {
        this.properties = new Properties();
        InputStreamReader inStream = null;
        try {
            // 读取properties文件,使用InputStreamReader字符流防止文件中出现中文导致乱码
            inStream = new InputStreamReader
                    (TpReadTools.class.getClassLoader().getResourceAsStream(fileName),
                            "UTF-8");
            properties.load(inStream);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * @param key
     * @return
     * @Title getQmResponseOut
     * @Description 从properties文件中读取出一个key对应的value
     */
    public String get(String key) {
        String value = properties.getProperty(key);
        return value;
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     * @Title getQmResponseOut
     * @Description 从properties文件中读取出一个key对应的value, 如果该value为空返回默认文本
     */
    public String get(String key, String defaultValue) {
        String value = properties.getProperty(key, defaultValue);
        if ("".equals(value.trim())) {
            value = defaultValue;
        }
        return value;
    }


}
