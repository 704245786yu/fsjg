package taobaoTest;

import org.junit.Test;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

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
		req.setSmsParamString("{number:'黄丽ILOVEYOU'}");
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
}
