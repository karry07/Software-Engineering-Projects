package com.utils;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Mybatis-Plus工具类
 */
public class MPUtil {
	public static final char UNDERLINE = '_';


	/**
	 * 将Bean转换为Map，并将Map的键从驼峰格式转换为下划线格式，键的前缀为指定的前缀
	 * @param bean 需要转换的Bean
	 * @param pre 转换后的键的前缀
	 * @return 转换后的Map
	 */
		public static Map allEQMapPre(Object bean,String pre) {
		   Map<String, Object> map =BeanUtil.beanToMap(bean);
		  return camelToUnderlineMap(map,pre);
	   }

	/**
	 * 将Bean转换为Map，并将Map的键从驼峰格式转换为下划线格式
	 * @param bean 需要转换的Bean
	 * @return 转换后的Map
	 */
		public static Map allEQMap(Object bean) {
		   Map<String, Object> map =BeanUtil.beanToMap(bean);
		   return camelToUnderlineMap(map,"");
	   }

	/**
	 * 将Bean转换为Map，并将Map的键从驼峰格式转换为下划线格式，然后生成like条件
	 * @param wrapper 条件构造器
	 * @param bean 需要转换的Bean
	 * @param pre 转换后的键的前缀
	 * @return 带有like条件的条件构造器
	 */
	   public static Wrapper allLikePre(Wrapper wrapper,Object bean,String pre) {
			   Map<String, Object> map =BeanUtil.beanToMap(bean);
			   Map result = camelToUnderlineMap(map,pre);
			 
			return genLike(wrapper,result);
		}

	/**
	 * 将Bean转换为Map，然后生成like条件
	 * @param wrapper 条件构造器
	 * @param bean 需要转换的Bean
	 * @return 带有like条件的条件构造器
	 */
		public static Wrapper allLike(Wrapper wrapper,Object bean) {
			  Map result = BeanUtil.beanToMap(bean, true, true);			 
			return genLike(wrapper,result);
		}


	/**
	 * 生成like条件
	 * @param wrapper 条件构造器
	 * @param param 需要转换的Map
	 * @return 带有like条件的条件构造器
	 */
		public static Wrapper genLike( Wrapper wrapper,Map param) {
			Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
			int i=0;
			while (it.hasNext()) {
				if(i>0) wrapper.and();
				Map.Entry<String, Object> entry = it.next();
				String key = entry.getKey();
				String value = (String) entry.getValue();
				wrapper.like(key, value);
				i++;
			}
			return wrapper;
		}

	/**
	 * 根据Bean生成like或等于条件
	 * @param wrapper 条件构造器
	 * @param bean 需要转换的Bean
	 * @return 带有like或等于条件的条件构造器
	 */
		public static Wrapper likeOrEq(Wrapper wrapper,Object bean) {
			  Map result = BeanUtil.beanToMap(bean, true, true);			 
			return genLikeOrEq(wrapper,result);
		}

	/**
	 * 生成like或等于条件
	 * @param wrapper 条件构造器
	 * @param param 需要转换的Map
	 * @return 带有like或等于条件的条件构造器
	 */
		public static Wrapper genLikeOrEq( Wrapper wrapper,Map param) {
			Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
			int i=0;
			while (it.hasNext()) {
				if(i>0) wrapper.and();
				Map.Entry<String, Object> entry = it.next();
				String key = entry.getKey();
				if(entry.getValue().toString().contains("%")) {
					wrapper.like(key, entry.getValue().toString().replace("%", ""));
				} else {
					wrapper.eq(key, entry.getValue());
				}
				i++;
			}
			return wrapper;
		}


	/**
	 * 根据Bean生成等于条件
	 * @param wrapper 条件构造器
	 * @param bean 需要转换的Bean
	 * @return 带有等于条件的条件构造器
	 */
		public static Wrapper allEq(Wrapper wrapper,Object bean) {
			  Map result = BeanUtil.beanToMap(bean, true, true);			 
			return genEq(wrapper,result);
		}


	/**
	 * 生成等于条件
	 * @param wrapper 条件构造器
	 * @param param 需要转换的Map
	 * @return 带有等于条件的条件构造器
	 */
		public static Wrapper genEq( Wrapper wrapper,Map param) {
			Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
			int i=0;
			while (it.hasNext()) {
				if(i>0) wrapper.and();
				Map.Entry<String, Object> entry = it.next();
				String key = entry.getKey();
				wrapper.eq(key, entry.getValue());
				i++;
			}
			return wrapper;
		}


	/**
	 * 生成between条件
	 * @param wrapper 条件构造器
	 * @param params 需要转换的Map
	 * @return 带有between条件的条件构造器
	 */
		public static Wrapper between(Wrapper wrapper,Map<String, Object> params) {
			for(String key : params.keySet()) {
				String columnName = "";
				if(key.endsWith("_start")) {
					columnName = key.substring(0, key.indexOf("_start"));
					if(StringUtils.isNotBlank(params.get(key).toString())) {
						wrapper.ge(columnName, params.get(key));
					}
				}
				if(key.endsWith("_end")) {
					columnName = key.substring(0, key.indexOf("_end"));
					if(StringUtils.isNotBlank(params.get(key).toString())) {
						wrapper.le(columnName, params.get(key));
					}
				}
			}
			return wrapper;
		}

	/**
	 * 根据参数生成排序条件
	 * @param wrapper 条件构造器
	 * @param params 需要转换的Map
	 * @return 带有排序条件的条件构造器
	 */
		public static Wrapper sort(Wrapper wrapper,Map<String, Object> params) {
			String order = "";
			if(params.get("order") != null && StringUtils.isNotBlank(params.get("order").toString())) {
				order = params.get("order").toString();
			}
			if(params.get("sort") != null && StringUtils.isNotBlank(params.get("sort").toString())) {
				if(order.equalsIgnoreCase("desc")) {
					wrapper.orderDesc(Arrays.asList(params.get("sort")));
				} else {
					wrapper.orderAsc(Arrays.asList(params.get("sort")));
				}
			}
			return wrapper;
		}


	/**
	 * 将驼峰格式字符串转换为下划线格式字符串
	 * @param param 需要转换的驼峰格式字符串
	 * @return 转换后的下划线格式字符串
	 */
	public static String camelToUnderline(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(UNDERLINE);
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	 /**
	 * 主函数，用于测试camelToUnderline方法
	 * @param ages 命令行参数
	 */
	public static void main(String[] ages) {
		System.out.println(camelToUnderline("ABCddfANM"));
	}
	/**
	 * 将Map中的键从驼峰格式转换为下划线格式
	 * @param param 需要转换的Map
	 * @param pre 转换后的键的前缀
	 * @return 转换后的Map
	 */
	public static Map camelToUnderlineMap(Map param, String pre) {

		Map<String, Object> newMap = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = camelToUnderline(key);
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {

				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		return newMap;
	}
}
