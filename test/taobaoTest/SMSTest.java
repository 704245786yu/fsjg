package taobaoTest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import org.junit.Test;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.util.JacksonJson;

public class SMSTest {
	private String url = "http://gw.api.taobao.com/router/rest";
	private String appkey = "23454065";
	private String secret = "9a0716c89346d304b0de9e0006607d91";
	
	@Test
	public void test(){
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("");
		req.setSmsType("normal");
		req.setSmsFreeSignName("中国服饰加工网");
		req.setSmsParamString("{number:'^-^'}");
		req.setRecNum("15221972921");
		req.setSmsTemplateCode("SMS_14761073");
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		try {
			rsp = client.execute(req);
		} catch (ApiException e) {
			e.printStackTrace();
		}
		System.out.println(rsp.getBody());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test1(){
		String response = "{\"alibaba_aliqin_fc_sms_num_send_response\":"
				+ "{\"result\":{\"err_code\":\"0\",\"model\":\"102982209284^1103744613697\",\"success\":true},\"request_id\":\"yf38ndkkmi4\"}}";
		HashMap<String,LinkedHashMap<String,Object>> map = (HashMap<String,LinkedHashMap<String,Object>>)JacksonJson.getBean(response, HashMap.class);
		LinkedHashMap<String,Object> o = (LinkedHashMap<String,Object>)map.get("alibaba_aliqin_fc_sms_num_send_response").get("result");
		Set<String> keySet = o.keySet();
		Iterator<String> i = keySet.iterator();
		while(i.hasNext()){
			System.out.println(o.get(i.next()).getClass());
		}
		System.out.println(o.get("success"));
	}
}
