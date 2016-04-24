package javaSETest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.util.JacksonJson;

/**泛型测试
 * */
public class GenericTypeTest {

	public void findByNativeSql(List<?> values){
		JacksonJson.printBeanToJson(values);
	}
	
	@Test
	public void test1(){
		List<Object> values = new ArrayList<Object>();
        values.add(1);
        values.add(2);
        values.add("aaa");
        Integer[] ary = new Integer[]{3,4,5};
        values.add(ary);
        findByNativeSql(values);
	}
	
	@Test
	public void test2(){
		List<Object> values = new ArrayList<Object>();
		Integer[] ary = new Integer[]{3,4,5};
		System.out.println(values instanceof  Collection);
		System.out.println(ary instanceof Object[]);
	}
}
