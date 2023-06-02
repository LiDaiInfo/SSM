package com.android;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.util.SystemOutLogger;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.*;

public class PDDOverWeight {


    public static void main(String[] args) {

       String  Timestamp="1505210978";
       String  ApiKey="TEST";
       String  Nonce="7cff68b2e9ed47cea1c0dc97af973ba9";
       String body="{\"biz_type\":1,\"count\":10}";
       String mds= MD5(body);
       System.out.println("md5:"+mds);
      //正确的值： String  ContentMD5="22D6A9A218DA9E2124555CF142194EBB";
       String screct = "TEST_SECRET";

       //如何排序   拼接
        Map<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("C-Timestamp",Timestamp);
        jsonMap.put("C-Api-Key",ApiKey);
        jsonMap.put("C-Nonce",Nonce);
        jsonMap.put("Content-MD5",mds);
        System.out.println("--jsonMap:"+jsonMap);

        JSONObject json = new JSONObject(jsonMap);
        System.out.println("------json:"+json);

//        Map<String, Object> map=jsonToMap(JSONObject.toJSONString(json));
//        System.out.println("----------map:"+map);


    String sign = getSign(JSONObject.toJSONString(json),screct);





//        String date="C-Api-Key"+ApiKey+"C-Nonce"+Nonce+"C-Timestamp"+Timestamp+"Content-MD5"+mds;
//        String zz = screct+date+screct;
      //正确的值：D6A6E9F43B95B47BC8B11B05D2691E0D  String sign = getSign(date,screct);
//        String sign = MD5(zz);
       System.out.println("sign:"+sign);

       System.out.println("******************************************************************");



//        String dataInfo = "{\"order_list\":[{\"biz_type\":1,\"cust_order_id\":\"11367\",\"sign_time\":\"2023-03-05 00:00:00\",\"weight\":1.0,\"volume\":2.0,\"qty\":3,\"product_type\":4,\"transport_mode\":1,\"delivery_mode\":1,\"sender\":{\"name\":\"战三\",\"phone1\":\"15454545454\",\"province\":\"福建省\",\"city\":\"厦门市\",\"county\":\"湖里区\",\"street\":\"厦门湖里***\"},\"receiver\":{\"name\":\"李四\",\"phone1\":\"15454545454\",\"province\":\"福建省\",\"city\":\"厦门市\",\"county\":\"思明区\",\"street\":\"厦门思明****\"},\"amount_fee\":1000.0,\"operator\":\"km_sj01\",\"got_time\":\"2023-02-21 00:00:00\",\"signger\":\"詹飞\"}]}";
//        String s_mds = MD5(dataInfo);

        String c_sign=sign;

        String url = "http://testapi.cnpl-ltl.com:3501/SpecialOrder/XjAdd";

        jsonMap.put("C-Sign",c_sign);

        JSONObject json2 = new JSONObject(jsonMap);

        String response =   sendDouyin(json2,url);

        System.out.println("response:"+response);

    }





    public static String getSign(String data, String parternID) {
        String d = douyinSecret(data, parternID);
        System.out.println("组装："+d);
        String newSign = MD5(d);
        return newSign;
    }

    public static String douyinSecret(String data, String parternID) {
        StringBuffer sb = new StringBuffer();
        Map<String, Object> json = null;
        String KVstr = "";
        try {
            json = jsonToMap(data);
        } catch (Exception e) {
        }
        System.out.println("json:"+json);
        try {
            KVstr = parseJSON2Map(JSONObject.toJSONString(json), sb);
        } catch (Exception e) {
        }
        KVstr = parternID + KVstr + parternID;
        return KVstr;
    }

    private static Map<String, Object> jsonToMap(String jsonStr) {
        Map<String, Object> treeMap = new TreeMap();
        JSONObject json = JSONObject.parseObject(jsonStr, Feature.OrderedField);// Feature.OrderedField实现解析后保存不乱序
        Iterator<String> keys = json.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            Object value = json.get(key);
            // 判断传入kay-value中是否含有""或null
            if (json.get(key) == null) {
                // 当JSON字符串存在null时,不将该kay-value放入Map中,即显示的结果不包括该kay-value
                continue;
            }
            // 判断是否为JSONArray(json数组)
            if (value instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) value;
                List<Object> arrayList = new ArrayList<>();
                for (Object object : jsonArray) {
                    // 判断是否为JSONObject，如果是 转化成TreeMap
                    if (object instanceof JSONObject) {
                        object = jsonToMap(object.toString());
                    }
                    arrayList.add(object);
                }
                treeMap.put(key, arrayList);
            } else {
                // 判断该JSON中是否嵌套JSON
                if (!"request".equals(key)) {
                    boolean flag = isJSONValid(value.toString());
                    if (flag) {
                        // 若嵌套json了,通过递归再对嵌套的json(即子json)进行排序
                        value = jsonToMap(value.toString());
                    }
                }
                // 其他基础类型直接放入treeMap
                // JSONObject可进行再次解析转换
                treeMap.put(key, value);
            }
        }
        return treeMap;
    }

    private final static boolean isJSONValid(String json) {
        try {
            if (null != json && !"".equals(json.trim())) {
                JSONObject.parseObject(json);
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    private static String parseJSON2Map(String jsonStr, StringBuffer sb) {

        Map<String, Object> map = new HashMap<String, Object>();
//        net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(jsonStr);
        JSONObject json = JSONObject.parseObject(jsonStr, Feature.OrderedField);
        System.out.println("**************："+json);
        for (Object k : json.keySet()) {
            if ("C-Sign".equals(k)) {
                continue;
            }
            sb.append(k + "");
            Object v = json.get(k);
            if (v instanceof net.sf.json.JSONArray) {
                Iterator it = ((net.sf.json.JSONArray) v).iterator();
                while (it.hasNext()) {
                    Object json2 = it.next();
                    parseJSON2Map(json2.toString(), sb);
                }
            } else {
                sb.append(v);
            }
        }
        return sb.toString();
    }

    public final static String MD5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private static String sendDouyin(JSONObject date,String url){

        String data = date.toJSONString();
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(data, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            // 执行http请求
            response = httpClient.execute(httpPost);
            System.out.println("打印响应状态码："+response.getStatusLine().getStatusCode());
            if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            }
            String value =   response.getFirstHeader("C-Result").getValue();
            System.out.println("C-Result:"+value);

            String Message =   response.getFirstHeader("C-Message").getValue();
            System.out.println("C-Message:"+Message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;

    }

}
