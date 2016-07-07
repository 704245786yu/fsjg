package javaSETest;

import org.junit.Test;

import com.util.JacksonJson;

/**测试字符串方法*/
public class StringTest {

	@Test
	public void split(){
		String str = "服装 服饰 ";
//		String str = "服装";
		String[] strAry = str.split(" ");
		JacksonJson.printBeanToJson(strAry);
	}
	
	@Test
	public void test1(){
		String a = "几个数字";
		System.out.println(a.length());
	}
	
}
