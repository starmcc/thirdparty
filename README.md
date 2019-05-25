# thirdparty - Tp第三方API类库
> 正在努力封存第三方各种API对接库，如微信、支付宝等

> 目前仅有微信小程序模块和微信支付的统一下单接口，正在逐步完善更新，期待以后该类库的无限拓展

## 使用说明

### 引入依赖库

> 下载jar包  [点击下载依赖库](<https://github.com/starmcc/thirdparty/releases>)

> maven依赖加入该依赖库

```xml
<dependencies>
    <dependency>
        <groupId>com.starmcc</groupId>
        <artifactId>thirdparty</artifactId>
        <version>0.0.1</version>
        <scope>system</scope>
        <systemPath>${basedir}/lib/thirdparty-0.0.1.jar</systemPath>
    </dependency>
</dependencies>
```

> 注意：上述方式为引入本地jar包方式，`systemPath`请指定依赖库具体位置。

### 创建配置文件

> 在`resource`目录下创建`thirdparty.properties`配置文件

```properties
# ========Tp第三方类库支持配置文件=========
# 小程序 appid
wechat.miniprogram.appid=
# 小程序 secret
wechat.miniprogram.secret=
# 调用微信支付的appid
wechat.pay.appid=
# 商户号 微信商户平台-微信支付分配的商户号
wechat.pay.mch_id=
# 微信商户平台-支付key
wechat.pay.mch_key=
# 通知地址	notify_url 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
wechat.pay.notify_url=
# 支付类型
# JSAPI--JSAPI支付（或小程序支付）、
# NATIVE--Native支付、
# APP--app支付，
# MWEB--H5支付，
# 不同trade_type决定了调起支付的方式
wechat.pay.trade_type=
```

### 开始使用

> 以小程序接口为例，先创建一个需要用到的回调类，并实现`WechatMiniprogramCallBack`

```java
public class MiniprogramCallBack implements WechatMiniprogramCallBack{
    
    @Override
    public AccessTokenResult getCacheAccessToken() {
        // 具体业务逻辑
        return null;
    }

    @Override
    public void callBackAccessToken(AccessTokenResult accessTokenResult) {
        // 具体业务逻辑
    }
}
```

> 以`main`方法举例调用

```java
public class Demo {
    public static void main(String[] args) {
        // 使用 TpWechat 创建小程序实例，赐予该实例回调实现类
        WechatMiniprogram miniprogram = TpWechat.createMiniprogram(new MiniprogramCallBack());
        // 调用微信小程序提供的 code2Session 方法，返回微信回调对象。
        Code2SessionResult jsCode = miniprogram.code2Session("jsCode");
    }
}
```




## 关于作者

- 小生不才,黄阶后期,跪求各路高手路过指点迷津
- 浅梦在此感谢各位的Star
- Email:starczt1992@163.com
- 一个纯粹的Java农民、一个有梦想的Java农民。

### 指若下键万里行，如入浅梦醉逍遥

