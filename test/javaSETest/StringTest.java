package javaSETest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.util.DateTransform;
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
	
	@Test
	public void test2(){
		System.out.println((int)(Math.random()*(9999-1000+1))+1000);
	}
	
	@Test
	public void test3(){
		Long telephone = 18721414056L;
		String time = DateTransform.Date2String(new Date(), "YYMMddHHmmssSSS");
		String numStr = time + telephone.toString().substring(7);
		long num = Long.valueOf(numStr);
		System.out.println(numStr);
		System.out.println(num);
	}
	
	@Test
	public void test4(){
		java.util.Random r=new java.util.Random(10);
		for(int i=0;i<10;i++){
		    System.out.println(r.nextInt());
		}
		
//		new java.util.Random().nextInt(900)+100;
	}
}
