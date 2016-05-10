package com.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("rawtypes")
public class JacksonJson {

	/**
	 * @author 支煜
	 * @param json 封装json串里包含bean的属性。
	 * */
	public static Object getBean(String json, Class<?> beanclass)
	{
		ObjectMapper mapper = new ObjectMapper();
//		DateFormat(mapper);
		Object beanJSON = null;
		try {
			beanJSON = mapper.readValue(json, beanclass);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return beanJSON;
	}
	
	/**
	 * javabean转换为json串，并包含了日期的格式化处理
	 * */
	public static String beanToJson(Object object)
	{
		ObjectMapper mapper = new ObjectMapper();
//		DateFormat(mapper);
		Writer strWriter = new StringWriter();
		try {
			mapper.writeValue(strWriter, object);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strWriter.toString();    
	}
	
	public static void printBeanToJson(Object object){
		System.out.println(beanToJson(object));
	}
	
	/**
	 * 时间的格式化处理
	 * */
	/*@SuppressWarnings("deprecation")
	private static void DateFormat(ObjectMapper mapper)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SerializationConfig serConfig = mapper.getSerializationConfig();
		serConfig.setDateFormat(dateFormat);
		DeserializationConfig deserializationConfig = mapper.getDeserializationConfig();
		deserializationConfig.setDateFormat(dateFormat);
		mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
	}*/
	
	/**
	 * 封装列表信息
	 * */
	public static String packageJson(List list)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", "success");
		map.put("ErrorDescription", null);
		map.put("TotalNum",list.size());
		map.put("List", list);
		return beanToJson(map);
	}
	
	/**
	 * 封装单个bean信息
	 * */
	public static String packageJson(String status, String ErrorDescription, Object object)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", status);
		map.put("ErrorDescription", ErrorDescription);
		map.put("result", object);
		return beanToJson(map);
	}
	
	/**
	 * 封装成功 或 错误信息
	 * */
	public static String packageJson(String status, String ErrorDescription)
	{
		Map<String,String> map = new HashMap<String,String>();
		map.put("status", status);
		map.put("ErrorDescription", ErrorDescription);
		return beanToJson(map);
	}
	
	/**
	 * 封装成功 或 错误信息
	 * @return Map<String,Object>
	 * */
	public static Map<String,Object> packResponseStatus(String status, String ErrorDescription)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", status);
		map.put("ErrorDescription", ErrorDescription);
		return map;
	}
	
	/**
	 * 封装返回的数据
	 * @return Map<String,Object>
	 * */
	public static Map<String,Object> packResponseStatus(String status, Object data)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", status);
		map.put("data", data);
		return map;
	}
}
