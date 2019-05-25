package com.starmcc.thirdparty.wechat.pay.tools;

import com.starmcc.thirdparty.wechat.pay.config.WechatPayConfig;
import com.starmcc.thirdparty.wechat.pay.entity.WechatPayInfo;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.*;

/**
 * @author qm
 * @date 2019/1/15 16:15
 * @Description 微信基础工具支持
 */
public class WechatPayBaseTools {

    /**
     * 获取随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        String base = "1234567890abcdefghijklmmopqrstuvwsyzQWERTYUIOPASDFGHJKLZXCVBNM";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 微信支付签名
     *
     * @param apiKey
     * @param apiKey
     * @return
     */
    public static SortedMap<String, Object> createSign(String apiKey, SortedMap<String, Object> parameters) {
        StringBuffer joinSignSb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                joinSignSb.append(k + "=" + v + "&");
            }
        }
        joinSignSb.append("key=" + apiKey);
        String sign = MdFiveTools.mdFiveEncode(joinSignSb.toString()).toUpperCase();
        parameters.put("sign", sign);
        // 打印信息
        System.out.println("进行签名的内容：" + joinSignSb.toString());
        System.out.println("请求预支付签名：" + sign);
        return parameters;
    }

    /**
     * 获取请求XML报文
     *
     * @param parameters
     * @return
     */
    public static String getRequestXml(SortedMap<String, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set<Map.Entry<String, Object>> es = parameters.entrySet();
        Iterator<Map.Entry<String, Object>> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            String key = (String) entry.getKey();
            String value = String.valueOf(entry.getValue());
            if ("attach".equalsIgnoreCase(key) || "body".equalsIgnoreCase(key) || "sign".equalsIgnoreCase(key)) {
                sb.append("<" + key + ">" + "<![CDATA[" + value + "]]></" + key + ">");
            } else {
                sb.append("<" + key + ">" + value + "</" + key + ">");
            }
        }
        sb.append("</xml>");
        System.out.println("组装完成的XML：" + sb.toString());
        return sb.toString();
    }

    /**
     * 请求方法
     *
     * @param requestUrl
     * @param requestMethod
     * @param outputStr
     * @return
     */
    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        StringBuffer buffer = null;
        try {
            URL url = new URL(requestUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(requestMethod);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            inputStream = conn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            System.out.println("返回消息体：" + buffer.toString());
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            inputStream = null;
            conn.disconnect();
        }
        return null;
    }

    /**
     * 解析微信返回的XML报文
     *
     * @param strxml
     * @return
     * @throws IOException
     * @throws JDOMException
     */
    public static Map<String, String> doXMLParse(String strxml) throws IOException, JDOMException {
        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
        if (null == strxml || "".equals(strxml)) {
            return null;
        }
        Map<String, String> resMap = new HashMap<String, String>(16);
        InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List<?> list = root.getChildren();
        Iterator<?> it = list.iterator();
        while (it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List<?> children = e.getChildren();
            if (children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = getChildrenText(children);
            }
            System.out.println(k + "-" + v);
            resMap.put(k, v);
        }
        in.close();
        return resMap;
    }

    /**
     * 解析报文依赖
     *
     * @param children
     * @return
     */
    private static String getChildrenText(List<?> children) {
        StringBuffer sb = new StringBuffer();
        if (!children.isEmpty()) {
            Iterator<?> it = children.iterator();
            while (it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List<?> list = e.getChildren();
                sb.append("<" + name + ">");
                if (!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }
        return sb.toString();
    }

    /**
     * 拼接所需参数
     *
     * @param wechatPayInfo
     * @param tradeTypeName
     * @return
     * @throws UnknownHostException
     */
    public static SortedMap<String, Object> joinPayMap(WechatPayConfig wechatPayConfig,
                                                       WechatPayInfo wechatPayInfo)
            throws UnknownHostException {
        SortedMap<String, Object> payMap = new TreeMap<>();
        payMap.put("appid", wechatPayConfig.getAppid());
        payMap.put("mch_id", wechatPayConfig.getMchId());
        payMap.put("trade_type", wechatPayConfig.getWechatTradeType().name());
        payMap.put("notify_url", wechatPayConfig.getNotifyUrl());
        Field[] fields = wechatPayInfo.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                // 开放字段权限public
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                // 转下划线风格
                String key = StyleTools.transformNameByUnderline(field.getName());
                Object value = field.get(wechatPayInfo);
                // null 值不解析
                if (value != null) {
                    payMap.put(key, value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return payMap;
    }

    /**
     * 解析微信支付成功后回调的信息
     *
     * @param request
     * @return
     */
    public static Map<String, String> parseCallbackInfo(HttpServletRequest request) {
        InputStream inStream = null;
        ByteArrayOutputStream outSteam = null;
        try {
            inStream = request.getInputStream();
            outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            String resultxml = new String(outSteam.toByteArray(), "utf-8");
            return WechatPayBaseTools.doXMLParse(resultxml);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        } finally {
            try {
                outSteam.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

