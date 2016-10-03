package com.util;

import java.util.HashMap;
import java.util.LinkedHashMap;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class SMS {
	private final static String url = "http://gw.api.taobao.com/router/rest";
	private final static String appkey = "23454065";
	private final static String secret = "9a0716c89346d304b0de9e0006607d91";
	private final static String smsType = "normal";	//短信类型
	private final static String freeSignName = "中国服饰加工网";	//短信签名
	
	/**发送验证码
	 * @return 返回验证码发送状态
	 * */
	@SuppressWarnings("unchecked")
	public static HashMap<String,LinkedHashMap<String,Object>> sendNum(long telephone,int num){
		String tele = String.valueOf(telephone);
		String number = String.valueOf(num);
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("");
		req.setSmsType(smsType);
		req.setSmsFreeSignName(freeSignName);
		req.setSmsParamString("{number:'"+number+"'}");
		req.setRecNum(tele);
		req.setSmsTemplateCode("SMS_14761073");
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		try {
			rsp = client.execute(req);
		} catch (ApiException e) {
			e.printStackTrace();
		}
		String body = rsp.getBody();
		return (HashMap<String,LinkedHashMap<String,Object>>)JacksonJson.getBean(body, HashMap.class);
	}
	
	/**发送短信通知:收到报价
	 * @param indentName 订单名称
	 * @param telephone 手机号
	 * @return 返回验证码发送状态
	 * */
	@SuppressWarnings("unchecked")
	public static HashMap<String,LinkedHashMap<String,Object>> sendQuoteNotice(String indentName,String telephone){
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("");
		req.setSmsType(smsType);
		req.setSmsFreeSignName(freeSignName);
		req.setSmsParamString("{indentName:'"+indentName+"'}");
		req.setRecNum(telephone);
		req.setSmsTemplateCode("SMS_16675931");
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		try {
			rsp = client.execute(req);
		} catch (ApiException e) {
			e.printStackTrace();
		}
		String body = rsp.getBody();
		return (HashMap<String,LinkedHashMap<String,Object>>)JacksonJson.getBean(body, HashMap.class);
	}
	
	/**发送短信通知:确认订单
	 * @param indentName 订单名称
	 * @param telephone 接单方手机号
	 * @return 返回验证码发送状态
	 * */
	@SuppressWarnings("unchecked")
	public static HashMap<String,LinkedHashMap<String,Object>> sendConfirmIndentNotice(String indentName,long telephone){
		String tele = String.valueOf(telephone);
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("");
		req.setSmsType(smsType);
		req.setSmsFreeSignName(freeSignName);
		req.setSmsParamString("{indentName:'"+indentName+"'}");
		req.setRecNum(tele);
		req.setSmsTemplateCode("SMS_16706105");
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		try {
			rsp = client.execute(req);
		} catch (ApiException e) {
			e.printStackTrace();
		}
		String body = rsp.getBody();
		return (HashMap<String,LinkedHashMap<String,Object>>)JacksonJson.getBean(body, HashMap.class);
	}
	
	@SuppressWarnings("unchecked")
	public static HashMap<String,LinkedHashMap<String,Object>> test1(long telephone,int num){
		String response = "{\"alibaba_aliqin_fc_sms_num_send_response\":"
				+ "{\"result\":{\"err_code\":\"0\",\"model\":\"102982209284^1103744613697\",\"success\":true},\"request_id\":\"yf38ndkkmi4\"}}";
		HashMap<String,LinkedHashMap<String,Object>> map = (HashMap<String,LinkedHashMap<String,Object>>)JacksonJson.getBean(response, HashMap.class);
		return map;
	}
}
