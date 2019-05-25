package com.starmcc.thirdparty.wechat.miniprogram.service.message;

import java.util.Map;

/**
 * @author qm
 * @date 2019/5/23 22:59
 * @Description 客服消息接口实现
 */
public interface MessageService {

    /**
     * 下发客服当前输入状态给用户。详见 客服消息输入状态
     * 此接口需要客服消息接口权限。
     * 如果不满足发送客服消息的触发条件，则无法下发输入状态。
     * 下发输入状态，需要客服之前 30 秒内跟用户有过消息交互。
     * 在输入状态中（持续 15 秒），不可重复下发输入态。
     * 在输入状态中，如果向用户下发消息，会同时取消输入状态。
     *
     * @param touser  用户的 OpenID
     * @param command 命令 Typing	对用户下发"正在输入"状态 ，CancelTyping	取消对用户的"正在输入"状态
     * @return
     */
    Map<String, Object> setTyping(String touser, String command);


    /**
     * 发送客服消息给用户。详细规则见 发送客服消息
     *
     * @param touser          用户的 OpenID
     * @param msgtype         消息类型
     * @param content         文本消息内容，msgtype="text" 时必填
     * @param image           图片消息，msgtype="image" 时必填
     * @param link            图片消息，msgtype="link" 时必填
     * @param miniprogrampage 小程序卡片，msgtype="miniprogrampage" 时必填
     * @return
     */
    Map<String, Object> customerServiceSend(String touser, String msgtype,
                                            String content, Object image,
                                            Object link, Object miniprogrampage);

    /**
     * 组合模板并添加至帐号下的个人模板库
     *
     * @param id            模板标题id，可通过接口获取，也可登录小程序后台查看获取
     * @param keywordIdList 开发者自行组合好的模板关键词列表，关键词顺序可以自由搭配（例如[3,5,4]或[4,5,3]），最多支持10个关键词组合
     * @return
     */
    Map<String, Object> addTemplate(String id, int[] keywordIdList);


    /**
     * 删除帐号下的某个模板
     *
     * @param templateId
     * @return
     */
    Map<String, Object> deleteTemplate(String templateId);

    /**
     * 获取模板库某个模板标题下关键词库
     *
     * @param id 模板标题id，可通过接口获取，也可登录小程序后台查看获取
     * @return
     */
    Map<String, Object> getTemplateLibraryById(String id);

    /**
     * 获取小程序模板库标题列表
     *
     * @param offset 用于分页，表示从offset开始。从 0 开始计数。
     * @param count  用于分页，表示拉取count条记录。最大为 20。
     * @return
     */
    Map<String, Object> getTemplateLibraryList(int offset, int count);


    /**
     * 获取帐号下已存在的模板列表
     *
     * @param offset 用于分页，表示从offset开始。从 0 开始计数。
     * @param count  用于分页，表示拉取count条记录。最大为 20。最后一页的list长度可能小于请求的count。
     * @return
     */
    Map<String, Object> getTemplateList(int offset, int count);


    /**
     * 发送模板消息
     *
     * @param touser          接收者（用户）的 openid
     * @param templateId      所需下发的模板消息的id
     * @param page            点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转。
     * @param formId          表单提交场景下，为 submit 事件带上的 formId；支付场景下，为本次支付的 prepay_id
     * @param data            模板内容，不填则下发空模板。具体格式请参考示例。
     * @param emphasisKeyword 模板需要放大的关键词，不填则默认无放大
     * @return
     */
    Map<String, Object> templateSend(String touser, String templateId,
                                     String page, String formId,
                                     Object data, String emphasisKeyword);

    /**
     * 下发小程序和公众号统一的服务消息
     * @param touser 用户openid，可以是小程序的openid，也可以是mp_template_msg.appid对应的公众号的openid
     * @param weappTemplateMsg 小程序模板消息相关的信息，可以参考小程序模板消息接口; 有此节点则优先发送小程序模板消息
     * @param mpTemplateMsg 公众号模板消息相关的信息，可以参考公众号模板消息接口；有此节点并且没有weapp_template_msg节点时，发送公众号模板消息
     * @return
     */
    Map<String, Object> uniformSend(String touser,Object weappTemplateMsg,Object mpTemplateMsg);


    /**
     * 创建被分享动态消息的 activity_id。详见动态消息。
     * @return
     */
    Map<String, Object> createActivityId();

    /**
     * 修改被分享的动态消息。详见动态消息。
     * @param activityId 动态消息的 ID，通过 updatableMessage.createActivityId 接口获取
     * @param targetState 动态消息修改后的状态（具体含义见后文）
     * @param templateInfo 动态消息对应的模板信息
     * @return
     */
    Map<String, Object> setUpdatableMsg(String activityId,int targetState,Object templateInfo);

}
