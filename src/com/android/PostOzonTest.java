package com.android;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;
import java.util.HashMap;

public class PostOzonTest {

    // PRD OZON_E_PACKET   315
//    public static String   ClientId      = "acc801ea-14e0-437d-bbf2-d135c3dae51e";
//    public static String   ClientSecret  = "ZdbpRVndolANR8iM";
//    public static String   ProviderID   = "315";
    // PRD OZON_E_PACKET_ECONOMY_TRACK   780
     public static String ClientId = "0457f870-fae5-4852-901f-22321b77e950";
     public static String ClientSecret = "5RAlDy4PQfFcbfHw";
     public static String ProviderID = "780";
     public static String   URL  = "https://api-logistic-platform.ozon.ru:443/";

    public static String[] RegionFromUid = null;
    public static String   token         = null;

    public static  void main(String[] args)  {
        try {
            Map<String, String> hashMap = new HashMap<>();
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .connectTimeout(600, TimeUnit.SECONDS).readTimeout(500, TimeUnit.SECONDS).build();
            MediaType mediaType = MediaType.parse("application/json");
            hashMap.put("ClientId", ClientId);
            hashMap.put("ClientSecret", ClientSecret);
            RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(hashMap));
            Request request = new Request.Builder().url(URL + "GetAuthToken").method("POST", body)
                    .addHeader("Content-Type", "application/json").build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            JSONObject parse = JSON.parseObject(response.body().string());
            token = parse.getString("Data");
            System.out.println("获取到token信息："+token);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        if (true) {
//            String asd2 = "";
//
//            File file2 = new File("C:\\Users\\岱岱\\Desktop\\OZon\\OZON_prd.txt");
//            try {
//                BufferedReader br = new BufferedReader(new FileReader(file2));
//                StringBuilder sb = new StringBuilder();
//                String temp = null;
//                while (null != (temp = br.readLine())) {
//                    sb.append(temp);
//                }
//                br.close();
//                asd2 = sb.toString();
//            }
//            catch (Exception fnfe) {
//                System.out.println(fnfe);
//            }
//            RegionFromUid = asd2.split(",");

            ReadLocalExcel t = new ReadLocalExcel();

            // 需要读取的文件的位置和文件名称
            List<List<String>> list = t.read("C:\\Users\\岱岱\\Desktop\\新建文件夹\\666.xlsx");

            List<Map<String, Object>> requestList = new ArrayList<>();
            int i = 1;
           // for (String string : RegionFromUid) {
                long date1 = System.currentTimeMillis();
                for (List<String> cellList : list) {
                    Map<String, Object> requestMap = new HashMap<String, Object>();
                    requestMap.put("ProviderID", ProviderID);
                    String info =  cellList.get(2);
                    if(info.startsWith("'0")){
                        info=info.replace("'0","0");
                    }
                    requestMap.put("Code",info ); //网点代码
                    requestMap.put("Name", cellList.get(4)); //网点名称
                    requestMap.put("MerchandiseReturn", true);
                    requestMap.put("AddressString", cellList.get(5)); //网点中文地址
                    //  自送/揽收
                    if("Y".equalsIgnoreCase(cellList.get(9))&&"Y".equalsIgnoreCase(cellList.get(10))){
                        requestMap.put("HowToGet", "自送/揽收");
                    }else if("Y".equalsIgnoreCase(cellList.get(9))&&"N".equalsIgnoreCase(cellList.get(10))){
                        requestMap.put("HowToGet", "揽收");
                    }else if("N".equalsIgnoreCase(cellList.get(9))&&"Y".equalsIgnoreCase(cellList.get(10))){
                        requestMap.put("HowToGet", "自送");
                    }else {
                        requestMap.put("HowToGet", "自送");}
                    requestMap.put("StoragePeriod", 1);

                    requestMap.put("Latitude", cellList.get(18));//纬度
                    requestMap.put("Longitude", cellList.get(19)); //经度
                    Map<String, Integer> priceMap = new HashMap<String, Integer>();
                    priceMap.put("Min",0);
                    priceMap.put("Max",500000);
                    requestMap.put("Price", priceMap); //最高价值
                    Map<String, Integer> weightGmMap = new HashMap<String, Integer>();
                    weightGmMap.put("Min",0);
                    weightGmMap.put("Max",2000);
                    requestMap.put("WeightGm", weightGmMap);     //  最高重量
                    Map<String, Integer> lengthMmMap = new HashMap<String, Integer>();
                    lengthMmMap.put("Min",0);
                    lengthMmMap.put("Max",900);
                    requestMap.put("LengthMm", lengthMmMap); //最长长度
                    Map<String, Integer> widthMmMap = new HashMap<String, Integer>();
                    widthMmMap.put("Min",0);
                    widthMmMap.put("Max",600);
                    requestMap.put("WidthMm",widthMmMap );// 最长宽度
                    Map<String, Integer> heightMmMap = new HashMap<String, Integer>();
                    heightMmMap.put("Min",0);
                    heightMmMap.put("Max",600);
                    requestMap.put("HeightMm",heightMmMap );//最长高度
                    Map<String, Integer> dimensionSumMmMap = new HashMap<String, Integer>();
                    dimensionSumMmMap.put("Min",0);
                    dimensionSumMmMap.put("Max",900);
                    requestMap.put("DimensionSumMm", dimensionSumMmMap); // 长宽高限制
                    requestMap.put("Deleted", false);     //  网点是否删除
                    requestMap.put("UpdatedAt", "2022-01-21T15:04:51Z"); //时间
                     String PostalCode = cellList.get(3).toString();
                     if (PostalCode.indexOf(".")>0){
                         PostalCode=PostalCode.replace(".0","");
                     }
                    requestMap.put("PostalCode", PostalCode);// 网点机构编码
                    List<Map<String, Object>> schedulesList = new ArrayList<>();
                    for(int s=1;s<=7;s++){
                        Map<String, Object> schedulesMap = new HashMap<String, Object>();
                        schedulesMap.put("Day",s);
                        if(i<=5){
                            schedulesMap.put("IsHoliday",false);
                        }else if(i==6){
                            String holiday6=cellList.get(13);
                            if(holiday6.equals("Y")){
                                schedulesMap.put("IsHoliday",false);
                            }else{
                                schedulesMap.put("IsHoliday",true);
                            }
                        }else{
                            String holiday7=cellList.get(14);
                            if(holiday7.equals("Y")){
                                schedulesMap.put("IsHoliday",false);
                            }else{
                                schedulesMap.put("IsHoliday",true);
                            }
                        }
                        schedulesMap.put("FromTo",cellList.get(12));
                        schedulesList.add(schedulesMap);
                    }
                    requestMap.put("Schedules", schedulesList); // 机构工作时间
                    requestList.add(requestMap);
                }
                long date2 = System.currentTimeMillis();
                System.out.println("组装requestList耗时" + (date1 - date2));

          //  }

          //  String info=JSONObject.toJSONString(requestList,Feature.OrderedField);
           // System.out.println("完整的报文语句:"+JSON.toJSONString(requestList));
            dopost(requestList, i);
        }
    }



    private static void dopost(List<Map<String, Object>> requestList, int i)  {
        Response response = null;
        try {
            long date3 = System.currentTimeMillis();
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .connectTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).build();
            MediaType mediaType = MediaType.parse("application/json");
            //java.net.URLEncoder.encode(JSON.toJSONString(requestList),"UTF-8")
            RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(requestList));
            Request request = new Request.Builder().url(URL + "v1/DropOffPoints").method("get", body)
                    .addHeader("Authorization", "Bearer " + token)
                    .addHeader("Content-Type", "application/json").build();
            try {
                response = client.newCall(request).execute();
                long date4 = System.currentTimeMillis();
                System.out.println("发送http请求耗时" + (date3 - date4));
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
        catch (Exception e) {
            // TODO: handle exception
            System.out.println("请求异常信息:"+e);
        }

//        if (!response.isSuccessful()){
//            throw new IOException("response"+response);
//        }

        try {
            Writer w = new FileWriter("C:\\Users\\岱岱\\Desktop\\新建文件夹\\新增e邮宝.txt", true);
            if (response == null || !response.isSuccessful()) {
               // System.out.println("响应有误");
                w.write(JSON.toJSONString(requestList) + "\r\n");
                w.write(response.body().string() + "\r\n");
            }
            else {
                w.write(response + "\r\n");
            }
            // w.write(JSON.toJSONString(i++)+"\r\n");
            w.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
        requestList.clear();
        System.out.println(i++);
    }

}
