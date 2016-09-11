package javaSETest;

import java.util.ArrayList;
import java.util.List;

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
		List list = new ArrayList();
		list.add(11);
		System.out.println(list.subList(0, 1));
	}
	
}
