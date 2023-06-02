package com.android;

//import java.security.MessageDigest;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.TreeMap;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;


public class YunDaSignUtil {

//	public static String getSign(String data, String AccessSecret) {
//		StringBuffer sb = new StringBuffer();
//		Map<String, Object> json = null;
//		String KVstr = "";
//		try {
//			json = jsonToMap(data);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//		//System.out.println("排序后的123json>>>" + JSONObject.toJSONString(json));
//		try {
//			KVstr = parseJSON2Map(JSONObject.toJSONString(json), sb);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//		KVstr = KVstr.substring(0, KVstr.length() - 1);
//		//System.out.println("k-v拼接后：" + KVstr);
//		// String encodeStr = getSHA256(KVstr + AccessSecret);
//		String sign = MD5(KVstr + AccessSecret);
//		return sign;
//	}
//
//	private static String MD5(String str) {
//		//System.out.println("str" + str);
//		MessageDigest md5;
//		String sign = "";
//		try {
//			md5 = MessageDigest.getInstance("MD5");
//			byte[] digest = md5.digest(str.getBytes("utf-8"));
//
//			final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
//			StringBuilder sb = new StringBuilder();
//			for (int i = 0; i < digest.length; i++) {
//				sb.append(HEX_DIGITS[(digest[i] >> 4) & 0x0f]);
//				sb.append(HEX_DIGITS[digest[i] & 0x0f]);
//			}
//			//System.out.println(sb.toString());
//			sign = CoralHelper.base64StringFromByteArray(sb.toString().getBytes("utf-8"));
//			//System.out.println(sign);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//		return sign;
//	}
//
//	private static Map<String, Object> jsonToMap(String jsonStr) {
//		Map<String, Object> treeMap = new TreeMap();
//		JSONObject json = JSONObject.parseObject(jsonStr);// Feature.OrderedField实现解析后保存不乱序
//		Iterator<String> keys = json.keySet().iterator();
//		while (keys.hasNext()) {
//			String key = keys.next();
//			Object value = json.get(key);
//			// 判断传入kay-value中是否含有""或null
//			if (json.get(key) == null) {
//				// 当JSON字符串存在null时,不将该kay-value放入Map中,即显示的结果不包括该kay-value
//				continue;
//			}
//			// 判断是否为JSONArray(json数组)
//			if (value instanceof JSONArray) {
//				JSONArray jsonArray = (JSONArray) value;
//				List<Object> arrayList = new ArrayList<>();
//				for (Object object : jsonArray) {
//					// 判断是否为JSONObject，如果是 转化成TreeMap
//					if (object instanceof JSONObject) {
//						object = jsonToMap(object.toString());
//					}
//					arrayList.add(object);
//				}
//				treeMap.put(key, arrayList);
//			} else {
//				// 判断该JSON中是否嵌套JSON
//				if (!"extra".equals(key) && !"serviceValue".equals(key)) {
//					boolean flag = isJSONValid(value.toString());
//					if (flag) {
//						// 若嵌套json了,通过递归再对嵌套的json(即子json)进行排序
//						value = jsonToMap(value.toString());
//					}
//				}
//				// 其他基础类型直接放入treeMap
//				// JSONObject可进行再次解析转换
//				treeMap.put(key, value);
//			}
//		}
//		return treeMap;
//	}
//
//	private final static boolean isJSONValid(String json) {
//		try {
//			if (null != json && !"".equals(json.trim())) {
//				JSONObject.parseObject(json);
//			} else {
//				return false;
//			}
//		} catch (Exception ex) {
//			return false;
//		}
//		return true;
//	}
//
//	private static String parseJSON2Map(String jsonStr, StringBuffer sb) {
//
//		Map<String, Object> map = new HashMap<String, Object>();
//		net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(jsonStr);
//		for (Object k : json.keySet()) {
//			sb.append(k + "=");
//			Object v = json.get(k);
//			if (v instanceof net.sf.json.JSONArray) {
//				Iterator it = ((net.sf.json.JSONArray) v).iterator();
//				while (it.hasNext()) {
//					Object json2 = it.next();
//					parseJSON2Map(json2.toString(), sb);
//				}
//			} else if (v instanceof net.sf.json.JSONObject) {
//				Iterator it = ((net.sf.json.JSONObject) v).entrySet().iterator();
//				while (it.hasNext()) {
//					Map.Entry<String, Object> entry = (Entry<String, Object>) it.next();
//					/*
//					 * if ("signature".equals(entry.getKey())) { continue; }
//					 */
//					sb.append(entry.getKey() + "=");
//					Object v1 = entry.getValue();
//					if (v1 instanceof net.sf.json.JSONObject) {
//						parseJSON2Map(v1.toString(), sb);
//					} else if (v1 instanceof net.sf.json.JSONArray) {
//						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//						Iterator it1 = ((net.sf.json.JSONArray) v1).iterator();
//						while (it1.hasNext()) {
//							Object json2 = it1.next();
//							parseJSON2Map(json2.toString(), sb);
//						}
//					} else {
//						sb.append(entry.getValue());
//					}
//				}
//				map.put(k.toString(), v);
//			} else {
//				sb.append(v + "&");
//			}
//		}
//		return sb.toString();
//	}
}
